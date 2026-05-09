package com.example.techstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techstore.DTO.MarcaDTO;
import com.example.techstore.model.Marca;
import com.example.techstore.services.MarcaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> listarMarcas(){

        List<MarcaDTO> marcas = marcaService.obtenerTodas();

        if(marcas.isEmpty()){
            return new ResponseEntity<>("No hay marcas", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMarca(@PathVariable Integer id){

        try{

            MarcaDTO marca = marcaService.buscarPorId(id);

            return new ResponseEntity<>(marca, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarMarca(@Valid @RequestBody Marca marca){

        try{

            return new ResponseEntity<>(marcaService.guardar(marca), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar la marca", HttpStatus.BAD_REQUEST);
        }
    }
}