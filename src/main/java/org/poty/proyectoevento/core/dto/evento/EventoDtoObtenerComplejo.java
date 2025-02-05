package org.poty.proyectoevento.core.dto.evento;

import org.poty.proyectoevento.core.dto.tickets.TicketsDto;
import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public record EventoDtoObtenerComplejo (Long id,
                                        String nombre,
                                        LocalDateTime fecha_inicio,
                                        LocalDateTime fecha_fin,
                                        String descripcion,
                                        int cantidad_boletos,
                                        int boletos_vendidos,
                                        List<TicketsDto> tickets) {
    public static EventoDtoObtenerComplejo convertirEventoAEventoDtoObtener(Evento evento){
        return new EventoDtoObtenerComplejo(
                evento.getId(),evento.getNombre(),evento.getFecha_inicio(),evento.getFecha_fin(),evento.getDescripcion(), evento.getCantidad_boletos(), evento.getBoletos_vendidos(),
                evento.getTickets().stream().map(TicketsDto::convertirTicketATicketDto).toList());
    }
}