package com.alura.foro.dto.request.crear;

import com.alura.foro.util.ConstantService;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DtoCrearTopico(

        @NotBlank(message = "Título " + ConstantService.OBLIGATORIO)
        String titulo,

        String descripcion,

        @Digits(message = "Id de " + ConstantService.MODEL_COURSE + " " + ConstantService.NUMERICO , fraction = 0, integer = 10)
        Long idCurso,

        @Digits(message = "Id de " + ConstantService.MODEL_USER + " " + ConstantService.NUMERICO, fraction = 0, integer = 10)
        Long idUsuario

    ) {
}
