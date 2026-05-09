package com.example.techstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techstore.DTO.UsuarioDTO;
import com.example.techstore.model.Usuario;
import com.example.techstore.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listarUsuarios(){

        List<UsuarioDTO> usuarios = usuarioService.obtenerTodos();

        if(usuarios.isEmpty()){
            return new ResponseEntity<>("No hay usuarios", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer id){

        try{

            UsuarioDTO usuario = usuarioService.buscarPorId(id);

            return new ResponseEntity<>(usuario, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario){

        try{

            return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar el usuario", HttpStatus.BAD_REQUEST);
        }
    }
}