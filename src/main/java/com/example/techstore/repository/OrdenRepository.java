package com.example.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.techstore.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{
}