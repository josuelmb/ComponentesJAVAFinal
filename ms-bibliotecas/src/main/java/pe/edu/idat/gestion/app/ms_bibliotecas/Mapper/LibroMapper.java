package pe.edu.idat.gestion.app.ms_bibliotecas.Mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.LibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.LibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Libro;

import java.util.List;

//Sirve para viajen los datos hacia la BD y viceversa
//Mapea como entidad y devuelve response
@Mapper(componentModel = "spring")
public interface LibroMapper {

    LibroResponseDto toResponse(Libro libro);

    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idLibro", ignore = true)
    Libro toEntity(LibroRequestDto libroRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idLibro", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateFromRequest(LibroRequestDto libroRequestDto, @MappingTarget Libro libro);

    List<LibroResponseDto> toResponseList(List<Libro> libros);
}