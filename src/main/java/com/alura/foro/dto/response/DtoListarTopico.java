package com.alura.foro.dto.response;

import com.alura.foro.model.Topico;

public record DtoListarTopico(

        Long id,
        String titulo,
        String descripcion,

        Long idCurso,

        Long idUsuario

    ) {

    public DtoListarTopico(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getDescripcion(),
            topico.getIdCurso(),
            topico.getIdUsuario()
        );
    }
}
