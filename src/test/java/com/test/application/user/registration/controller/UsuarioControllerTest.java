package com.test.application.user.registration.controller;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void registrarUsuario() {
        usuarioDTO = UsuarioDTO.builder()
                .nombre("test")
                .password("1234")
                .correo("test@gmail.com")
                .build();
        usuarioDTO = usuarioService.crearUsuario(usuarioDTO);
    }


    @DisplayName("Junit test: status ok para consulta de usuario con autenticacion")
    @Test
    @WithMockUser(username = "usuario", password = "pass", roles = "USER")
    public void givenUsuario_whenConsultaPorId_thenReturnStatusOk() throws Exception {

        ResultActions response = mockMvc.perform(get("/api/usuario/{id}", usuarioDTO.getId()));
        response.andExpect(status().isOk())
                .andDo(print());

    }


    @DisplayName("Junit test: status prohibido para consulta de usuario sin autenticacion")
    @Test
    public void givenUsuario_whenConsultaPorId_thenReturnStatusForbidden() throws Exception {

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id("1")
                .correo("test@gmail.com")
                .nombre("testNombre")
                .build();

        ResultActions response = mockMvc.perform(get("/api/usuario/{id}", usuarioDTO.getId()));
        response.andExpect(status().isForbidden())
                .andDo(print());

    }
}
