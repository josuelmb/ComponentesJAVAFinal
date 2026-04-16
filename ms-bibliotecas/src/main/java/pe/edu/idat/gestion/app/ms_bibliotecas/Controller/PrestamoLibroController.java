package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoLibroResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoLibroRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.PrestamoLibroService;

import java.util.List;
@Tag(name = "Prestamo Libro", description = "Operaciones sobre detalle de préstamos")
@RestController
@RequestMapping("/prestamos-libros")
@RequiredArgsConstructor

public class PrestamoLibroController {

    private final PrestamoLibroService prestamoLibroService;
    @Operation(summary = "Obtener detalle de Prestamos Libros")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PrestamoLibroResponseDto>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.ok(prestamoLibroService.findAll())
        );
    }
    @Operation(summary = "Obtener detalle de Prestamos Libros por ID")
    @GetMapping("/{idPrestamoLibro}")
    public ResponseEntity<ApiResponse<PrestamoLibroResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok(prestamoLibroService.findById(id))
        );
    }

    @Operation(summary = "Actualizar Prestamos Libros por ID")
    @PutMapping("/{idPrestamoLibro}")
    public ResponseEntity<ApiResponse<PrestamoLibroResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoLibroRequestDto request) {

        return ResponseEntity.ok(
                ApiResponse.<PrestamoLibroResponseDto>ok(
                        "Detalle de préstamo actualizado",
                        prestamoLibroService.update(id, request)));
    }
    @Operation(summary = "Elminar Prestamos Libros por ID")
    @DeleteMapping("/{idPrestamoLibro}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        prestamoLibroService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Detalle de préstamo eliminado correctamente", null)
        );
    }
}