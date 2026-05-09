package com.example.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.techstore.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
}