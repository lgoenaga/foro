package com.alura.foro.controller;

import com.alura.foro.dto.request.crear.DtoCrearDiscusion;
import com.alura.foro.dto.response.DtoListarDiscusion;
import com.alura.foro.service.implement.DiscusionService;
import com.alura.foro.util.ConstantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<DtoListarDiscusion> discusiones = discusionService.listarDiscusiones();
        return ResponseEntity.ok().body(discusiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarDiscusion discusion = discusionService.buscarDiscusion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discusion);
    }

    @PostMapping
    public ResponseEntity<Object> store(@RequestBody DtoCrearDiscusion discusion) {
        discusionService.registrarDiscusion(discusion);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_CREATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(discusion);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
        discusionService.eliminarDiscusion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_DELETED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }
}
