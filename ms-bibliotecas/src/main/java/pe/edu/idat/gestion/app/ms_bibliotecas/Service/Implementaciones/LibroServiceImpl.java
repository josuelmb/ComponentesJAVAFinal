package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

//Generamos todos los service

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.LibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.LibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Libro;
import pe.edu.idat.gestion.app.ms_bibliotecas.Mapper.LibroMapper;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.LibroRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroMapper libroMapper;
    private final LibroRepository libroRepository;

    @Override
    public LibroResponseDto findById(long idLibro) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException("Error al buscar el libro"));

        return libroMapper.toResponse(libro);
    }

    @Override
    public List<LibroResponseDto> findAll() {

        return libroRepository.findAll()
                .stream()
                .map(libroMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public LibroResponseDto create(LibroRequestDto libroRequestDto) {
        log.info("Create Libro: {}", libroRequestDto);

        // REGLA DE NEGOCIO: Validar si ya existe un libro con ese título
        if (libroRepository.existsByTitulo(libroRequestDto.getTitulo())) {
            throw new IllegalArgumentException("Ya existe un libro registrado con ese título");
        }

        Libro libro = libroMapper.toEntity(libroRequestDto);
        return libroMapper.toResponse(libroRepository.save(libro));
    }
    @Override
    @Transactional
    public LibroResponseDto update(Long idLibro, LibroRequestDto libroRequestDto) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException("Error al buscar el libro"));

        log.info("Update Libro: {}", libroRequestDto);

        libroMapper.updateFromRequest(libroRequestDto, libro);

        return libroMapper.toResponse(libroRepository.save(libro));
    }

    @Override
    @Transactional
    public void delete(Long idLibro) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el id del libro"));

        libroRepository.delete(libro);

        log.info("Libro eliminado con id: {}", idLibro);
    }
}