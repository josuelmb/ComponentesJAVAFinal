package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Informacion del Login con el token JWT")
public record LoginResponse(

        @Schema(description = "Token JWT Bearer")
        String token,

        @Schema(description = "Bearer")
        String tipo,

        @Schema(description = "jperez")
        String username,

        @Schema(description = "admin, user")
        Set<String> roles,

        @Schema(description = "Tiempo de expiración en milisegundos", example = "86400000")
        long expiracion

) {

        public static LoginResponse of(String token, String username, String tipo, Set<String> roles, long expiracion) {
                return new LoginResponse(token,tipo,username,roles,expiracion);
        }
}
