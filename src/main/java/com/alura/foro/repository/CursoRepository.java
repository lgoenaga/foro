package com.alura.foro.repository;

import com.alura.foro.model.Curso;
import com.alura.foro.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByEstado(Estado estado);
}
