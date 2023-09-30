package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarTopico;
import com.alura.foro.dto.request.crear.DtoCrearTopico;
import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.service.implement.TopicoService;
import com.alura.foro.service.implement.UsuarioService;
import com.alura.foro.util.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
@Tag(name = ConstantService.MODEL_TOPIC, description = "API " + ConstantService.MODEL_TOPIC)
public class TopicoController {

    final TopicoService topicoService;
    final UsuarioService usuarioService;

    @Operation(
            summary = ConstantService.LIST + " " +  ConstantService.TOPICOS,
            description = ConstantService.LIST + " " +  ConstantService.TOPICOS,
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "200", description = ConstantService.TOPICOS + " encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
            List<DtoListarTopico> topicos = topicoService.listarTopicos();
            return ResponseEntity.ok().body(topicos);
    }

    @Operation(
            summary = ConstantService.LIST + " " + ConstantService.MODEL_TOPIC + " por id",
            description = ConstantService.LIST + " " + ConstantService.MODEL_TOPIC + " por id",
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
            DtoListarTopico topico = topicoService.listarTopico(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

    @Operation(
            summary = ConstantService.LIST + " " + ConstantService.TOPICOS + " por estado",
            description = ConstantService.LIST + " todos los " + ConstantService.TOPICOS + " por estado",
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.TOPICOS + " encontrados")
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<Object> showByEstatus(@PathVariable("estatus") String estatus) {
            List<DtoListarTopico> topicos = topicoService.listarTopicosPorEstatus(estatus);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicos);
    }

    @Operation(
            summary = ConstantService.CREATE + " " + ConstantService.MODEL_TOPIC,
            description = ConstantService.CREATE + " " + ConstantService.MODEL_TOPIC,
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "201", description = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_CREATED)
    @ApiResponse(responseCode = "403", description = "Usuario no autorizado")
    @PostMapping
    @PreAuthorize(ConstantService.ADMIN_USER_SEC)
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearTopico topico) {
            topicoService.registrarTopico(topico);
            return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @Operation(
            summary = ConstantService.UPDATE + " " + ConstantService.MODEL_TOPIC,
            description = ConstantService.UPDATE + " " + ConstantService.MODEL_TOPIC + " por id",
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_UPDATED)
    @PutMapping("/{id}")
    @PreAuthorize(ConstantService.ADMIN_USER_SEC)
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarTopico topico) {
            topicoService.actualizarTopico(id, topico);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

    @Operation(
            summary = ConstantService.DELETE + " " + ConstantService.MODEL_TOPIC,
            description = ConstantService.DELETE + " " + ConstantService.MODEL_TOPIC + " por id",
            tags = { ConstantService.TOPICOS }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_DELETED)
    @DeleteMapping("/{id}")
    @PreAuthorize(ConstantService.ADMIN_SEC)
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
            String message;
            topicoService.eliminarTopico(id);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_DELETED;
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
