package com.example.techstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.DTO.CategoriaDTO;
import com.example.techstore.model.Categoria;
import com.example.techstore.model.Producto;
import com.example.techstore.repository.CategoriaRepository;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> obtenerTodas() {

        return categoriaRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();

    }

    public CategoriaDTO buscarPorId(Integer id) {

        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        
        return convertirADTO(categoria);

    }

    public Categoria guardar(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    private CategoriaDTO convertirADTO(Categoria categoria) {

        CategoriaDTO dto = new CategoriaDTO();

        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());

        if(categoria.getProductos() != null) {

            dto.setNombresProductos(
                categoria.getProductos().stream()
                .map(Producto::getNombre)
                .toList()
            );

        }else{
            dto.setNombresProductos(new ArrayList<>());
        }

        return dto;

    }

}
