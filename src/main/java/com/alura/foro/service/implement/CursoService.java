package com.alura.foro.service.implement;

import com.alura.foro.dto.response.DtoListarCurso;
import com.alura.foro.model.Curso;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.service.interfaces.CursoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService implements CursoInterface {

    final CursoRepository cursoRepository;

    @Override
    public List<DtoListarCurso> listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(DtoListarCurso::new).toList();
    }

    @Override
    public DtoListarCurso buscarCurso(Long id) {
            Curso curso = cursoRepository.findById(id).orElseThrow();
            return new DtoListarCurso(curso);
    }
}
