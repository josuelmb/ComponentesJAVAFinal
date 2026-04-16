package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.LibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.LibroRequestDto;

import java.util.List;

public interface LibroService {
    LibroResponseDto findById(long idLibro);
    List<LibroResponseDto> findAll();
    LibroResponseDto create(LibroRequestDto libroRequestDto);
    LibroResponseDto update(Long idLibro, LibroRequestDto libroRequestDto);
    void delete(Long idLibro);

}
