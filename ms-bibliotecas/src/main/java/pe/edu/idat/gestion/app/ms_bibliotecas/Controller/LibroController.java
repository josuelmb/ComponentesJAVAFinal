package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.LibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.LibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.LibroService;

import java.util.List;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
@Tag(name = "Libro", description = "Operaciones sobre detalle de Libros")
public class LibroController {

    private final LibroService libroService;
    @Operation(summary = "Obtener detalle de libros")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LibroResponseDto>>> findAll() {

        return ResponseEntity.ok(
                ApiResponse.ok(libroService.findAll())
        );
    }
    @Operation(summary = "Obtener detalle por ID")
    @GetMapping("/{idLibro}")
    public ResponseEntity<ApiResponse<LibroResponseDto>> findById(
            @PathVariable Long idLibro) {

        return ResponseEntity.ok(
                ApiResponse.ok(libroService.findById(idLibro))
        );
    }
    @Operation(summary = "Crear Libro")
    @PostMapping
    public ResponseEntity<ApiResponse<LibroResponseDto>> create(
            @Valid @RequestBody LibroRequestDto libroRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.ok(
                                "Se ha creado el libro",
                                libroService.create(libroRequestDto)
                        )
                );
    }
    @Operation(summary = "Actualizar Libro por ID")
    @PutMapping("/{idLibro}")
    public ResponseEntity<ApiResponse<LibroResponseDto>> update(
            @PathVariable Long idLibro,
            @Valid @RequestBody LibroRequestDto libroRequestDto) {

        return ResponseEntity.ok(
                ApiResponse.ok(
                        "Se ha actualizado el libro",
                        libroService.update(idLibro, libroRequestDto)
                )
        );
    }
    @Operation(summary = "Eliminar libro por ID")
    @DeleteMapping("/{idLibro}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long idLibro) {

        libroService.delete(idLibro);

        return ResponseEntity.ok(
                ApiResponse.ok("Se ha eliminado el libro", null)
        );
    }
}