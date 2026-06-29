package com.example.techstore.DTO;

import java.util.List;
import lombok.Data;

@Data
public class CarritoDTO {

    private Integer id;
    private String nombreUsuario;

    private List<String> productos;
}