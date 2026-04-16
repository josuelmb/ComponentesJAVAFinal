
package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Persona;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Rol;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Usuario;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.UsuarioRepository;
import pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones.UsuarioService;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toUsuarioResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return toUsuarioResponse(usuario);
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
    }}