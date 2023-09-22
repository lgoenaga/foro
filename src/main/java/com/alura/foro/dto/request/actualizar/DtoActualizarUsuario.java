package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record DtoActualizarUsuario(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Usuario es obligatorio")
        @Email(message = "Formato de email es inválido")
        String username,

        @NotBlank(message = "Password es obligatorio")
        @Size(min = 5, message = "Password debe tener al menos 5 caracteres")
        String password,

        Estado estado
) {
}
