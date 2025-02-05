package org.poty.proyectoevento.core.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidacionFechaTest {

    @Test
    void validarFechaRango() {
        LocalDateTime fecha_inicio = LocalDateTime.now(),fecha_fin = LocalDateTime.now().plusDays(4);
        assertTrue(ValidacionFecha.validarFechaRango(fecha_inicio,fecha_fin));
    }

    @Test
    void validarFechaPosteriorHoy() {
        LocalDateTime fecha_inicio = LocalDateTime.now().plusDays(4);
        assertTrue(ValidacionFecha.validarFechaPosteriorHoy(fecha_inicio));
    }
}