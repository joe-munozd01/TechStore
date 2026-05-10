package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.ProductoDTO;
import com.example.techstore.model.Producto;
import com.example.techstore.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

    @Autowired
    public ProductoRepository productoRepository;

    public List<ProductoDTO> obtenerTOdos() {
        log.info("Consultando todos los productos en la base de datos");
        return productoRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public ProductoDTO buscarPorId(Integer id) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> {
                log.error("ERROR: No se encontro el producto por ID: {}", id);
                return new RuntimeException("Producto no encontrado");
            });

        return convertirADTO(producto);

    }

    public Producto guardar(Producto producto) {
        log.info("Guardando un nuevo producto...");
        return productoRepository.save(producto);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        
        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());

        if(producto.getCategoria() != null) {
            dto.setNombreCategoria(producto.getCategoria().getNombre());

        }else{
            dto.setNombreCategoria("Sin categoria");
        }

        if(producto.getMarca() != null) {
            dto.setNombreMarca(producto.getMarca().getNombre());

        }else{
            dto.setNombreMarca("Sin marca");
        }

        return dto;
    }
}