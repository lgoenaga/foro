package com.alura.foro.service.interfaces;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioInterface {

    List<DtoListarUsuario> listarUsuarios();
    DtoListarUsuario listarUsuario(Long id);
    List<DtoListarUsuario> listarUsuarios(String estado);
    void registrarUsuario(DtoCrearUsuario userDtoRequest);

    void actualizarUsuario(Long id, DtoActualizarUsuario userDtoRequest);

    void eliminarUsuario(Long id);


    DtoListarUsuario buscarUsuario(String username);
}
