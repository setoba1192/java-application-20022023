package com.test.application.user.registration.controller;

import com.test.application.user.registration.dto.RespuestaGenericaDTO;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.dto.security.AuthenticationRequest;
import com.test.application.user.registration.service.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registrar")
    public ResponseEntity<RespuestaGenericaDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioCreado = this.authenticationService.registrarUsuario(usuarioDTO);
        return ResponseEntity.created(URI.create("/api/usuario/" + usuarioCreado.getId())).body(RespuestaGenericaDTO.builder()
                .data(usuarioCreado)
                .mensaje("Se ha creado el usuario exitosamente")
                .build());
    }


    @PostMapping("/login")
    public ResponseEntity<RespuestaGenericaDTO> authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(RespuestaGenericaDTO.builder()
                .data(authenticationService.login(authenticationRequest))
                .mensaje("Inicio de sesión exitoso")
                .build());
    }
}
