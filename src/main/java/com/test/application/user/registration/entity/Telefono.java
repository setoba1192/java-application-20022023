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

    @NotBlank(message = "{telefono.number.notblank}")
    @Column(name = "numero", nullable = false)
    private String number;

    @NotBlank(message = "{telefono.codigociudad.notblank}")
    @Column(name = "codigo_ciudad", nullable = false)
    private String cityCode;

    @NotBlank(message = "{telefono.codigopais.notblank}")
    @Column(name = "codigo_pais", nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, updatable = false)
    private Usuario usuario;
}
