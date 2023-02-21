package com.test.application.user.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "telefono")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El número de contacto es requerido")
    @Column(name = "numero")
    private String number;

    @NotBlank(message = "El código de ciudad es requerido")
    @Column(name = "codigo_ciudad")
    private String cityCode;

    @NotBlank(message = "El código de país es requerido")
    @Column(name = "codigo_pais")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "usuario_telefono_fk", nullable = false, updatable = false)
    private Usuario usuario;
}
