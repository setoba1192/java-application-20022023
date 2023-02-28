package com.test.application.user.registration.dto;

import com.test.application.user.registration.config.validators.PasswordValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "{usuario.nombre.notblank}")
    private String nombre;

    @Email(message = "{usuario.correo.notvalid}")
    @NotBlank(message = "{usuario.correo.notblank}")
    private String correo;

    @NotBlank(message = "{usuario.password.notblank}")
    @PasswordValidation(message = "{usuario.password.patternmatch}")
    private String password;

    private Date created;

    private Date modified;

    private Date lastLogin;

    private boolean isActive;

    private String token;

    private List<TelefonoDTO> telefonos;
}
