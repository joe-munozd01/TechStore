package com.example.techstore.DTO;
import lombok.Data;

@Data
public class CarritoDetalleDTO {

    private Integer id;
    private Integer cantidad;

    private String nombreProducto;
}