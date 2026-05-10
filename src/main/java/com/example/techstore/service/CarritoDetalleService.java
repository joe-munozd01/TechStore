package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.CarritoDetalleDTO;
import com.example.techstore.model.CarritoDetalle;
import com.example.techstore.repository.CarritoDetalleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarritoDetalleService {

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    public List<CarritoDetalleDTO> obtenerTodos(){
        log.info("Consultando todos los detalles en la base de datos");
        return carritoDetalleRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CarritoDetalleDTO buscarPorId(Integer id){
        log.info("Buscando detalle por ID: {}", id);
        CarritoDetalle detalle = carritoDetalleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: No se encontro el detalle por ID: {}", id);
                    return new RuntimeException("Detalle no encontrado");
                });

        return convertirADTO(detalle);
    }

    public CarritoDetalle guardar(CarritoDetalle detalle){
        log.info("Guardando un nuevo detalle...");
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