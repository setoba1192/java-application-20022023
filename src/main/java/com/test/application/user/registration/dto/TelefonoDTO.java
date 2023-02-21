package com.test.application.user.registration.dto;

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

    private String number;

    private String cityCode;

    private String countryCode;

    private String completeNumber;
}
