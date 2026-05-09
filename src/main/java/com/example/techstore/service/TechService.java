package com.example.techstore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.ProductoDTO;
import com.example.techstore.model.Categoria;
import com.example.techstore.model.Marca;
import com.example.techstore.model.Producto;
import com.example.techstore.repository.CategoriaRepository;
import com.example.techstore.repository.MarcaRepository;
import com.example.techstore.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TechService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public TechService(ProductoRepository productoRepository,
                       MarcaRepository marcaRepository,
                       CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProductoDTO> obtenerTodos(){

        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductoDTO buscarPorId(Integer id){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return convertirADTO(producto);
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

    public Producto actualizarProducto(Integer id, Producto productoActualizado){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if(productoActualizado.getNombre() != null){
            producto.setNombre(productoActualizado.getNombre());
        }

        if(productoActualizado.getDescripcion() != null){
            producto.setDescripcion(productoActualizado.getDescripcion());
        }

        if(productoActualizado.getPrecio() != null){
            producto.setPrecio(productoActualizado.getPrecio());
        }

        return productoRepository.save(producto);
    }

    public String eliminar(Integer id){

        try{

            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            productoRepository.delete(producto);

            return "Producto eliminado correctamente";

        }catch(RuntimeException e){

            return e.getMessage();
        }
    }

    private ProductoDTO convertirADTO(Producto producto){

        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setDescripcion(producto.getDescripcion());

        if(producto.getCategoria() != null){
            dto.setNombreCategoria(producto.getCategoria().getNombre());
        }else{
            dto.setNombreCategoria("Sin categoria");
        }

        if(producto.getMarca() != null){
            dto.setNombreMarca(producto.getMarca().getNombre());
        }else{
            dto.setNombreMarca("Sin marca");
        }

        return dto;
    }
}