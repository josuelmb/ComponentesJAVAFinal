package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoLibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoLibroRequestDto;

import java.util.List;

public interface PrestamoLibroService {

    PrestamoLibroResponseDto update(Long idPrestamoLibro,
                                    PrestamoLibroRequestDto prestamoLibroRequestDto);

    PrestamoLibroResponseDto findById(Long idPrestamoLibro);

    List<PrestamoLibroResponseDto> findAll();

    void delete(Long idPrestamoLibro);

}
