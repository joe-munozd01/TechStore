package com.example.techstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.techstore.DTO.EnvioDTO;
import com.example.techstore.model.Envio;
import com.example.techstore.service.EnvioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<?> buscarEnvio(@PathVariable Integer id) {
        try{
            EnvioDTO envio = envioService.buscarPorId(id);
            return new ResponseEntity<>(envio, HttpStatus.OK);
        }catch(RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarEnvio(@Valid @RequestBody Envio envio) {
        try{
            return new ResponseEntity<>(envioService.guardar(envio), HttpStatus.CREATED);
        }catch(RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
