package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarCurso;
import com.alura.foro.dto.request.crear.DtoCrearCurso;
import com.alura.foro.dto.response.DtoListarCurso;
import com.alura.foro.service.implement.CursoService;
import com.alura.foro.util.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@Tag(name = ConstantService.MODEL_COURSE, description = "API " + ConstantService.MODEL_COURSE)
public class CursoController {

    final CursoService cursoService;

    @Operation(
            summary = ConstantService.LIST  + ConstantService.MODEL_COURSE + "s",
            description = ConstantService.LIST + ConstantService.MODEL_COURSE + "s",
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "200", description = "Cursos encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarCurso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @Operation(
            summary = ConstantService.LIST + " "  + ConstantService.MODEL_COURSE + "s por estado",
            description = ConstantService.LIST + " " + ConstantService.MODEL_COURSE + "s por estado",
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "200", description = "Cursos por estado encontrados")
    @GetMapping("/estados/{estado}")
    public ResponseEntity<Object> index(@PathVariable("estado") String estado) {
        List<DtoListarCurso> cursos = cursoService.listarCursos(estado);
        return ResponseEntity.ok().body(cursos);
    }

    @Operation(
            summary = ConstantService.FOUND + ConstantService.MODEL_COURSE + " por id",
            description = ConstantService.FOUND + ConstantService.MODEL_COURSE + " por id",
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarCurso curso = cursoService.buscarCurso(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }
    @Operation(
            summary = ConstantService.CREATE + ConstantService.MODEL_COURSE,
            description = ConstantService.CREATE + ConstantService.MODEL_COURSE,
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "201", description = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_CREATED)
    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearCurso curso) {
        cursoService.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @Operation(
            summary = ConstantService.CREATE + " " + ConstantService.MODEL_COURSE,
            description = ConstantService.CREATE + " " + ConstantService.MODEL_COURSE,
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_UPDATED)
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarCurso curso) {
        cursoService.actualizarCurso(id, curso);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }

    @Operation(
            summary = ConstantService.DELETE + " " + ConstantService.MODEL_COURSE,
            description = ConstantService.DELETE + " " + ConstantService.MODEL_COURSE,
            tags = { ConstantService.MODEL_COURSE }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
        String message;
        cursoService.eliminarCurso(id);
        message = ConstantService.MODEL_COURSE + " " + ConstantService.INFO_DELETED;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
