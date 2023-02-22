package com.test.application.user.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.dto.security.AuthenticationRequest;
import com.test.application.user.registration.repository.UsuarioRepository;
import com.test.application.user.registration.service.UsuarioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void registrarUsuario() {
        usuarioDTO = UsuarioDTO.builder()
                .nombre("test")
                .password("1234**Aa")
                .correo("test@gmail.com")
                .build();
        usuarioService.crearUsuario(usuarioDTO);
    }

    @AfterEach
    public void eliminarUsuarios(){
        usuarioRepository.deleteAll();
    }

    @DisplayName("Junit test: status no autorizado para una peticion de login incorrecta")
    @Test
    public void givenAuthRequest_whenLogin_thenReturnStatusForbidden() throws Exception {

        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .correo("test")
                .password("test")
                .build();

        ResultActions response = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequest)));

        response.andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @DisplayName("Junit test: status 200 para una peticion de login correcta")
    @Test
    public void givenAuthRequest_whenLogin_thenReturnStatusOk() throws Exception {

        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .correo(this.usuarioDTO.getCorreo())
                .password(this.usuarioDTO.getPassword())
                .build();

        ResultActions response = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequest)));

        response.andDo(print())
                .andExpect(status().isOk());
    }


    @DisplayName("Junit test: status 200 para una peticion de registro")
    @Test
    public void givenUsuario_whenRegistrar_thenReturnUsuarioRegistrado() throws Exception {

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .nombre("test")
                .password("1234**Aa")
                .correo("test1@gmail.com")
                .build();

        ResultActions response = mockMvc.perform(post("/api/auth/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDTO)));

        response.andDo(print())
                .andExpect(status().isCreated());
    }

}
