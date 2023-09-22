package com.alura.foro.dto.request.crear;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoCrearCurso(

        @NotBlank(message = "Nombre es obligatorio")
        String nombre,
        String descripcion
) {
}
