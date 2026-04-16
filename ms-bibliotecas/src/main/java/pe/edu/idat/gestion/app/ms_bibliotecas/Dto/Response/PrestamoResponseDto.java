package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.naming.InterruptedNamingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record PrestamoResponseDto (
        Long idPrestamo,
        Long idUsuario,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Lima")
        LocalDateTime fechaPrestamo,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Lima")
        LocalDateTime fechaLimite,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Lima")
        LocalDateTime fechaDevolucion,

        String observaciones,
        Integer estado,
        List<PrestamoLibroResponseDto> detalles
) {

}
