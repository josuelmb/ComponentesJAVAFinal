package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroRequestDto {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 30, message = "El título no debe superar los 30 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 30, message = "El autor no debe superar los 30 caracteres")
    private String autor;

    @NotBlank(message = "La editorial es obligatorio")
    @Size(max = 20, message = "La editorial no debe superar los 20 caracteres")
    private String editorial;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 20, message = "La categoría no debe superar los 20 caracteres")
    private String categoria;

    @NotBlank(message = "El idioma es obligatorio")
    @Size(max = 20, message = "El idioma no debe superar los 20 caracteres")
    private String idioma;

    @NotNull(message = "La cantidad total es obligatoria")
    @Min(value = 1, message = "La cantidad total debe ser mayor a 0")
    private Integer cantidadTotal;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value = 0, message = "La cantidad disponible no puede ser negativa")
    private Integer cantidadDisponible;

}
