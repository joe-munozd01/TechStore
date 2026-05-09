package com.example.techstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techstore.DTO.OrdenDTO;
import com.example.techstore.model.Orden;
import com.example.techstore.services.OrdenService;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<?> listarOrdenes(){

        List<OrdenDTO> ordenes = ordenService.obtenerTodas();

        if(ordenes.isEmpty()){
            return new ResponseEntity<>("No hay órdenes", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ordenes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarOrden(@RequestBody Orden orden){

        try{

            return new ResponseEntity<>(ordenService.guardar(orden), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar la orden", HttpStatus.BAD_REQUEST);
        }
    }
}