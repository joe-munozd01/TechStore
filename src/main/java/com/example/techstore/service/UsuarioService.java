package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.UsuarioDTO;
import com.example.techstore.model.Usuario;
import com.example.techstore.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obtenerTodos(){

        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public UsuarioDTO buscarPorId(Integer id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertirADTO(usuario);
    }

    public Usuario guardar(Usuario usuario){

        return usuarioRepository.save(usuario);
    }

    private UsuarioDTO convertirADTO(Usuario usuario){

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());

        return dto;
    }
}