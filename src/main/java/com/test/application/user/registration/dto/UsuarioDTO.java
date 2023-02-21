package com.test.application.user.registration.dto;

import com.test.application.user.registration.config.validators.PasswordValidation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String id;

    private String nombre;

    private String correo;

    @PasswordValidation(message = "{usuario.password.patternmatch}")
    private String password;

    private Date created;

    private Date modified;

    private Date lastLogin;

    private boolean isActive;

    private List<TelefonoDTO> telefonos;
}
