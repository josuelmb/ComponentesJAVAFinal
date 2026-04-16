package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.PersonaResponseDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.request.PersonaRequestDto;
import pe.edu.idat.gestion.app.ms_bibliotecas.Entity.Persona;
import pe.edu.idat.gestion.app.ms_bibliotecas.Mapper.PersonaMapper;
import pe.edu.idat.gestion.app.ms_bibliotecas.Repository.PersonaRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Transactional(readOnly = true)
    @Override
    public PersonaResponseDto findById(Long idPersona) {

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("No existe la persona"));

        return personaMapper.toResponse(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponseDto> findAll() {

        return personaMapper.toResponseList(personaRepository.findAll());
    }

    @Override
    @Transactional
    public PersonaResponseDto create(PersonaRequestDto request) {

        log.info("Create Persona: {}", request.getNombres());

        Persona persona = personaMapper.toEntity(request);

        if (persona.getEstado() == null) {
            persona.setEstado(1);
        }

        return personaMapper.toResponse(
                personaRepository.save(persona)
        );
    }

    @Override
    @Transactional
    public PersonaResponseDto update(Long idPersona,
                                     PersonaRequestDto request) {

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException(
                        "No existe la persona con id: " + idPersona));

        log.info("Update Persona: {}", idPersona);

        personaMapper.updateFromRequest(request, persona);

        return personaMapper.toResponse(
                personaRepository.save(persona)
        );
    }

    @Override
    @Transactional
    public void delete(Long idPersona) {

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException(
                        "No existe la persona con id: " + idPersona));

        personaRepository.delete(persona);

        log.info("Persona eliminada con id: {}", idPersona);
    }
}