package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estado;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoActualizarCurso(

        @NotBlank(message = "Nombre es obligatorio")
        String nombre,
        String descripcion,
        Estado estado
) {
}
