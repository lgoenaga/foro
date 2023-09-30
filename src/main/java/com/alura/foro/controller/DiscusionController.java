package com.alura.foro.controller;

import com.alura.foro.dto.request.crear.DtoCrearDiscusion;
import com.alura.foro.dto.response.DtoListarDiscusion;
import com.alura.foro.service.implement.DiscusionService;
import com.alura.foro.util.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping ("/discusiones")
@RequiredArgsConstructor
@Tag(name = "Discusion", description = "API Discusion")
public class DiscusionController {

    final DiscusionService discusionService;

    String message;
    Logger logger  = Logger.getLogger(DiscusionController.class.getName());

    @Operation(summary = "Listar Discusiones",
            description = "Listar todas las discusiones",
            tags = { "discusiones" }
    )
    @ApiResponse(responseCode = "200", description = "Discusiones encontradas")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> index() {
        List<DtoListarDiscusion> discusiones = discusionService.listarDiscusiones();
        return ResponseEntity.ok().body(discusiones);
    }

    @Operation(summary = "Listar Discusiones por estado",
            description = "Listar todas las discusiones por estado",
            tags = { "discusiones" }
    )
    @ApiResponse(responseCode = "202", description = "Discusión encontrada")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarDiscusion discusion = discusionService.buscarDiscusion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discusion);
    }

    @Operation(summary = "Crear Discusión",
            description = "Crear una discusión",
            tags = { "discusiones" }
    )
    @ApiResponse(responseCode = "201", description = "Discusión creada")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> store(@RequestBody DtoCrearDiscusion discusion) {
        discusionService.registrarDiscusion(discusion);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_CREATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(discusion);
    }
    @Operation(summary = "Actualizar Discusión",
            description = "Actualizar una discusión",
            tags = { "discusiones" }
    )
    @ApiResponse(responseCode = "202", description = "Discusión actualizada")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
        discusionService.eliminarDiscusion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_DELETED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }
}
