package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(description = "Clase (record) que permite registrar un nuevo usuario")
public record RegisterRequest(
        @NotNull(message = "El username es obligatorio")
        @Size(min = 8, max = 20, message = "El usuario debe tener como mínimo 8 y 20 como máximo de caracteres")
        @Schema(example = "jperez")
        String username,

        @NotNull(message = "El password es obligatorio")
        @Size(min = 6, max = 20, message = "El password debe tener como mínimo 6 y 10 como máximo de caracteres")
        @Schema(example = "123456")
        String password,

        @NotNull(message = "El ID de la persona es obligatorio") // Agregamos validación
        @Schema(description = "ID de la persona previamente registrada", example = "1")
        Long idPersona,

        String email,

        @Schema(description = "Roles: ADMIN | USER ")
        Set<String> roles
) {

}
