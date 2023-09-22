package com.alura.foro.service.implement;

import com.alura.foro.dto.request.actualizar.DtoActualizarCurso;
import com.alura.foro.dto.request.crear.DtoCrearCurso;
import com.alura.foro.dto.response.DtoListarCurso;
import com.alura.foro.model.Curso;
import com.alura.foro.model.Estado;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.service.interfaces.CursoInterface;
import jakarta.transaction.Transactional;
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

    @Override
    public List<DtoListarCurso> listarCursos(String estado) {
        List<Curso> cursos = cursoRepository.findByEstado(Estado.valueOf(estado));
        return cursos.stream().map(DtoListarCurso::new).toList();
    }

    @Override
    @Transactional
    public void crearCurso(DtoCrearCurso curso) {
        Curso cursoNuevo = new Curso();
        cursoNuevo.setNombre(curso.nombre());
        cursoNuevo.setDescripcion(curso.descripcion());
        cursoNuevo.setEstado(Estado.ACTIVO);
        cursoRepository.save(cursoNuevo);
    }

    @Override
    @Transactional
    public void actualizarCurso(Long id, DtoActualizarCurso curso) {
        Curso cursoActualizado = cursoRepository.findById(id).orElseThrow();
        cursoActualizado.setNombre(curso.nombre());
        cursoActualizado.setDescripcion(curso.descripcion());
        if (curso.estado()==null) {
            cursoActualizado.setEstado(cursoActualizado.getEstado());
        }else{
            cursoActualizado.setEstado(curso.estado());
        }
    }
    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        cursoRepository.findById(id).orElseThrow();
        cursoRepository.deleteById(id);
    }

}
