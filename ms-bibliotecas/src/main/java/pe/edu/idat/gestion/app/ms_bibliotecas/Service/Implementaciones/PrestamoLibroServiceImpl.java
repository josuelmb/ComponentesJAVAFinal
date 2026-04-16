package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoLibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoLibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Libro;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Prestamo;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.PrestamoLibro;
import pe.edu.idat.gestion.app.ms_bibliotecas.Mapper.PrestamoLibroMapper;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.LibroRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.PrestamoLibroRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.PrestamoRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrestamoLibroServiceImpl implements PrestamoLibroService {

    private final PrestamoLibroRepository prestamoLibroRepository;
    private final PrestamoLibroMapper prestamoLibroMapper;
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;


    @Override
    @Transactional
    public PrestamoLibroResponseDto update(Long idPrestamoLibro,
                                           PrestamoLibroRequestDto dto) {

        PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(idPrestamoLibro)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        Libro libro = prestamoLibro.getLibro();

        int cantidadAnterior = prestamoLibro.getCantidad();
        int nuevaCantidad = dto.getCantidad();

        int diferencia = nuevaCantidad - cantidadAnterior;

        if (diferencia > 0 && libro.getCantidadDisponible() < diferencia) {
            throw new RuntimeException("Stock insuficiente para: " + libro.getTitulo());
        }

        libro.setCantidadDisponible(libro.getCantidadDisponible() - diferencia);

        prestamoLibro.setCantidad(nuevaCantidad);

        return prestamoLibroMapper.toResponse(prestamoLibroRepository.save(prestamoLibro));
    }

    @Override
    public PrestamoLibroResponseDto findById(Long idPrestamoLibro) {

        PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(idPrestamoLibro)
                .orElseThrow(() -> new RuntimeException("Error al buscar el préstamo de libro"));

        return prestamoLibroMapper.toResponse(prestamoLibro);
    }

    @Override
    public List<PrestamoLibroResponseDto> findAll() {

        return prestamoLibroRepository.findAll()
                .stream()
                .map(prestamoLibroMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long idPrestamoLibro) {

        PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(idPrestamoLibro)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        Libro libro = prestamoLibro.getLibro();
        libro.setCantidadDisponible(
                libro.getCantidadDisponible() + prestamoLibro.getCantidad()
        );
        prestamoLibroRepository.delete(prestamoLibro);
        log.info("Detalle eliminado y stock restaurado: {}", idPrestamoLibro);
    }}