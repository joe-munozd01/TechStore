package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.PagoDTO;
import com.example.techstore.model.Pago;
import com.example.techstore.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> obtenerTodos(){

        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Integer id){

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        return convertirADTO(pago);
    }

    public Pago guardar(Pago pago){

        return pagoRepository.save(pago);
    }

    private PagoDTO convertirADTO(Pago pago){

        PagoDTO dto = new PagoDTO();

        dto.setId(pago.getId());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());

        return dto;
    }
}