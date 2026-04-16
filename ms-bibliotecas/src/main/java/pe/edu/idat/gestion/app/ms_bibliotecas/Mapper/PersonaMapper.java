package pe.edu.idat.gestion.app.ms_bibliotecas.Mapper;

import org.mapstruct.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PersonaRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Persona;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Prestamo;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaResponseDto toResponse(Persona persona);
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Persona toEntity(PersonaRequestDto personaRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateFromRequest(PersonaRequestDto personaRequestDto, @MappingTarget Persona persona);

    List<PersonaResponseDto> toResponseList(List<Persona> personas);
}