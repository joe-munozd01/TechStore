package com.example.techstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.DTO.MarcaDTO;
import com.example.techstore.model.Marca;
import com.example.techstore.model.Producto;
import com.example.techstore.repository.MarcaRepository;

@Service
public class MarcaServices {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaDTO> obtenerTodas() {

        return marcaRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
        
    }

    public MarcaDTO buscarPorId(Integer id) {

        Marca marca = marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        
        return convertirADTO(marca);
    }

    private MarcaDTO convertirADTO(Marca marca) {

        MarcaDTO dto = new MarcaDTO();

        dto.setId(marca.getId());
        dto.setNombre(marca.getNombre());

        if(marca.getProductos() != null) {

            dto.setNombresProductos(
                marca.getProductos().stream()
                .map(Producto::getNombre)
                .toList()
            );
        }else{
            dto.setNombresProductos(new ArrayList<>());
        }

        return dto;

    }

}
