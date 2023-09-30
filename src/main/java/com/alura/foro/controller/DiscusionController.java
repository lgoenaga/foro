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

@RestController
@RequestMapping ("/discusiones")
@RequiredArgsConstructor
@Tag(name = ConstantService.MODEL_DISCUSSION, description = "API " + ConstantService.MODEL_DISCUSSION)
public class DiscusionController {

    final DiscusionService discusionService;

    @Operation(summary = ConstantService.LIST + " Discusiones",
            description = ConstantService.LIST + " todas las discusiones",
            tags = { ConstantService.MODEL_DISCUSSION }
    )
    @ApiResponse(responseCode = "200", description = "Discusiones encontradas")
    @GetMapping
    @PreAuthorize(ConstantService.ADMIN_USER_SEC)
    public ResponseEntity<Object> index() {
        List<DtoListarDiscusion> discusiones = discusionService.listarDiscusiones();
        return ResponseEntity.ok().body(discusiones);
    }

    @Operation(summary = ConstantService.LIST + " Discusiones por estado",
            description = ConstantService.LIST + " todas las discusiones por estado",
            tags = { ConstantService.MODEL_DISCUSSION }
    )
    @ApiResponse(responseCode = "202", description = "Discusiones encontradas")
    @GetMapping("/{id}")
    @PreAuthorize(ConstantService.ADMIN_USER_SEC)
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        DtoListarDiscusion discusion = discusionService.buscarDiscusion(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discusion);
    }

    @Operation(summary = ConstantService.CREATE + " " + ConstantService.MODEL_DISCUSSION,
            description = ConstantService.CREATE + " " + ConstantService.MODEL_DISCUSSION,
            tags = { ConstantService.MODEL_DISCUSSION }
    )
    @ApiResponse(responseCode = "201", description = ConstantService.MODEL_DISCUSSION + " creada")
    @PostMapping
    @PreAuthorize(ConstantService.ADMIN_USER_SEC)
    public ResponseEntity<Object> store(@RequestBody DtoCrearDiscusion discusion) {
        discusionService.registrarDiscusion(discusion);
        return ResponseEntity.status(HttpStatus.CREATED).body(discusion);
    }
    @Operation(summary = ConstantService.UPDATE + " " + ConstantService.MODEL_DISCUSSION,
            description = ConstantService.UPDATE + " " + ConstantService.MODEL_DISCUSSION,
            tags = { ConstantService.MODEL_DISCUSSION }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_DISCUSSION + " actualizada")
    @DeleteMapping("/{id}")
    @PreAuthorize(ConstantService.ADMIN_SEC)
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id) {
        String message;
        discusionService.eliminarDiscusion(id);
        message = ConstantService.MODEL_DISCUSSION + " " + ConstantService.INFO_DELETED;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }
}
