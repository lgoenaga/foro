package com.alura.foro.controller;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;
    String message;
    Logger logger  = Logger.getLogger(UsuarioController.class.getName());

    @GetMapping
    public ResponseEntity<Object> index() {
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/estados/{estado}")
    public ResponseEntity<Object> index(@PathVariable("estado") String estado){
        List<DtoListarUsuario> usuarios = usuarioService.listarUsuarios(estado);
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            DtoListarUsuario userDtoResponse = usuarioService.listarUsuario(id);
            message = ConstantService.MODEL_USER + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoResponse);
    }

    @PostMapping
    public ResponseEntity<Object> store(@RequestBody @Valid DtoCrearUsuario userDtoRequest){
        usuarioService.registrarUsuario(userDtoRequest);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_CREATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid DtoActualizarUsuario userDtoRequest){
        usuarioService.actualizarUsuario(id, userDtoRequest);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_UPDATED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable("id") Long id){
        usuarioService.eliminarUsuario(id);
        message = ConstantService.MODEL_USER + " " + ConstantService.INFO_DELETED;
        logger.info(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
