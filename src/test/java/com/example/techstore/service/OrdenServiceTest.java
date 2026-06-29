package com.example.techstore.service;

import com.example.techstore.DTO.OrdenDTO;
import com.example.techstore.model.Orden;
import com.example.techstore.model.Usuario;
import com.example.techstore.repository.OrdenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdenServiceTest {

    @Mock
    private OrdenRepository ordenRepository;

    @InjectMocks
    private OrdenService ordenService;

    private Orden ordenEjemplo;
    private Usuario usuarioEjemplo;

    @BeforeEach
    void setUp() {
        usuarioEjemplo = new Usuario();
        usuarioEjemplo.setId(1);
        usuarioEjemplo.setNombre("Felipe Baez");
        usuarioEjemplo.setCorreo("felipe@techstore.cl");

        ordenEjemplo = new Orden();
        ordenEjemplo.setId(1);
        ordenEjemplo.setTotal(355000);
        ordenEjemplo.setUsuario(usuarioEjemplo);
    }

    @Test
    @DisplayName("obtenerTodas() debe retornar lista de OrdenDTO con nombre de usuario")
    void obtenerTodas_conOrdenesExistentes_retornaListaDTO() {

        when(ordenRepository.findAll()).thenReturn(List.of(ordenEjemplo));

        List<OrdenDTO> resultado = ordenService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(355000, resultado.get(0).getTotal());
        assertEquals("Felipe Baez", resultado.get(0).getNombreUsuario());
    }

    @Test
    @DisplayName("buscarPorId() debe retornar OrdenDTO cuando el ID existe")
    void buscarPorId_conIdExistente_retornaDTO() {

        when(ordenRepository.findById(1)).thenReturn(Optional.of(ordenEjemplo));

        OrdenDTO resultado = ordenService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(355000, resultado.getTotal());
    }

    @Test
    @DisplayName("buscarPorId() debe lanzar RuntimeException cuando la orden no existe")
    void buscarPorId_conIdInexistente_lanzaException() {

        when(ordenRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ordenService.buscarPorId(99));
        assertEquals("Orden no encontrada", ex.getMessage());
    }

    @Test
    @DisplayName("actualizar() debe modificar el total cuando se provee un nuevo valor")
    void actualizar_conNuevoTotal_actualizaCorrectamente() {

        Orden cambios = new Orden();
        cambios.setTotal(500000);

        when(ordenRepository.findById(1)).thenReturn(Optional.of(ordenEjemplo));
        when(ordenRepository.save(any(Orden.class))).thenReturn(ordenEjemplo);

        Orden resultado = ordenService.actualizar(1, cambios);

        assertEquals(500000, resultado.getTotal());
        verify(ordenRepository, times(1)).save(ordenEjemplo);
    }

    @Test
    @DisplayName("actualizar() debe lanzar RuntimeException cuando la orden no existe")
    void actualizar_conIdInexistente_lanzaException() {

        when(ordenRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ordenService.actualizar(99, new Orden()));
        assertEquals("Orden no encontrada", ex.getMessage());
        verify(ordenRepository, never()).save(any());
    }

    @Test
    @DisplayName("eliminar() debe retornar mensaje de exito para orden existente")
    void eliminar_conIdExistente_retornaMensajeExito() {

        when(ordenRepository.findById(1)).thenReturn(Optional.of(ordenEjemplo));
        doNothing().when(ordenRepository).delete(ordenEjemplo);

        String resultado = ordenService.eliminar(1);

        assertEquals("Orden eliminada correctamente", resultado);
        verify(ordenRepository, times(1)).delete(ordenEjemplo);
    }

    @Test
    @DisplayName("eliminar() debe lanzar RuntimeException cuando la orden no existe")
    void eliminar_conIdInexistente_lanzaException() {

        when(ordenRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ordenService.eliminar(99));
        verify(ordenRepository, never()).delete(any());
    }

    @Test
    @DisplayName("DTO debe mostrar 'Sin usuario' cuando la orden no tiene usuario asociado")
    void obtenerTodas_ordenSinUsuario_usaValorPorDefecto() {

        Orden sinUsuario = new Orden();
        sinUsuario.setId(2);
        sinUsuario.setTotal(75000);
        sinUsuario.setUsuario(null);

        when(ordenRepository.findAll()).thenReturn(List.of(sinUsuario));

        List<OrdenDTO> resultado = ordenService.obtenerTodas();

        assertEquals("Sin usuario", resultado.get(0).getNombreUsuario());
        assertEquals(75000, resultado.get(0).getTotal());
    }
}