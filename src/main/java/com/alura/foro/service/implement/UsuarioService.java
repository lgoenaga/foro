package com.alura.foro.service.implement;

import com.alura.foro.dto.request.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.model.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.service.interfaces.UsuarioInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface {

    final UsuarioRepository usuarioRepository;

    @Override
    public List<DtoListarUsuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(DtoListarUsuario::new).toList();
    }

    @Override
    public DtoListarUsuario listarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return new DtoListarUsuario(usuario);
    }

    @Override
    public void registrarUsuario(DtoCrearUsuario dtoCrearUsuario) {
        Usuario usuario = new Usuario(dtoCrearUsuario);
        usuarioRepository.save(usuario);
    }
}
