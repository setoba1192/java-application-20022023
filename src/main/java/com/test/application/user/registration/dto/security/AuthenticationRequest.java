package com.test.application.user.registration.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Email(message = "{login.correo.notblank}")
    @NotBlank(message = "{usuario.correo.notblank}")
    private String correo;

    @NotBlank(message = "{login.password.notblank}")
    private String password;
}
