package com.alura.foro.controller;

import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.service.implement.TopicoService;
import com.alura.foro.service.implement.UsuarioService;
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
}
