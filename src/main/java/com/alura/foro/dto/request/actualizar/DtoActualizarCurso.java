package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estado;
import com.alura.foro.util.ConstantService;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoActualizarCurso(

        @NotBlank(message = "Nombre " + ConstantService.OBLIGATORIO)
        String nombre,
        String descripcion,
        Estado estado
) {
}
