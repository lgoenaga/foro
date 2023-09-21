package com.alura.foro.service.interfaces;

import com.alura.foro.dto.request.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioInterface {

    List<DtoListarUsuario> listarUsuarios();
    DtoListarUsuario listarUsuario(Long id);
    void registrarUsuario(DtoCrearUsuario userDtoRequest);
}
