package com.alura.foro.security;

import com.alura.foro.dto.request.auth.LoginRequest;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.AuthResponse;
import com.alura.foro.model.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.service.implement.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;
    private final UsuarioService usuarioService;


    public Object login(LoginRequest request) {

            Usuario user = userRepository.findByUsername(request.username()).orElseThrow();

            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                return null;
            }

            return AuthResponse.builder()
                    .token(jwtService.generateToken(user))
                    .build();
    }
    public void register(DtoCrearUsuario request) {
       usuarioService.registrarUsuario(request);
    }
}
