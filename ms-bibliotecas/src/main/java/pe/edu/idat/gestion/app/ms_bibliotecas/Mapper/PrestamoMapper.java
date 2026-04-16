package pe.edu.idat.gestion.app.ms_bibliotecas.Mapper;

import org.mapstruct.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Prestamo;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PrestamoLibroMapper.class })
public interface PrestamoMapper {

    @Mapping(target = "idUsuario", source = "usuario.idUsuario")
    @Mapping(target = "fechaPrestamo", source = "fechaPrestamo")
    @Mapping(target = "fechaDevolucion", source = "fechaDevolucion")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "detalles", source = "detalles")
    PrestamoResponseDto toResponse(Prestamo prestamo);

    @Mapping(target = "idPrestamo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    @Mapping(target = "fechaPrestamo", ignore = true)
    @Mapping(target = "fechaDevolucion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Prestamo toEntity(PrestamoRequestDto dto);
}