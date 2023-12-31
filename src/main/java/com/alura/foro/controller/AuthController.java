package com.alura.foro.controller;

import com.alura.foro.dto.request.auth.LoginRequest;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import com.alura.foro.dto.response.DtoListarUsuario;
import com.alura.foro.model.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.security.AuthService;
import com.alura.foro.util.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "API Auth")
public class AuthController {
    Logger logger  = Logger.getLogger(AuthController.class.getName());
    String message;

    private final AuthService authService;
    private final UsuarioRepository userRepository;

    @Operation(
            summary = "Login",
            description = "Login",
            tags = { "Auth" }
    )
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        Object authResponse = authService.login(request);
        message = ConstantService.MODEL_USER + " " + ConstantService.LOGIN_SUCCESS;
        logger.info(message);
        if (authResponse == null) {
            message = ConstantService.MODEL_USER + " " + ConstantService.LOGIN_ERROR;
            logger.warning(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok().body(authResponse);

    }

    @Operation(
            summary = "Register",
            description = "Register",
            tags = { "Auth" }
    )
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody DtoCrearUsuario request) {
        authService.register(request);
        Usuario user = userRepository.findByUsername(request.username()).orElseThrow();
        message = ConstantService.MODEL_USER + " " + ConstantService.REGISTER_SUCCESS;
        logger.info(message);
        return ResponseEntity.ok().body(new DtoListarUsuario(user));
    }

}
