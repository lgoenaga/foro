package com.alura.foro.service.implement;

import com.alura.foro.model.Role;
import com.alura.foro.repository.RoleRepository;
import com.alura.foro.service.interfaces.RoleInterface;
import com.alura.foro.util.ConstantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInterface {

    final RoleRepository roleRepository;

    Logger logger = Logger.getLogger(RoleService.class.getName());
    String message;

    @Override
    public List<Role> listarRoles() {
        List<Role> roles = roleRepository.findAll();
        message = "Roles encontrados: " + roles.size();
        logger.info(message);
        return roles;
    }

    @Override
    public Role listarRol(Long id) {
        Role role = roleRepository.findById(id).orElseThrow();
        message = ConstantService.MODEL_ROLE + " " + ConstantService.INFO_FOUND + " " + role.getRol();
        logger.info(message);
        return role;
    }
}
