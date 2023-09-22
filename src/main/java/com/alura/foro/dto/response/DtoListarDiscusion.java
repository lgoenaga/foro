package com.alura.foro.dto.response;

import com.alura.foro.model.Discusion;

public record DtoListarDiscusion(

        Long id,
        String mensaje,
        Long idTopico,
        Long idUsuario

) {
    public DtoListarDiscusion(Discusion discusion) {
        this(
                discusion.getId(),
                discusion.getMensaje(),
                discusion.getIdTopico(),
                discusion.getIdUsuario()
        );
    }


}
