package com.Leandro.usuario.infrastructure.business;


import com.Leandro.usuario.infrastructure.business.converter.UsuarioConverter;
import com.Leandro.usuario.infrastructure.business.dto.UsuarioDTO;
import com.Leandro.usuario.infrastructure.entity.Usuario;
import com.Leandro.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }



}
