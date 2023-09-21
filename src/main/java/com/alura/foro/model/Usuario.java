package com.alura.foro.model;

import com.alura.foro.dto.request.actualizar.DtoActualizarUsuario;
import com.alura.foro.dto.request.crear.DtoCrearUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "usuarios")
@Getter
@RequiredArgsConstructor
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull(message = "Nombre de usuario es obligatorio")
    @NotEmpty(message = "Nombre de usuario no puede estar vacío")
    private String username;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Password es obligatorio")
    @NotEmpty(message = "Password no puede estar vacío")
    @Size(min = 5, message = "Password debe tener al menos 5 caracteres")
    private String password;

    @Column(length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Setter
    private Estado estado;

    public Usuario(DtoCrearUsuario dtoCrearUsuario) {
        this.username = dtoCrearUsuario.username();
        this.password = dtoCrearUsuario.password();
        this.nombre = dtoCrearUsuario.nombre();
        this.estado = Estado.Activo;
    }

    public void actualizarUsuario(DtoActualizarUsuario userDtoRequest) {
        this.username = userDtoRequest.username();
        this.password = userDtoRequest.password();
        this.nombre = userDtoRequest.nombre();
        if (userDtoRequest.estado()!=null) {
            this.estado = userDtoRequest.estado();
        }
    }
}
