package com.alura.foro.repository;

import com.alura.foro.model.Estado;
import com.alura.foro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEstado(Estado estado);
}
