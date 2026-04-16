package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;
@Tag(name = "Usuarios", description = "Operaciones sobre detalles de Usuarios")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Operation(summary = "Obtener detalle de Usuarios")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UsuarioResponseDto>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.<List<UsuarioResponseDto>>builder()
                        .success(true)
                        .message("Lista de usuarios obtenida")
                        .data(usuarioService.findAll())
                        .timestamp(LocalDateTime.parse(LocalDateTime.now().toString()))
                        .build()
        );
    }
    @Operation(summary = "Obtener detalle de Usuarios por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> findById(@PathVariable Long id) {
        UsuarioResponseDto usuario = usuarioService.findById(id);

        return ResponseEntity.ok(
                ApiResponse.<UsuarioResponseDto>builder()
                        .success(true)
                        .message("Usuario encontrado")
                        .data(usuario)
                        .timestamp(LocalDateTime.parse(LocalDateTime.now().toString()))
                        .build()
        );
    }}