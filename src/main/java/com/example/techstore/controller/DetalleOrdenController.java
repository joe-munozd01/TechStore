package com.example.techstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.techstore.DTO.DetalleOrdenDTO;
import com.example.techstore.model.DetalleOrden;
import com.example.techstore.services.DetalleOrdenService;

@RestController
@RequestMapping("/api/v1/detalle-orden")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @GetMapping
    public ResponseEntity<?> listarDetalles(){

        List<DetalleOrdenDTO> detalles = detalleOrdenService.obtenerTodos();

        if(detalles.isEmpty()){
            return new ResponseEntity<>("No hay detalles", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarDetalle(@RequestBody DetalleOrden detalle){

        try{

            return new ResponseEntity<>(detalleOrdenService.guardar(detalle), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar el detalle", HttpStatus.BAD_REQUEST);
        }
    }
}