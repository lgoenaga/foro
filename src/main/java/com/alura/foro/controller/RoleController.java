package com.alura.foro.controller;

import com.alura.foro.model.Role;
import com.alura.foro.service.implement.RoleService;
import com.alura.foro.util.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Role", description = "API Role")
public class RoleController {

    final RoleService roleService;

    String message;
    Logger logger  = Logger.getLogger(RoleController.class.getName());

    @Operation(
            summary = "Listar Roles",
            description = "Listar Roles",
            tags = { "Role" }
    )
    @ApiResponse(responseCode = "200", description = "Roles encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
            return ResponseEntity.ok().body(roleService.listarRoles());
    }

    @Operation(
            summary = "Buscar Role",
            description = "Buscar Role",
            tags = { "Role" }
    )
    @ApiResponse(responseCode = "202", description = "Role encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            Role role = roleService.listarRol(id);
            message = ConstantService.MODEL_ROLE + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(role);
    }

}
