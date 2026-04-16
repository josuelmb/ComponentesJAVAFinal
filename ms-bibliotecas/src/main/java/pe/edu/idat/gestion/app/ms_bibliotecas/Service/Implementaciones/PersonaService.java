package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PersonaRequestDto;

import java.util.List;

public interface PersonaService {
    PersonaResponseDto create (PersonaRequestDto personaRequestDto);
    PersonaResponseDto update (Long idUsuario , PersonaRequestDto personaRequestDto);
    void delete(Long idUsuario);
    List<PersonaResponseDto> findAll();
    PersonaResponseDto findById(Long idUsuario);
}
