package com.alura.foro.service.interfaces;


import com.alura.foro.model.Role;

import java.util.List;

public interface RoleInterface {

    List<Role> listarRoles();

    Role listarRol(Long id);

}
