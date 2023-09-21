package com.alura.foro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@RequiredArgsConstructor
public class Topico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull(message = "Título es obligatorio")
    @NotEmpty(message = "Título no puede estar vacío")
    private String titulo;

    @Column(length = 100)
    private String descripcion;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Id de curso es obligatorio")
    @NotEmpty(message = "Id de curso no puede estar vacío")
    private Long idCurso;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Id de usuario es obligatorio")
    @NotEmpty(message = "Id de usuario no puede estar vacío")
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;

}
