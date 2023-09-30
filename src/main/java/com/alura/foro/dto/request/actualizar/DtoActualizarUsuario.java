package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estado;
import com.alura.foro.model.Role;
import com.alura.foro.util.ConstantService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record DtoActualizarUsuario(
        @NotBlank(message = "Nombre " + ConstantService.OBLIGATORIO)
        String nombre,

        @NotBlank(message = ConstantService.MODEL_USER + " " + ConstantService.OBLIGATORIO)
        @Email(message = "Formato de email es inválido")
        String username,

        @NotBlank(message = "Password " + ConstantService.OBLIGATORIO)
        @Size(min = 5, message = "Password debe tener al menos 5 caracteres")
        String password,

        Estado estado,
        List<Role> roles
) {
}
