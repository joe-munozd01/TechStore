package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.PagoDTO;
import com.example.techstore.model.Pago;
import com.example.techstore.repository.PagoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> obtenerTodos(){
        log.info("Buscando todos los pagos en la base de datos...");
        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Integer id){
        log.info("Buscando pago con el ID: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: El pago con ID {} no existe en la base de datos");
                    return new RuntimeException("Pago no encontrado");
                });
        return convertirADTO(pago);
    }

    public Pago guardar(Pago pago){
        log.info("");
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