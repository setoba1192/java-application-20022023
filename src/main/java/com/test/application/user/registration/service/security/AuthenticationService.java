package com.test.application.user.registration.service.security;

import com.test.application.user.registration.config.security.JwtService;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.dto.security.AuthenticationRequest;
import com.test.application.user.registration.entity.Usuario;
import com.test.application.user.registration.entity.security.Role;
import com.test.application.user.registration.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final UsuarioService usuarioService;

    private final UsuarioSecurityService usuarioSecurityService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {

        var usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .correo(usuarioDTO.getCorreo())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(usuario);
        usuarioDTO.setLastLogin(new Date(System.currentTimeMillis()));
        usuarioDTO.setToken(jwtToken);
        UsuarioDTO usuarioRegistrado = usuarioService.crearUsuario(usuarioDTO);

        return usuarioRegistrado;
    }

    /**
     * Método para realizar login.
     * Se establece el nuevo token para el usuario para que pueda persistir
     *
     * @param request
     * @return
     */
    public UsuarioDTO login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        var usuario = usuarioSecurityService.findByCorreoForSecurity(request.getCorreo());

        String token = jwtService.generateToken(usuario);

        usuario.setToken(token);
        usuario = this.usuarioSecurityService.updateLastLogin(usuario);


        UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setPassword(null);

        return usuarioDTO;
    }
}
