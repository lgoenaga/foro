package com.alura.foro.dto.request.crear;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoCrearDiscusion(

        @NotBlank(message = "Título es obligatorio")
        String mensaje,

        @Digits(message = "Id de curso debe ser numérico", fraction = 0, integer = 10)
        Long idTopico,

        @Digits(message = "Id de usuario debe ser numérico", fraction = 0, integer = 10)
        Long idUsuario

    ) {
}
