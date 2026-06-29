package com.example.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.techstore.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer>{
}