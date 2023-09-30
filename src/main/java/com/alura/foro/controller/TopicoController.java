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
import java.util.logging.Logger;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
@Tag(name = "Topico", description = "API Topico")
public class TopicoController {

    final TopicoService topicoService;
    final UsuarioService usuarioService;
    String message;
    Logger logger  = Logger.getLogger(TopicoController.class.getName());

    @Operation(
            summary = "Listar topicos",
            description = "Listar todos los topicos",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "200", description = "Topicos encontrados")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> index() {
            List<DtoListarTopico> topicos = topicoService.listarTopicos();
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.ok().body(topicos);
    }

    @Operation(
            summary = "Lista topico por id",
            description = "Listar topico por id",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "202", description = "Topico encontrado")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
            DtoListarTopico topico = topicoService.listarTopico(id);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

    @Operation(
            summary = "Listar topicos por estado",
            description = "Listar todos los topicos por estado",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "202", description = "Topicos encontrados")
    @GetMapping("/estatus/{estatus}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> showByEstatus(@PathVariable("estatus") String estatus) {
            List<DtoListarTopico> topicos = topicoService.listarTopicosPorEstatus(estatus);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicos);
    }

    @Operation(
            summary = "Registrar topico",
            description = "Registrar topico",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "201", description = "Topico registrado")
    @ApiResponse(responseCode = "403", description = "Usuario no autorizado")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearTopico topico) {
            topicoService.registrarTopico(topico);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_CREATED;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @Operation(
            summary = "Actualizar topico",
            description = "Actualizar topico por id",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "202", description = "Topico actualizado")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarTopico topico) {
            topicoService.actualizarTopico(id, topico);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_UPDATED;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

    @Operation(
            summary = "Eliminar topico",
            description = "Eliminar topico por id",
            tags = { "Topicos" }
    )
    @ApiResponse(responseCode = "202", description = "Topico eliminado")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
            topicoService.eliminarTopico(id);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_DELETED;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
