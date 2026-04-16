package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PrestamoResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PrestamoRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
@RequiredArgsConstructor
@Tag(name = "Prestamos", description = "Operaciones sobre detalle de préstamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    @Operation(summary = "Obtener detalle de Prestamos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PrestamoResponseDto>>> findAll() {

        return ResponseEntity.ok(
                ApiResponse.ok(prestamoService.findAll())
        );
    }
    @Operation(summary = "Obtener detalle de Prestamo por ID")
    @GetMapping("/{idPrestamo}")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> findById(
            @PathVariable Long idPrestamo) {

        return ResponseEntity.ok(
                ApiResponse.ok(prestamoService.findById(idPrestamo))
        );
    }
    @Operation(summary = "Obtener detalle de Prestamo por Usuario")
    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<ApiResponse<List<PrestamoResponseDto>>> findByUsuario(
            @PathVariable Long idUsuario) {

        return ResponseEntity.ok(
                ApiResponse.ok(prestamoService.findByUsuario(idUsuario))
        );
    }
    @Operation(summary = "Crear Prestamos")
    @PostMapping
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> create(
            @Valid @RequestBody PrestamoRequestDto prestamoRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.ok(
                                "Se ha creado el préstamo",
                                prestamoService.create(prestamoRequestDto)
                        )
                );
    }
    @Operation(summary = "Actualizar Prestamo por ID")
    @PutMapping("/{idPrestamo}")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> update(
            @PathVariable Long idPrestamo,
            @Valid @RequestBody PrestamoRequestDto prestamoRequestDto) {

        return ResponseEntity.ok(
                ApiResponse.ok(
                        "Se ha actualizado el préstamo",
                        prestamoService.update(idPrestamo, prestamoRequestDto)
                )
        );
    }
    @Operation(summary = "Actualizar devolucion por ID")
    @PatchMapping("/{idPrestamo}/devolucion")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> registrarDevolucion(@PathVariable Long idPrestamo) {
        PrestamoResponseDto response = prestamoService.registrarDevolucion(idPrestamo);

        return ResponseEntity.ok(
                ApiResponse.ok("Devolución registrada exitosamente", response)
        );
    }

    @Operation(summary = "Eliminar informacion de Prestamo por ID")
    @DeleteMapping("/{idPrestamo}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long idPrestamo) {

        prestamoService.delete(idPrestamo);

        return ResponseEntity.ok(
                ApiResponse.ok("Se ha eliminado el préstamo", null)
        );
    }
    @Operation(summary = "Obtener informacion de Prestamo personal")
    @GetMapping("/mis-prestamos")
    public ResponseEntity<ApiResponse<List<PrestamoResponseDto>>> misPrestamos() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                ApiResponse.ok(prestamoService.findByUsername(username))
        );
    }
}