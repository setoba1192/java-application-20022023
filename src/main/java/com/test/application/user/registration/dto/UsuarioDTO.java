package com.test.application.user.registration.dto;

import com.test.application.user.registration.entity.Telefono;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String id;

    private String nombre;

    private String correo;

    private String password;

    private List<Telefono> telefonos;
}
