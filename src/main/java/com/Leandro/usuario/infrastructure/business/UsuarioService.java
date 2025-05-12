package com.Leandro.usuario.infrastructure.business;


import com.Leandro.usuario.infrastructure.business.converter.UsuarioConverter;
import com.Leandro.usuario.infrastructure.business.dto.UsuarioDTO;
import com.Leandro.usuario.infrastructure.entity.Usuario;
import com.Leandro.usuario.infrastructure.exceptions.ConflictException;
import com.Leandro.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.Leandro.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email ja cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado" + e.getCause());
        }
    }

    public  boolean verificaEmailExistente(String email){
        return  usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return  usuarioRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException
                ("Email não encontrado" + email));
    }


    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }




}
