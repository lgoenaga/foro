package com.alura.foro.controller;

import com.alura.foro.dto.response.DtoListarCurso;
import com.alura.foro.service.implement.CursoService;
import com.alura.foro.util.ConstantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    final CursoService cursoService;

    String message;
    Logger logger  = Logger.getLogger(CursoController.class.getName());

    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarCurso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarCurso curso = cursoService.buscarCurso(id);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }

}
