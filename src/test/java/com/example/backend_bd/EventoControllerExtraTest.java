package com.example.backend_bd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EventoControllerExtraTest {

    private EventoController controller;

    @BeforeEach
    void setUp() {
        controller = new EventoController();
    }


    @Test
    void buscarPorFecha_sinResultados() {
        List<Evento> eventos = controller.buscarEventos(
                null, null,
                "1900-01-01",  
                null, null);

        assertTrue(eventos.isEmpty(), "No debería encontrar eventos para esa fecha");
    }

    @Test
    void buscarPorNombreYFecha_sinResultados() {
        List<Evento> eventos = controller.buscarEventos(
                "GamerFest",
                null,
                "1900-01-01",
                null,
                null);

        assertTrue(eventos.isEmpty(), "No debería encontrar eventos con esa combinación");
    }

    @Test
    void buscarPorTodosLosCriterios_sinResultados() {
        List<Evento> eventos = controller.buscarEventos(
                "GamerFest",
                "Torneos",
                "1900-01-01",
                "CiudadInventada",
                "organizador‑que‑no‑existe");

        assertTrue(eventos.isEmpty(), "La lista debe estar vacía al no haber coincidencias");
    }
}
