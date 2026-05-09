package com.example.techstore.service;


import org.springframework.stereotype.Service;
import com.example.techstore.DTO.ProductoDTO;
import com.example.techstore.model.Categoria;
import com.example.techstore.model.Marca;
import com.example.techstore.model.Producto;
import com.example.techstore.repository.CategoriaRepository;
import com.example.techstore.repository.MarcaRepository;
import com.example.techstore.repository.ProductoRepository;

@Service
public class TechService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public TechService(ProductoRepository productoRepository, MarcaRepository marcaRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this. categoriaRepository = categoriaRepository;

    }

    public Producto guardaProducto(ProductoDTO productoDTO) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoDTO.getNombre());
        nuevoProducto.setPrecio(productoDTO.getPrecio());
        nuevoProducto.setDescripcion(productoDTO.getDescripcion());

        Categoria categoriaEncontrada = categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Error: La categoria no existe"));

        Marca marcaEncontrada = marcaRepository.findById(productoDTO.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Error: La marca no existe"));

        nuevoProducto.setCategoria(categoriaEncontrada);
        nuevoProducto.setMarca(marcaEncontrada);

        return productoRepository.save(nuevoProducto);
    }
}