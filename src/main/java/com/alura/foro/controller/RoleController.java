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

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = ConstantService.MODEL_ROLE, description = "API " + ConstantService.MODEL_ROLE)
public class RoleController {

    final RoleService roleService;


    @Operation(
            summary = "Listar Roles",
            description = "Listar Roles",
            tags = {ConstantService.MODEL_ROLE}
    )
    @ApiResponse(responseCode = "200", description = "Roles encontrados")
    @GetMapping
    public ResponseEntity<Object> index() {
            return ResponseEntity.ok().body(roleService.listarRoles());
    }

    @Operation(
            summary = "Buscar " + ConstantService.MODEL_ROLE,
            description = "Buscar " + ConstantService.MODEL_ROLE,
            tags = {ConstantService.MODEL_ROLE}
    )
    @ApiResponse(responseCode = "202", description = ConstantService.MODEL_ROLE + " " + ConstantService.INFO_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            Role role = roleService.listarRol(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(role);
    }

}
