package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.LoginRequest;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.LoginResponse;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Auth.RegisterRequest;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Persona;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Rol;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Usuario;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.PersonaRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.RolRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.UsuarioRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Security.JwtService;
import pe.edu.idat.gestion.app.ms_bibliotecas.Security.UserDetailsImpl;
import pe.edu.idat.gestion.app.ms_bibliotecas.Security.UserDetailsServiceImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class AuthServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PersonaRepository  personaRepository;

    @Transactional
    public UsuarioResponseDto register(RegisterRequest registerRequest) {

        Persona persona = personaRepository.findById(registerRequest.idPersona())
                .orElseThrow(() -> new RuntimeException("Error: La persona con ID " + registerRequest.idPersona() + " no existe."));

        // Verificar si el usuario existe
        if(usuarioRepository.existsByUsername(registerRequest.username())) {
            throw new RuntimeException("El nombre de usuario '" + registerRequest.username() + "' ya está en uso.");
        }

        Set<Rol> roles = definirRoles(registerRequest.roles());

        Usuario usuario = Usuario.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .email(registerRequest.email())
                .persona(persona)
                .roles(roles)
                .estado(1)
                .build();

        Usuario usuarioSave = usuarioRepository.save(usuario);
        log.info("Usuario registrado correctamente: {}", usuarioSave.getIdUsuario());

        return toUsuarioResponse(usuarioSave);
    }
    private Set<Rol> definirRoles(Set<String> roles) {
        Set<Rol> roleSet = new HashSet<>();
        if(roles == null || roles.isEmpty()) {
            roleSet.add(rolRepository.findByNombre("USER")
                    .orElseThrow(() -> new IllegalArgumentException("Rol USER no encontrado")));
        } else {
            for(String rolName : roles) {
                Rol r = rolRepository.findByNombre(rolName.toUpperCase())
                        .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
                roleSet.add(r);
            }
        }
        return roleSet;
    }

    private UsuarioResponseDto toUsuarioResponse(Usuario usuario) {
        Set<String> roles = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());
        Persona persona = usuario.getPersona();

        PersonaResponseDto personaDto = new PersonaResponseDto(
                persona.getIdPersona(),
                persona.getNombres(),
                persona.getApellidos(),
                persona.getCodigoUniversitario(),
                persona.getTelefono(),
                persona.getEstado()
        );
        return new UsuarioResponseDto(
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getEstado(),
                roles,
                personaDto
        );
    }

    // Login de autenticación
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        ));

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.username());

        Set<String> roles = userDetails.getUsuario().getRoles()
                .stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());

        log.info("Login exitoso del usuario: {} con roles {}", loginRequest.username(), roles);
        String token = jwtService.generateToken(userDetails);

        return LoginResponse.of(
                token,
                "Bearer",
                loginRequest.username(),
                roles,
                jwtService.getExpirationms()
        );
    }
}

