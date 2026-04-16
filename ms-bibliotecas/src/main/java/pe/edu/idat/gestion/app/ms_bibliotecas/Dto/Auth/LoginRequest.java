package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Credenciales de acceso")
public record LoginRequest (

        @NotBlank(message = "El Usuario es obligatorio")
        @Schema(example = "Admin")
         String username,

        @NotBlank(message = "La contraseña es obligatorio")
        @Schema(example = "xxxxx")
         String password
){}