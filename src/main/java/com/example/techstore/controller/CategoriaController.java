package com.example.techstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techstore.DTO.CategoriaDTO;
import com.example.techstore.model.Categoria;
import com.example.techstore.services.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listarCategorias(){

        List<CategoriaDTO> categorias = categoriaService.obtenerTodas();

        if(categorias.isEmpty()){
            return new ResponseEntity<>("No hay categorías", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Integer id){

        try{

            CategoriaDTO categoria = categoriaService.buscarPorId(id);

            return new ResponseEntity<>(categoria, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarCategoria(@Valid @RequestBody Categoria categoria){

        try{

            return new ResponseEntity<>(categoriaService.guardar(categoria), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar la categoría", HttpStatus.BAD_REQUEST);
        }
    }
}