package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoLibroRequestDto {
    @NotNull(message = "El id del préstamo es obligatorio")
    private Long idPrestamo;

    @NotNull(message = "El id del libro es obligatorio")
    private Long idLibro;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;
}
