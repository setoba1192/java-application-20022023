package com.test.application.user.registration.entity;

import com.test.application.user.registration.config.validators.PasswordValidation;
import com.test.application.user.registration.entity.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank(message = "{usuario.nombre.notblank}")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Email(message = "{usuario.correo.notvalid}")
    @NotBlank(message = "{usuario.correo.notblank}")
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @NotBlank(message = "{usuario.password.notblank}")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Telefono> telefonos;

    @Column(name = "token")
    private String token;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private Date created;

    @UpdateTimestamp
    @Column(name = "modified")
    private Date modified;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "isactive")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.correo;
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
