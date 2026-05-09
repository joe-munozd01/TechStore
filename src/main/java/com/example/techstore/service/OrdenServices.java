package com.example.techstore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.OrdenDTO;
import com.example.techstore.model.Orden;
import com.example.techstore.repository.OrdenRepository;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<OrdenDTO> obtenerTodas(){

        return ordenRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public OrdenDTO buscarPorId(Integer id){

        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        return convertirADTO(orden);
    }

    public Orden guardar(Orden orden){

        return ordenRepository.save(orden);
    }

    private OrdenDTO convertirADTO(Orden orden){

        OrdenDTO dto = new OrdenDTO();

        dto.setId(orden.getId());
        dto.setTotal(orden.getTotal());

        if(orden.getUsuario() != null){
            dto.setNombreUsuario(orden.getUsuario().getNombre());
        }else{
            dto.setNombreUsuario("Sin usuario");
        }
        return dto;
    }
}