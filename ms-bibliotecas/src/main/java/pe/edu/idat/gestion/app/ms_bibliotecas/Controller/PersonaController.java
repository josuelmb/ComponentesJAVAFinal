package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PersonaRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
@Tag(name = "Personas", description = "Operaciones sobre detalle de personas")
public class PersonaController {

    private final PersonaService personaService;
    @Operation(summary = "Obtener detalle de Personas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonaResponseDto>>> findAll() {

        return ResponseEntity.ok(
                ApiResponse.ok(personaService.findAll())
        );
    }
    @Operation(summary = "Obtener detalle de Personas")
    @GetMapping("/{idPersona}")
    public ResponseEntity<ApiResponse<PersonaResponseDto>> findById(
            @PathVariable Long idPersona) {

        return ResponseEntity.ok(
                ApiResponse.ok(personaService.findById(idPersona))
        );
    }
    @Operation(summary = "Crear Persona")
    @PostMapping
    public ResponseEntity<ApiResponse<PersonaResponseDto>> create(
            @Valid @RequestBody PersonaRequestDto personaRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Se ha creado la persona",
                        personaService.create(personaRequestDto)));
    }
    @Operation(summary = "Actualizar Libro por ID")
    @PutMapping("/{idPersona}")
    public ResponseEntity<ApiResponse<PersonaResponseDto>> update(
            @PathVariable Long idPersona,
            @Valid @RequestBody PersonaRequestDto personaRequestDto) {

        return ResponseEntity.ok(
                ApiResponse.ok("Se ha actualizado la persona",
                        personaService.update(idPersona, personaRequestDto)));
    }

    @Operation(summary = "Eliminar informacion de persona por ID")
    @DeleteMapping("/{idPersona}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long idPersona) {

        personaService.delete(idPersona);

        return ResponseEntity.ok(
                ApiResponse.ok("Se ha eliminado la persona", null)
        );
    }
}