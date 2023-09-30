package com.alura.foro.model;

import com.alura.foro.util.ConstantService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cursos")
@Data
public class Curso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull(message = "Nombre de " + ConstantService.MODEL_COURSE + " " + ConstantService.OBLIGATORIO)
    @NotEmpty(message = "Nombre de " + ConstantService.MODEL_COURSE + " " + ConstantService.NO_VACIO)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado " + ConstantService.OBLIGATORIO)
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
