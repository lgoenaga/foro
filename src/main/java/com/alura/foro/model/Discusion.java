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
@Table(name = "discusiones")
@Getter
@RequiredArgsConstructor
public class Discusion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String mensaje;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Id de topico es obligatorio")
    @NotEmpty(message = "Id de topico no puede estar vacío")
    private Long idTopico;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Id de usuario es obligatorio")
    @NotEmpty(message = "Id de usuario no puede estar vacío")
    private Long idUsuario;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
}
