package com.example.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.techstore.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer>{

}
