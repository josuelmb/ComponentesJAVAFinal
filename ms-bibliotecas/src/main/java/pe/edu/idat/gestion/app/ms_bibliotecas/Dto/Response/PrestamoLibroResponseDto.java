package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response;

public record PrestamoLibroResponseDto(
         String tituloLibro,
        Long idPrestamoLibro,
        Long idPrestamo,
        Long idLibro,
        Integer cantidad
){
}
