package com.alura.foro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "discusiones")
@Data
public class Discusion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    @NotNull(message = "Id de tópico es obligatorio")
    @NotEmpty(message = "Id de tópico no puede estar vacío")
    private String mensaje;

    @Column(nullable = false)
    @NotNull(message = "Id de tópico es obligatorio")
    @Positive(message = "Id de usuario debe ser numérico y mayor a cero")
    private Long idTopico;

    @Column(nullable = false)
    @NotNull(message = "Id de usuario es obligatorio")
    @Positive(message = "Id de usuario debe ser numérico y mayor a cero")
    private Long idUsuario;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

}
