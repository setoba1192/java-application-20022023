package com.test.application.user.registration.service;

import com.test.application.user.registration.dto.TelefonoDTO;
import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Telefono;
import com.test.application.user.registration.entity.Usuario;
import com.test.application.user.registration.entity.security.Role;
import com.test.application.user.registration.exception.ServiceException;
import com.test.application.user.registration.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    private final MessageSource messageSource;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByCorreo(usuarioDTO.getCorreo());
        if (usuarioEncontrado.isPresent())
            throw new ServiceException(messageSource.getMessage("usuario.correo.existing", null, Locale.getDefault())
                    .replace("#correo", usuarioDTO.getCorreo()));

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setTelefonos(Optional.ofNullable(usuarioDTO.getTelefonos()).orElseGet(() -> new ArrayList<>()).stream()
                .map(telefonoDTO -> Telefono.builder()
                        .usuario(usuario)
                        .number(telefonoDTO.getNumber())
                        .cityCode(telefonoDTO.getCityCode())
                        .countryCode(telefonoDTO.getCountryCode())
                        .build()).toList());
        usuario.setActive(true);
        usuario.setRole(Role.USER);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        UsuarioDTO returnDto = modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
        returnDto.setPassword(null);
        returnDto.setTelefonos(usuario.getTelefonos().stream().map(telefono -> TelefonoDTO.builder()
                .id(telefono.getId())
                .number(telefono.getNumber())
                .cityCode(telefono.getCityCode())
                .countryCode(telefono.getCountryCode())
                .build()).toList());

        return returnDto;
    }

    @Override
    public UsuarioDTO findById(String id) {
        return this.usuarioRepository.findById(id)
                .map(usuario -> Optional.of(this.modelMapper.map(usuario, UsuarioDTO.class)).map(usuarioDTO -> {
                    usuarioDTO.setPassword(null);
                    return usuarioDTO;
                }).get())
                .orElseThrow(() -> new ServiceException(this.messageSource.getMessage("usuario.id.nonexisting", null, Locale.getDefault())
                        .replace("id", id)));
    }


}
