package com.alura.foro.dto.response;

import com.alura.foro.model.Estado;
import com.alura.foro.model.Role;
import com.alura.foro.model.Usuario;

import java.util.List;

public record DtoListarUsuario(

        Long id,
        String nombre,
        String username,

        Estado estado,
        List <Role> roles
) {

        public DtoListarUsuario(Usuario usuario) {

                this(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getUsername(),
                        usuario.getEstado(),
                        usuario.getRoles()
                );
        }
}
