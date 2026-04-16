package pe.edu.idat.gestion.app.ms_bibliotecas.Mapper;

import org.mapstruct.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoLibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoLibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.PrestamoLibro;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoLibroMapper {
    @Mapping(target = "tituloLibro", source = "libro.titulo")
    @Mapping(target = "idPrestamo", source = "prestamo.idPrestamo")
    @Mapping(target = "idLibro", source = "libro.idLibro")
    PrestamoLibroResponseDto toResponse(PrestamoLibro prestamoLibro);

    @Mapping(target = "idPrestamoLibro", ignore = true)
    @Mapping(target = "prestamo", ignore = true)
    @Mapping(target = "libro", ignore = true)
    PrestamoLibro toEntity(PrestamoLibroRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idPrestamoLibro", ignore = true)
    @Mapping(target = "prestamo", ignore = true)
    @Mapping(target = "libro", ignore = true) // Evita que se intente mapear el objeto Libro completo
    void updateFromRequest(PrestamoLibroRequestDto dto, @MappingTarget PrestamoLibro entity);

    List<PrestamoLibroResponseDto> toResponseList(List<PrestamoLibro> list);
}