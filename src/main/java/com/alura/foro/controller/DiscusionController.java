package com.alura.foro.controller;

import com.alura.foro.dto.response.DtoListarDiscucion;
import com.alura.foro.service.implement.DiscusionService;
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
@RequestMapping ("/discusiones")
@RequiredArgsConstructor
public class DiscusionController {

    final DiscusionService discusionService;

    String message;
    Logger logger  = Logger.getLogger(DiscusionController.class.getName());

    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarDiscucion> discusiones = discusionService.listarDiscuciones();
        return ResponseEntity.ok().body(discusiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarDiscucion discusion = discusionService.buscarDiscucion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discusion);
    }

}
