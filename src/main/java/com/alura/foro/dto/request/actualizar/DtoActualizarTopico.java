package com.alura.foro.dto.request.actualizar;

import com.alura.foro.model.Estatus;
import com.alura.foro.util.ConstantService;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record DtoActualizarTopico(

        @NotBlank(message = "Título " + ConstantService.OBLIGATORIO)
        String titulo,

        String descripcion,

        @Digits(message = "Id de " + ConstantService.MODEL_COURSE+ " " + ConstantService.NUMERICO, fraction = 0, integer = 10)
        Long idCurso,

        @Digits(message = "Id de" + ConstantService.MODEL_USER  + ConstantService.NUMERICO, fraction = 0, integer = 10)
        Long idUsuario,

        @Column(nullable = false)
        Estatus estatus,

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaActualizacion

    ) {

}
