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
import com.example.techstore.DTO.CarritoDTO;
import com.example.techstore.model.Carrito;
import com.example.techstore.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<?> listarCarritos(){

        List<CarritoDTO> carritos = carritoService.obtenerTodos();

        if(carritos.isEmpty()){
            return new ResponseEntity<>("No hay carritos", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCarrito(@PathVariable Integer id){

        try{

            CarritoDTO carrito = carritoService.buscarPorId(id);

            return new ResponseEntity<>(carrito, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarCarrito(@Valid @RequestBody Carrito carrito){

        try{

            return new ResponseEntity<>(carritoService.guardar(carrito), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>("No se pudo guardar el carrito", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCarrito(@PathVariable Integer id, @Valid @RequestBody Carrito carrito){

        try{
            Carrito carritoActualizado = carritoService.actualizar(id, carrito);

            return new ResponseEntity<>(carritoActualizado, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable Integer id){

        String resultado = carritoService.eliminar(id);

        if(resultado.contains("correctamente")){

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        }else{

            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}