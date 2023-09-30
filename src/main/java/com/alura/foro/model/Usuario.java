package com.alura.foro.model;

import com.alura.foro.util.ConstantService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @NotNull(message = "Nombre de " + ConstantService.MODEL_USER + " " + ConstantService.OBLIGATORIO)
    @NotEmpty(message = "Nombre de " + ConstantService.MODEL_USER + " " + ConstantService.NO_VACIO)
    private String username;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Password " + ConstantService.OBLIGATORIO)
    @NotEmpty(message = "Password " + ConstantService.NO_VACIO)
    private String password;

    @Column(length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    @NotNull(message = "Estado " + ConstantService.OBLIGATORIO)
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
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role: roles) {
            authorities.add(new GrantedAuthority() {
                @Serial
                private static final long serialVersionUID = 1L;

                @Override
                public String getAuthority() {
                    return role.getRol();
                }
            });
        }

        return authorities;
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
