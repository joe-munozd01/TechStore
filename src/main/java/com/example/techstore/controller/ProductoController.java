package com.example.techstore.controller;

import java.util.List;
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
import com.example.techstore.DTO.ProductoDTO;
import com.example.techstore.model.Producto;
import com.example.techstore.service.TechService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final TechService techService;

    public ProductoController(TechService techService) {
        this.techService = techService;
    }

    @GetMapping
    public ResponseEntity<?> listarProductos(){

        List<ProductoDTO> productos = techService.obtenerTodos();

        if(productos.isEmpty()){
            return new ResponseEntity<>("No hay productos", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Integer id){

        try{

            ProductoDTO producto = techService.buscarPorId(id);

            return new ResponseEntity<>(producto, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDTO productoDTO){

        try{

            return new ResponseEntity<>(techService.guardaProducto(productoDTO), HttpStatus.CREATED);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer id,
                                                @RequestBody Producto producto){

        try{

            Producto productoActualizado = techService.actualizarProducto(id, producto);

            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);

        }catch(RuntimeException e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id){

        String resultado = techService.eliminar(id);

        if(resultado.contains("correctamente")){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}