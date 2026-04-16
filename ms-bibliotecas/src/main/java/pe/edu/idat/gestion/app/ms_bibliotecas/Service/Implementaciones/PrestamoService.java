package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoRequestDto;

import java.util.List;

public interface PrestamoService {
    PrestamoResponseDto create(PrestamoRequestDto prestamoRequestDto);

    PrestamoResponseDto update(Long idPrestamo, PrestamoRequestDto prestamoRequestDto);

    PrestamoResponseDto findById(Long idPrestamo);

    List<PrestamoResponseDto> findAll();

    void delete(Long idPrestamo);

    List<PrestamoResponseDto> findByUsuario(Long idUsuario);

    PrestamoResponseDto registrarDevolucion(Long idPrestamo);

    List<PrestamoResponseDto> findByUsername(String username);
}
