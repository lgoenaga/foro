package com.alura.foro.controller;

import com.alura.foro.model.Role;
import com.alura.foro.service.implement.RoleService;
import com.alura.foro.util.ConstantService;
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
public class RoleController {

    final RoleService roleService;

    String message;
    Logger logger  = Logger.getLogger(RoleController.class.getName());

    @GetMapping
    public ResponseEntity<Object> index() {
            return ResponseEntity.ok().body(roleService.listarRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id){
            Role role = roleService.listarRol(id);
            message = ConstantService.MODEL_ROLE + " " + ConstantService.INFO_FOUND;
            logger.info(message);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(role);
    }

}
