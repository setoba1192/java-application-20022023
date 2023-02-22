package com.test.application.user.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaGenericaDTO {

    private String mensaje;

    private List<String> errores;

    private Object data;

}
