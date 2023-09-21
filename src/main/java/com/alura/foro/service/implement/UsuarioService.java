package com.alura.foro.service.implement;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.model.Estado;
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

    @Override
    public void actualizarUsuario(Long id, DtoActualizarUsuario userDtoRequest) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        if (userDtoRequest.estado()==null) {
            usuario.setEstado(usuario.getEstado());
        }else{
            usuario.setEstado(userDtoRequest.estado());
        }
        usuario.actualizarUsuario(userDtoRequest);
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<DtoListarUsuario> listarUsuarios(String estado) {
        List<Usuario> usuarios = usuarioRepository.findByEstado(Estado.valueOf(estado));
        return usuarios.stream().map(DtoListarUsuario::new).toList();
    }
}
