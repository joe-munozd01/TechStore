package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.CarritoDetalleDTO;
import com.example.techstore.model.CarritoDetalle;
import com.example.techstore.repository.CarritoDetalleRepository;

@Service
public class CarritoDetalleService {

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    public List<CarritoDetalleDTO> obtenerTodos(){

        return carritoDetalleRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CarritoDetalleDTO buscarPorId(Integer id){

        CarritoDetalle detalle = carritoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        return convertirADTO(detalle);
    }

    public CarritoDetalle guardar(CarritoDetalle detalle){

        return carritoDetalleRepository.save(detalle);
    }

    private CarritoDetalleDTO convertirADTO(CarritoDetalle detalle){

        CarritoDetalleDTO dto = new CarritoDetalleDTO();

        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());

        if(detalle.getProducto() != null){
            dto.setNombreProducto(detalle.getProducto().getNombre());
        }else{
            dto.setNombreProducto("Sin producto");
        }
        return dto;
    }
}