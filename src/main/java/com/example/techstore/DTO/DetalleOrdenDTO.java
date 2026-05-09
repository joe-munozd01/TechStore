package com.example.techstore.DTO;

import lombok.Data;

@Data
public class DetalleOrdenDTO {

    private Integer id;
    private Integer cantidad;
    private Integer subtotal;

    private String nombreProducto;
}