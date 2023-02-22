package com.test.application.user.registration.service.security;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Usuario;
import com.test.application.user.registration.exception.ServiceException;
import com.test.application.user.registration.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UsuarioSecurityServiceImpl implements UsuarioSecurityService {

    private final MessageSource messageSource;

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByCorreoForSecurity(String correo) {
        return this.usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new ServiceException(this.messageSource.getMessage("usuario.correo.nonexisting", null, Locale.getDefault())
                        .replace("#correo", correo)));
    }

    @Override
    public Usuario updateLastLogin(Usuario usuario) {
        usuario.setLastLogin(new Date(System.currentTimeMillis()));
        return this.usuarioRepository.save(usuario);
    }

}
