package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarTopico;
import com.alura.foro.dto.request.crear.DtoCrearTopico;
import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.service.implement.TopicoService;
import com.alura.foro.service.implement.UsuarioService;
import com.alura.foro.util.ConstantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    final TopicoService topicoService;
    final UsuarioService usuarioService;
    String message;
    Logger logger  = Logger.getLogger(TopicoController.class.getName());

    @GetMapping
    public ResponseEntity<Object> index() {
            List<DtoListarTopico> topicos = topicoService.listarTopicos();
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.ok().body(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
            DtoListarTopico topico = topicoService.listarTopico(id);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearTopico topico) {
            topicoService.registrarTopico(topico);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_CREATED;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarTopico topico) {
            topicoService.actualizarTopico(id, topico);
            message = ConstantService.MODEL_TOPIC + " " + ConstantService.INFO_UPDATED;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(topico);
    }

}
