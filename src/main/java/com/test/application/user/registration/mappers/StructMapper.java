package com.test.application.user.registration.mappers;

import com.test.application.user.registration.dto.TelefonoDTO;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Telefono;
import com.test.application.user.registration.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StructMapper {

    StructMapper INSTANCE = Mappers.getMapper(StructMapper.class);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(Usuario target);

    Telefono toEntity(TelefonoDTO telefonoDTO);

    @Mapping( target = "completeNumber", expression = "java(telefono.getCountryCode()+telefono.getCityCode()+telefono.getNumber())")
    TelefonoDTO toDto(Telefono telefono);
}