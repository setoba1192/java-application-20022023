package com.test.application.user.registration.controller;

import com.test.application.user.registration.dto.RespuestaGenericaDTO;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespuestaGenericaDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @RequestHeader(name = "Authorization") String token) {

        UsuarioDTO usuarioCreado = this.usuarioService.crearUsuario(usuarioDTO, token);

        return ResponseEntity.created(URI.create("/api/usuario/" + usuarioCreado.getId())).body(RespuestaGenericaDTO.builder()
                .data(usuarioCreado)
                .mensaje("Se ha creado el usuario exitosamente")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaGenericaDTO> consultarUsuario(@PathVariable String id, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(RespuestaGenericaDTO.builder()
                .mensaje("Consulta de usuario")
                .data(this.usuarioService.findById(id))
                .build());
    }
}
