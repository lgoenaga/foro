package com.alura.foro.dto.response;

import com.alura.foro.model.Usuario;

public record DtoListarUsuario(

        Long id,
        String nombre,
        String username
) {

        public DtoListarUsuario(Usuario usuario) {
                this(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getUsername()
                );
        }
}
