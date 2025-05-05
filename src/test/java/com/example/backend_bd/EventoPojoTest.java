package com.example.backend_bd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("POJO – Evento")
class EventoPojoTest {

    @Test
    void gettersAndSetters_work() {
        Categoria cat = new Categoria();
        cat.setNombre("Torneos");

        Evento e = new Evento();
        e.setId(99);
        e.setNombre("Liga Primavera");
        e.setDescripcion("Demostración");
        e.setCategoria(cat);

        assertAll(
            () -> assertEquals(99, e.getId()),
            () -> assertEquals("Liga Primavera", e.getNombre()),
            () -> assertEquals("Demostración", e.getDescripcion()),
            () -> assertEquals("Torneos", e.getCategoria().getNombre())
        );
    }
}
