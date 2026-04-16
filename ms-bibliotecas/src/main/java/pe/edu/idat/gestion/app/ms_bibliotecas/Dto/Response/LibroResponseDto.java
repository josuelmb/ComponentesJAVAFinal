package pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response;

public record LibroResponseDto (
        Long idLibro,
        String titulo,
        String autor,
        String editorial,
        String categoria,
        String idioma,
        Integer cantidadTotal,
        Integer cantidadDisponible
)
{
//Tener un objeto immutable, para poder extraer
}
