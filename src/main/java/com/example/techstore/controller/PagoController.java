package com.example.techstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.techstore.DTO.PagoDTO;
import com.example.techstore.model.Pago;
import com.example.techstore.service.PagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> listarPagos(){

        List<PagoDTO> pagos = pagoService.obtenerTodos();

        if(pagos.isEmpty()){
            return new ResponseEntity<>("No hay pagos", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarPago(@Valid @RequestBody Pago pago){

        try{

            return new ResponseEntity<>(pagoService.guardar(pago), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar el pago", HttpStatus.BAD_REQUEST);
        }
    }
}