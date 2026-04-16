package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response;

public record PersonaResponseDto(
        Long idPersona,
        String nombres,
        String apellidos,
        String codigoUniversitario,
        String telefono,
        Integer estado

) {}