
package com.example.backend_bd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventoControllerTest {

    private EventoController controller;

    @BeforeEach
    public void setUp() {
        controller = new EventoController();
    }

    @Test
    public void testGetEventos() {
        List<Evento> eventos = controller.getEventos();
        assertNotNull(eventos);
        assertTrue(eventos.size() > 0);
    }

    @Test
    public void testBuscarEventosPorNombre() {
        List<Evento> resultados = controller.buscarEventos("GamerFest", null, null, null, null);
        assertFalse(resultados.isEmpty());
        assertTrue(resultados.get(0).getNombre().contains("GamerFest"));
    }

    @Test
    public void testBuscarEventosPorCategoria() {
        List<Evento> resultados = controller.buscarEventos(null, "Torneos", null, null, null);
        assertFalse(resultados.isEmpty());
        assertEquals("Torneos", resultados.get(0).getCategoria().getNombre());
    }

    @Test
    public void testBuscarEventosPorUbicacion() {
        List<Evento> resultados = controller.buscarEventos(null, null, null, "Santiago", null);
        assertFalse(resultados.isEmpty());
    }

    @Test
    public void testGetEventoExistente() {
        Evento evento = controller.getEvento(1);
        assertNotNull(evento);
        assertEquals(1, evento.getId());
    }

    @Test
    public void testGetEventoInexistente() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.getEvento(999);
        });
        assertEquals("Evento no encontrado", exception.getMessage());
    }
}
