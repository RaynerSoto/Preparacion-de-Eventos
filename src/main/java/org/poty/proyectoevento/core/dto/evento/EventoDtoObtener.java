package org.poty.proyectoevento.core.dto.evento;

import org.poty.proyectoevento.core.model.Evento;

import java.time.LocalDateTime;

public record EventoDtoObtener(Long id,
                               String nombre,
                               LocalDateTime fecha_inicio,
                               LocalDateTime fecha_fin,
                               String descripcion,
                               int cantidad_boletos,
                               int boletos_vendidos) {
    public static EventoDtoObtener convertirEventoAEventoDtoObtener(Evento evento){
        return new EventoDtoObtener(
                evento.getId(),evento.getNombre(),evento.getFecha_inicio(),evento.getFecha_fin(),evento.getDescripcion(), evento.getCantidad_boletos(), evento.getBoletos_vendidos()
        );
    }
}
