package com.alura.foro.dto.response;

import com.alura.foro.model.Curso;

public record DtoListarCurso(

        Long id,
        String nombre,
        String descripcion

) {

    public DtoListarCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion()
        );
    }
}
