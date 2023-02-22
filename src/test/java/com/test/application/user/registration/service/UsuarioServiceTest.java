package com.test.application.user.registration.service;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Usuario;
import com.test.application.user.registration.mappers.StructMapper;
import com.test.application.user.registration.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.anyOf;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private StructMapper structMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioDTO usuarioDTO;

    private Usuario usuario;

    @BeforeEach
    public void setup() {

        usuarioDTO = UsuarioDTO.builder()
                .nombre("test")
                .correo("test@mail.com")
                .password("12345")
                .build();

        usuario = Usuario.builder()
                .nombre("test")
                .correo("test@mail.com")
                .password("12345")
                .build();

    }

    // Junit test for saveEmployee method
    @DisplayName("Junit test: crear usuario con todos los parametros correctos")
    @Test
    public void givenUsuario_whenCrearUsuario_thenReturnUsuarioDTO() {

        given(usuarioRepository.findByCorreo(usuarioDTO.getCorreo()))
                .willReturn(Optional.empty());
        given(usuarioRepository.save(usuario))
                .willReturn(usuario);
        given(structMapper.toEntity(any(UsuarioDTO.class)))
                .willReturn(usuario);
        given(structMapper.toDto(any(Usuario.class)))
                .willReturn(usuarioDTO);

        given(passwordEncoder.encode(usuarioDTO.getPassword())).willReturn(any());

        UsuarioDTO usuarioDTOCreado = usuarioService.crearUsuario(usuarioDTO);

        assertThat(usuarioDTOCreado).isNotNull();
    }

    @DisplayName("Junit test: buscar usuario por id")
    @Test
    public void givenId_whenFindById_thenReturnUsuarioDTO() {

        String id = "1";

        given(usuarioRepository.findById(id)).willReturn(Optional.of(usuario));

        given(structMapper.toDto(any(Usuario.class)))
                .willReturn(usuarioDTO);

        UsuarioDTO usuarioDTOencontrado = usuarioService.findById(id);

        assertThat(usuarioDTOencontrado).isNotNull();
    }

}
