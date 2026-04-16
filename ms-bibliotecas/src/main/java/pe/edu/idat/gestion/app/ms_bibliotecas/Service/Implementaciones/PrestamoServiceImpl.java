package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoLibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Libro;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Prestamo;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.PrestamoLibro;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Usuario;
import pe.edu.idat.gestion.app.ms_bibliotecas.Mapper.PrestamoMapper;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.LibroRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.PrestamoRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;
    private final PrestamoMapper prestamoMapper;

    @Override
    @Transactional
    public PrestamoResponseDto create(PrestamoRequestDto dto) {
        log.info("Creando préstamo para usuario: {}", dto.getIdUsuario());

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);

        prestamo.setFechaPrestamo(LocalDateTime.now());
        prestamo.setFechaLimite(dto.getFechaLimite());
        prestamo.setEstado(1);
        prestamo.setObservaciones(dto.getObservaciones());

        List<PrestamoLibro> detalles = new ArrayList<>();

        for (PrestamoLibroRequestDto item : dto.getLibros()) {
            Libro libro = libroRepository.findById(item.getIdLibro())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            if (libro.getCantidadDisponible() < item.getCantidad()) {
                throw new RuntimeException("Sin stock para: " + libro.getTitulo());
            }

            libro.setCantidadDisponible(libro.getCantidadDisponible() - item.getCantidad());
            libroRepository.save(libro);

            PrestamoLibro detalle = new PrestamoLibro();
            detalle.setPrestamo(prestamo);
            detalle.setLibro(libro);
            detalle.setCantidad(item.getCantidad());
            detalles.add(detalle);
        }

        prestamo.setDetalles(detalles);
        return prestamoMapper.toResponse(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional
    public PrestamoResponseDto update(Long idPrestamo, PrestamoRequestDto dto) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        prestamo.setObservaciones(dto.getObservaciones());
        prestamo.setFechaLimite(dto.getFechaLimite());

        return prestamoMapper.toResponse(prestamoRepository.save(prestamo));
    }

    @Transactional
    public PrestamoResponseDto registrarDevolucion(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getEstado() == 0) {
            throw new RuntimeException("Este préstamo ya fue devuelto anteriormente");
        }

        prestamo.setFechaDevolucion(LocalDateTime.now());
        prestamo.setEstado(0);

        for (PrestamoLibro detalle : prestamo.getDetalles()) {
            Libro libro = detalle.getLibro();
            libro.setCantidadDisponible(libro.getCantidadDisponible() + detalle.getCantidad());
            libroRepository.save(libro);
        }

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        return prestamoMapper.toResponse(prestamoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoResponseDto findById(Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .map(prestamoMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDto> findAll() {
        return prestamoRepository.findAll()
                .stream()
                .map(prestamoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("No existe el préstamo"));

        if (prestamo.getEstado() == 1) {
            for (PrestamoLibro detalle : prestamo.getDetalles()) {
                Libro libro = detalle.getLibro();
                libro.setCantidadDisponible(libro.getCantidadDisponible() + detalle.getCantidad());
                libroRepository.save(libro);
            }
        }

        prestamoRepository.delete(prestamo);
        log.info("Prestamo eliminado con ID: {}", idPrestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDto> findByUsuario(Long idUsuario) {
        return prestamoRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(prestamoMapper::toResponse)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDto> findByUsername(String username) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return prestamoRepository.findByUsuario_IdUsuario(usuario.getIdUsuario())
                .stream()
                .map(prestamoMapper::toResponse)
                .toList();
    }

}