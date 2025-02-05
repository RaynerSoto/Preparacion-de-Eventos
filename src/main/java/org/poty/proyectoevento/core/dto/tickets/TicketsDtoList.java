package org.poty.proyectoevento.core.dto.tickets;

import org.poty.proyectoevento.core.model.Ticket;

import java.time.LocalDateTime;

public record TicketsDtoList (Long id,
                             String nombre_evento,
                             String codigo,
                             LocalDateTime fechaCanjeo,
                             boolean isCanjeo){
    public static TicketsDtoList convertirTickets(Ticket ticket){
        return new TicketsDtoList(ticket.getId(), ticket.getEvento().getNombre(), ticket.getCodigo(), ticket.getFecha_canjeo(), ticket.isCanjeo());
    }
}
