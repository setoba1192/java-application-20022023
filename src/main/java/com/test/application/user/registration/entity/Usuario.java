package com.test.application.user.registration.entity;

import com.test.application.user.registration.config.validators.PasswordValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "usuario")
public class Usuario {
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

    @Column(name = "last_login", insertable = false)
    private Date lastLogin;

    @Column(name = "isactive")
    private boolean isActive;

}
