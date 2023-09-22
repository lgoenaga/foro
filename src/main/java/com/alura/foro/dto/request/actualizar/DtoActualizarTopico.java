package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DtoActualizarTopico(

        @NotBlank(message = "Título es obligatorio")
        String titulo,

        String descripcion,

        @NotBlank(message = "Id de curso es obligatorio")
        Long idCurso,

        @NotBlank(message = "Id de usuario es obligatorio")
        Long idUsuario,

        Estatus estatus,

        LocalDateTime fechaActualizacion

    ) {
}
