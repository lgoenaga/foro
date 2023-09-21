package com.alura.foro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cursos")
@Getter
@RequiredArgsConstructor
public class Curso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull(message = "Nombre de curso es obligatorio")
    @NotEmpty(message = "Nombre de curso no puede estar vacío")
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
