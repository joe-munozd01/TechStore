package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.DetalleOrdenDTO;
import com.example.techstore.model.DetalleOrden;
import com.example.techstore.repository.DetalleOrdenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public List<DetalleOrdenDTO> obtenerTodos(){
        log.info("Consultando detalles en la base de datos");
        return detalleOrdenRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DetalleOrdenDTO buscarPorId(Integer id){
        log.info("Buscando por detalles por ID: {}", id);
        DetalleOrden detalle = detalleOrdenRepository.findById(id)
                .orElseThrow(() ->  {
                    log.error("ERROR: No se encontro el detalle con ID: {}", id);
                    return new RuntimeException("Detalle no encontrado");
                });

        return convertirADTO(detalle);
    }

    public DetalleOrden guardar(DetalleOrden detalle){
        log.info("Guardando un nuevo detalle...");
        return detalleOrdenRepository.save(detalle);
    }

    private DetalleOrdenDTO convertirADTO(DetalleOrden detalle){

        DetalleOrdenDTO dto = new DetalleOrdenDTO();

        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());

        if(detalle.getProducto() != null){
            dto.setNombreProducto(detalle.getProducto().getNombre());
        }else{
            dto.setNombreProducto("Sin producto");
        }
        return dto;
    }
}