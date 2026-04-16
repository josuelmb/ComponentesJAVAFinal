package pe.edu.idat.gestion.app.ms_bibliotecas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.LoginRequest;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.LoginResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.RegisterRequest;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.ApiResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.AuthServiceImpl;

@Slf4j
    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/auth")
    @Tag(name = "Registro y Login", description = "Operaciones sobre detalle de Login y Register")
    public class AuthController
    {
        private final AuthServiceImpl authService;
        @Operation(summary = "Ingresar con Login")
        @PostMapping("/login")
        public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest)
        {
            return ResponseEntity.ok(ApiResponse.ok(authService.login(loginRequest)));
        }
        @Operation(summary = "Registrarse")
        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UsuarioResponseDto>> register(@Valid @RequestBody RegisterRequest registerRequest)
        {
            return ResponseEntity.ok(ApiResponse.ok("Usuario registrado exitosamente", authService.register(registerRequest)));
        }

    }