package com.alura.foro.service.implement;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.model.Estado;
import com.alura.foro.model.Usuario;
import com.alura.foro.repository.RoleRepository;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.service.interfaces.UsuarioInterface;
import com.alura.foro.util.ConstantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    Logger logger  = Logger.getLogger(UsuarioService.class.getName());

    String message;
    @Override
    public List<DtoListarUsuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        message = "Listando " + ConstantService.MODEL_USER + "s";
        logger.info(message);
        return usuarios.stream().map(DtoListarUsuario::new).toList();
    }

    @Override
    public DtoListarUsuario listarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        message = ConstantService.MODEL_USER + " " + usuario.getUsername() + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return new DtoListarUsuario(usuario);
    }

    @Override
    public List<DtoListarUsuario> listarUsuarios(String estado) {
        List<Usuario> usuarios = usuarioRepository.findByEstado(Estado.valueOf(estado));
        message = "Listando usuarios con estado " + estado;
        logger.info(message);
        return usuarios.stream().map(DtoListarUsuario::new).toList();
    }

    @Override
    @Transactional
    public void registrarUsuario(DtoCrearUsuario dtoCrearUsuario) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoCrearUsuario.username());

        if (dtoCrearUsuario.password()!=null) {
            usuario.setPassword(passwordEncoder.encode(dtoCrearUsuario.password()));
        }
        usuario.setNombre(dtoCrearUsuario.nombre());
        usuario.setEstado(Estado.ACTIVO);
        usuario.setRoles(roleRepository.findByRol(ConstantService.ROLE_USER));
        usuarioRepository.save(usuario);
        message = ConstantService.MODEL_USER + " " + usuario.getUsername() + " " + ConstantService.INFO_CREATED;
        logger.info(message);
    }

    @Override
    @Transactional
    public void actualizarUsuario(Long id, DtoActualizarUsuario userDtoRequest) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuario.setUsername(userDtoRequest.username());
        if (userDtoRequest.password()==null) {
            usuario.setPassword(usuario.getPassword());
        }else{
            usuario.setPassword(passwordEncoder.encode(userDtoRequest.password()));
        }
        usuario.setNombre(userDtoRequest.nombre());
        if (userDtoRequest.estado()==null) {
            usuario.setEstado(usuario.getEstado());
        }else{
            usuario.setEstado(userDtoRequest.estado());
        }
        if (userDtoRequest.roles()==null) {
            usuario.setRoles(usuario.getRoles());
        }else{
            usuario.setRoles(userDtoRequest.roles());
        }

        message = ConstantService.MODEL_USER + " " + usuario.getUsername() + " " + ConstantService.INFO_UPDATED;
        logger.info(message);

    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        message = ConstantService.MODEL_USER + " " + usuario.getUsername() + " " + ConstantService.INFO_DELETED;
        usuarioRepository.deleteById(id);
        logger.info(message);
    }

    @Override
    public DtoListarUsuario buscarUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow();
        message = ConstantService.MODEL_USER + " " + username + " " + ConstantService.INFO_FOUND;
        logger.info(message);
        return new DtoListarUsuario(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findByUsername(username).orElseThrow();
    }
}
