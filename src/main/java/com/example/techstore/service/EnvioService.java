package com.example.techstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.techstore.DTO.EnvioDTO;
import com.example.techstore.model.Envio;
import com.example.techstore.repository.EnvioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<EnvioDTO> obtenerTodos() {
        log.info("Buscando todos los envios en la base de datos...");
        return envioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public EnvioDTO buscarPorId(Integer id) {
        log.info("Buscando envio con ID: {}", id);
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: No se encontro el envio con ID: {}", id);
                    return new RuntimeException("Envio no encontrado");
                });
        return convertirADTO(envio);
    }

    public Envio guardar(Envio envio) {
        log.info("Guardando nuevo envio con código de seguimiento: {}", envio.getCodigoSeguimiento());
        return envioRepository.save(envio);
    }

    private EnvioDTO convertirADTO(Envio envio) {
        EnvioDTO dto = new EnvioDTO();
        dto.setId(envio.getId());
        dto.setDireccion(envio.getDireccion());
        dto.setEstado(envio.getEstado());
        dto.setCodigoSeguimiento(envio.getCodigoSeguimiento());

        if(envio.getOrden() != null) {
            dto.setIdOrden(envio.getOrden().getId());
        }
        return dto;
    }

}
