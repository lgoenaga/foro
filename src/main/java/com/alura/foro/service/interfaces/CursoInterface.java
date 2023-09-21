package com.alura.foro.service.interfaces;

import com.alura.foro.dto.request.actualizar.DtoActualizarCurso;
import com.alura.foro.dto.request.crear.DtoCrearCurso;
import com.alura.foro.dto.response.DtoListarCurso;

import java.util.List;

public interface CursoInterface {

    List<DtoListarCurso> listarCursos();

    DtoListarCurso buscarCurso(Long id);

    List<DtoListarCurso> listarCursos(String estado);

    void crearCurso(DtoCrearCurso curso);

    void actualizarCurso(Long id, DtoActualizarCurso curso);

    void eliminarCurso(Long id);
}
