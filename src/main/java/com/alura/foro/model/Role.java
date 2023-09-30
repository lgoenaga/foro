package com.alura.foro.model;

import com.alura.foro.util.ConstantService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@RequiredArgsConstructor
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    @NotNull(message = "Rol " + ConstantService.OBLIGATORIO)
    @NotEmpty(message = "Rol " + ConstantService.NO_VACIO)
    private String rol;


}
