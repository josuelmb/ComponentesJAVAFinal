package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PersonaRequestDto {
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no deben superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Los apellidos no deben superar los 100 caracteres")
    private String apellidos;

    @Size(max = 30, message = "El código universitario no debe superar los 30 caracteres")
    private String codigoUniversitario;

    @Size(max = 11, message = "El teléfono no debe superar los 11 caracteres")
    private String telefono;

}
