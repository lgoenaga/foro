package com.alura.foro.dto.response;

import com.alura.foro.model.Discusion;

public record DtoListarDiscucion(

        Long id,
        String mensaje,
        Long idTopico,
        Long idUsuario

) {
    public DtoListarDiscucion(Discusion discusion) {
        this(
                discusion.getId(),
                discusion.getMensaje(),
                discusion.getIdTopico(),
                discusion.getIdUsuario()
        );
    }


}
