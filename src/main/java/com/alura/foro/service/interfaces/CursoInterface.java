package com.alura.foro.service.interfaces;

import com.alura.foro.dto.response.DtoListarCurso;

import java.util.List;

public interface CursoInterface {

    List<DtoListarCurso> listarCursos();

    DtoListarCurso buscarCurso(Long id);
}
