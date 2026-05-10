package com.example.techstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.CarritoDTO;
import com.example.techstore.model.Carrito;
import com.example.techstore.model.CarritoDetalle;
import com.example.techstore.repository.CarritoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<CarritoDTO> obtenerTodos(){
        log.info("Consultando todos los carritos en la base de datos");
        return carritoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CarritoDTO buscarPorId(Integer id){
        log.info("Buscando carrito por ID: {}", id);
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() ->  {
                    log.error("ERROR: No se encontro el carrito por ID: {}", id);
                    return new RuntimeException("Carrito no encontrado");

                });

        return convertirADTO(carrito);
    }

    public Carrito guardar(Carrito carrito){
        log.info("Guardando un nuevo producto...");
        return carritoRepository.save(carrito);
    }

    private CarritoDTO convertirADTO(Carrito carrito){

        CarritoDTO dto = new CarritoDTO();

        dto.setId(carrito.getId());

        if(carrito.getUsuario() != null){
            dto.setNombreUsuario(carrito.getUsuario().getNombre());
        }else{
            dto.setNombreUsuario("Sin usuario");
        }

        if(carrito.getDetalles() != null){

            dto.setProductos(
                    carrito.getDetalles().stream()
                    .map(CarritoDetalle::getProducto)
                    .map(producto -> producto.getNombre())
                    .toList()
            );

        }else{
            dto.setProductos(new ArrayList<>());
        }
        return dto;
    }
}