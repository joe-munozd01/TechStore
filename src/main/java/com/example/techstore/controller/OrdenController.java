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
import com.example.techstore.DTO.OrdenDTO;
import com.example.techstore.model.Orden;
import com.example.techstore.service.OrdenService;

import jakarta.validation.Valid;

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
    public ResponseEntity<?> guardarOrden(@Valid @RequestBody Orden orden){

        try{

            return new ResponseEntity<>(ordenService.guardar(orden), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar la orden", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarOrden(@PathVariable Integer id, @Valid @RequestBody Orden orden){

        try{

            Orden ordenActualizada = ordenService.actualizar(id, orden);

            return new ResponseEntity<>(ordenActualizada, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrden(@PathVariable Integer id){

        String resultado = ordenService.eliminar(id);

        if(resultado.contains("correctamente")){

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        }else{

            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}