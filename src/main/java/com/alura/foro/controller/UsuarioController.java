package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.service.implement.UsuarioService;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = ConstantService.MODEL_USER, description = "API " + ConstantService.MODEL_USER)
public class UsuarioController {

    final UsuarioService usuarioService;

    @Operation(
            summary = ConstantService.LIST + " " + ConstantService.MODEL_USER + "s",
            description = ConstantService.LIST + " todos los" + ConstantService.MODEL_USER + "s",
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(
            summary = ConstantService.LIST + " " + ConstantService.MODEL_USER + "s por estado",
            description = ConstantService.LIST + " todos los" + ConstantService.MODEL_USER + "s por estado",
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "200", description = "Usuarios por estado encontrados")
    @GetMapping("/estados/{estado}")
    public ResponseEntity<Object> index(@PathVariable("estado") String estado){
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios(estado);
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(
            summary = ConstantService.FOUND + " " + ConstantService.MODEL_USER + " por id",
            description = ConstantService.FOUND + " " + ConstantService.MODEL_USER + " por id",
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_USER + " " + ConstantService.INFO_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            DtoListarUsuario userDtoResponse = usuarioService.listarUsuario(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoResponse);
    }

    @Operation(
            summary = ConstantService.CREATE + " " + ConstantService.MODEL_USER,
            description = ConstantService.CREATE + " " + ConstantService.MODEL_USER,
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "201", description = ConstantService.MODEL_USER + " " + ConstantService.INFO_CREATED)
    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearUsuario userDtoRequest){
        usuarioService.registrarUsuario(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoRequest);
    }

    @Operation(
            summary = ConstantService.UPDATE + " " + ConstantService.MODEL_USER,
            description = ConstantService.UPDATE + " " + ConstantService.MODEL_USER + " por id",
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_USER + " " + ConstantService.INFO_UPDATED)
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarUsuario userDtoRequest){
        usuarioService.actualizarUsuario(id, userDtoRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoRequest);
    }

    @Operation(
            summary = ConstantService.DELETE + " " + ConstantService.MODEL_USER,
            description = ConstantService.DELETE + " " + ConstantService.MODEL_USER + " por id",
            tags = { ConstantService.MODEL_USER }
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_USER + " " + ConstantService.INFO_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id){
        String message;
        usuarioService.eliminarUsuario(id);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_DELETED;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
