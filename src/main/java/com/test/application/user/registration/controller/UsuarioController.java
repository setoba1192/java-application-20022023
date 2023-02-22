package com.test.application.user.registration.controller;

import com.test.application.user.registration.dto.RespuestaGenericaDTO;
import com.test.application.user.registration.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaGenericaDTO> consultarUsuario(@PathVariable String id) {
        return ResponseEntity.ok(RespuestaGenericaDTO.builder()
                .mensaje("Consulta de usuario")
                .data(this.usuarioService.findById(id))
                .build());
    }
}
