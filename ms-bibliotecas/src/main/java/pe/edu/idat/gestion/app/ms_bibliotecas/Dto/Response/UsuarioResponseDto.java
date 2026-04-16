package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response;

import java.util.Set;

public record UsuarioResponseDto(

        Long idUsuario,
        String username,
        String email,
        Integer estado,
        Set<String> roles,
        PersonaResponseDto persona
) {
}
