package pe.edu.idat.gestion.app.ms_bibliotecas.Service.Implementaciones;


import pe.edu.idat.gestion.app.ms_bibliotecas.Dto.Response.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDto> findAll();
    UsuarioResponseDto findById(Long id);

}