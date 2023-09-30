package com.alura.foro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable, UserDetails {

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
    private String password;

    @Column(length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Setter
    private Estado estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        this.roles.forEach(role -> {
            roles.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role.getRol();
                }
            });
        });
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
