package com.test.application.user.registration.service.security;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Usuario;

public interface UsuarioSecurityService {

    /**
     * El siguiente metodo solo se debe utilizar para fines de seguridad/autenticación
     * @param id
     * @return
     */
    Usuario findByCorreoForSecurity(String correo);

    Usuario updateLastLogin(Usuario usuario);

}
