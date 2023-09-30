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
import java.util.logging.Logger;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "API Usuario")
public class UsuarioController {

    final UsuarioService usuarioService;
    String message;
    Logger logger  = Logger.getLogger(UsuarioController.class.getName());

    @Operation(
            summary = "Listar usuarios",
            description = "Listar todos los usuarios",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(
            summary = "Listar usuarios por estado",
            description = "Listar todos los usuarios por estado",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "200", description = "Usuarios por estado encontrados")
    @GetMapping("/estados/{estado}")
    public ResponseEntity<Object> index(@PathVariable("estado") String estado){
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios(estado);
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(
            summary = "Buscar usuario",
            description = "Buscar usuario por id",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "202", description = "Usuario encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            DtoListarUsuario userDtoResponse = usuarioService.listarUsuario(id);
            message = ConstantService.MODEL_USER + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoResponse);
    }

    @Operation(
            summary = "Registrar usuario",
            description = "Registrar usuario",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearUsuario userDtoRequest){
        usuarioService.registrarUsuario(userDtoRequest);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_CREATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoRequest);
    }

    @Operation(
            summary = "Actualizar usuario",
            description = "Actualizar usuario por id",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "202", description = "Usuario actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarUsuario userDtoRequest){
        usuarioService.actualizarUsuario(id, userDtoRequest);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_UPDATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoRequest);
    }

    @Operation(
            summary = "Eliminar usuario",
            description = "Eliminar usuario por id",
            tags = { "Usuarios" }
    )
    @ApiResponse(responseCode = "202", description = "Usuario eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id){
        usuarioService.eliminarUsuario(id);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_DELETED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
