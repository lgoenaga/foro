package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarCurso;
import com.alura.foro.dto.request.crear.DtoCrearCurso;
import com.alura.foro.dto.response.DtoListarCurso;
import com.alura.foro.service.implement.CursoService;
import com.alura.foro.util.ConstantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/estados/{estado}")
    public ResponseEntity<Object> index(@PathVariable("estado") String estado) {
        List<DtoListarCurso> cursos = cursoService.listarCursos(estado);
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarCurso curso = cursoService.buscarCurso(id);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }
    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearCurso curso) {
        cursoService.crearCurso(curso);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_CREATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarCurso curso) {
        cursoService.actualizarCurso(id, curso);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_UPDATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
        cursoService.eliminarCurso(id);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_DELETED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
