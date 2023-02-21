package com.test.application.user.registration.service;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, String token);

    UsuarioDTO findById(String id);

}
