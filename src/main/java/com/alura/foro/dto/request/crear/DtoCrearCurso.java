package com.alura.foro.dto.request.crear;

import com.alura.foro.util.ConstantService;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoCrearCurso(

        @NotBlank(message = "Nombre " + ConstantService.OBLIGATORIO)
        String nombre,
        String descripcion
) {
}
