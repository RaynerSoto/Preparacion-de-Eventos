package org.poty.proyectoevento.core.dto.evento;

import java.time.LocalDateTime;
import java.util.Date;

public record EventoDtoInsert(
        String nombre,
        LocalDateTime fecha_inicio,
        LocalDateTime fecha_fin,
        String descripcion,
        int cantidad_boletos
){}