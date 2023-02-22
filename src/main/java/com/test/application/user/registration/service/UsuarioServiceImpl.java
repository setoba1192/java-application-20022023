package com.test.application.user.registration.service;

import com.test.application.user.registration.dto.UsuarioDTO;
import com.test.application.user.registration.entity.Telefono;
import com.test.application.user.registration.entity.Usuario;
import com.test.application.user.registration.entity.security.Role;
import com.test.application.user.registration.exception.ServiceException;
import com.test.application.user.registration.mappers.StructMapper;
import com.test.application.user.registration.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final MessageSource messageSource;

    private final PasswordEncoder passwordEncoder;

    private final StructMapper structMapper;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByCorreo(usuarioDTO.getCorreo());
        if (usuarioEncontrado.isPresent())
            throw new ServiceException(messageSource.getMessage("usuario.correo.existing", null, Locale.getDefault())
                    .replace("#correo", usuarioDTO.getCorreo()));

        Usuario usuario = structMapper.toEntity(usuarioDTO);
        usuario.setTelefonos(Optional.ofNullable(usuarioDTO.getTelefonos()).orElseGet(() -> new ArrayList<>()).stream()
                .map(telefonoDTO -> {
                    Telefono telefono = this.structMapper.toEntity(telefonoDTO);
                    telefono.setUsuario(usuario);
                    return telefono;
                }).toList());

        usuario.setActive(true);
        usuario.setRole(Role.USER);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        UsuarioDTO returnDto = this.structMapper.toDto(usuarioRepository.save(usuario));
        returnDto.setPassword(null);
        returnDto.setTelefonos(usuario.getTelefonos().stream().map(telefono -> this.structMapper.toDto(telefono)).toList());

        return returnDto;
    }

    @Override
    public UsuarioDTO findById(String id) {
        return this.usuarioRepository.findById(id)
                .map(usuario -> Optional.of(this.structMapper.toDto(usuario)).map(usuarioDTO -> {
                    usuarioDTO.setPassword(null);
                    return usuarioDTO;
                }).get())
                .orElseThrow(() -> new ServiceException(this.messageSource.getMessage("usuario.id.nonexisting", null, Locale.getDefault())
                        .replace("id", id)));
    }


}
