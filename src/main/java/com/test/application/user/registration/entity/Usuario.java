package com.test.application.user.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank(message = "El nombre del usuario es requerido")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Email(message = "Es necesario especificar un nombre de correo válido")
    @NotBlank(message = "El correo del usuario es requerido")
    @Column(name = "correo", nullable = false)
    private String correo;

    @NotBlank(message = "La contraseña es requerida")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "Es requerido al menos 1 teléfono de contacto")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Telefono> telefonos;

    @Column(name = "token")
    private String token;

}
