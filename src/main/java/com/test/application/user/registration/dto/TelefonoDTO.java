package com.test.application.user.registration.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefonoDTO {

    private Long id;

    @NotBlank(message = "{telefono.number.notblank}")
    private String number;

    @NotBlank(message = "{telefono.codigociudad.notblank}")
    private String cityCode;

    @NotBlank(message = "{telefono.codigopais.notblank}")
    private String countryCode;

    private String completeNumber;
}
