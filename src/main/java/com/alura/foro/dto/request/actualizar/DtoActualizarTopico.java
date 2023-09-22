package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record DtoActualizarTopico(

        @NotBlank(message = "Título es obligatorio")
        String titulo,

        String descripcion,

        @Digits(message = "Id de curso debe ser numérico", fraction = 0, integer = 10)
        Long idCurso,

        @Digits(message = "Id de usuario debe ser numérico", fraction = 0, integer = 10)
        Long idUsuario,

        @Column(nullable = false)
        Estatus estatus,

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaActualizacion

    ) {

}
