package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoRequestDto {

    @NotNull(message = "El id del Usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "La fecha límite es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Lima")
    private LocalDateTime fechaLimite;

    @Size(max = 50, message = "Las observaciones no deben superar los 50 caracteres")
    private String observaciones;

    private List<PrestamoLibroRequestDto> libros;
}
