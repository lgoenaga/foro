package com.alura.foro.service.implement;

import com.alura.foro.model.Role;
import com.alura.foro.repository.RoleRepository;
import com.alura.foro.service.interfaces.RoleInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleInterface {

    final RoleRepository roleRepository;

    @Override
    public List<Role> listarRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role listarRol(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }
}
