package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.DetalleOrdenDTO;
import com.example.techstore.model.DetalleOrden;
import com.example.techstore.repository.DetalleOrdenRepository;

@Service
public class DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public List<DetalleOrdenDTO> obtenerTodos(){

        return detalleOrdenRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DetalleOrdenDTO buscarPorId(Integer id){

        DetalleOrden detalle = detalleOrdenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        return convertirADTO(detalle);
    }

    public DetalleOrden guardar(DetalleOrden detalle){

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