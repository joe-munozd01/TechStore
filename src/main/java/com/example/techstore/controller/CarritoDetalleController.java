package com.example.techstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.techstore.DTO.CarritoDetalleDTO;
import com.example.techstore.model.CarritoDetalle;
import com.example.techstore.service.CarritoDetalleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carrito-detalle")
public class CarritoDetalleController {

    @Autowired
    private CarritoDetalleService carritoDetalleService;

    @GetMapping
    public ResponseEntity<?> listarDetalles(){

        List<CarritoDetalleDTO> detalles = carritoDetalleService.obtenerTodos();

        if(detalles.isEmpty()){
            return new ResponseEntity<>("No hay detalles", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarDetalle(@Valid @RequestBody CarritoDetalle detalle){

        try{

            return new ResponseEntity<>(carritoDetalleService.guardar(detalle), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar el detalle", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDetalle(@PathVariable Integer id, @RequestBody CarritoDetalle detalle) {
        try {
            return new ResponseEntity<>(carritoDetalleService.actualizar(id, detalle), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Integer id) {
        try {
            String mensaje = carritoDetalleService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}