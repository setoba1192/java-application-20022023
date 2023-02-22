package com.test.application.user.registration.service.security;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.dto.security.AuthenticationRequest;

public interface AuthenticationService {

    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO login(AuthenticationRequest request);
}
