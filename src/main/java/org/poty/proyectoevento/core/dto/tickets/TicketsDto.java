package org.poty.proyectoevento.core.dto.tickets;

import org.poty.proyectoevento.core.model.Ticket;

import java.time.LocalDateTime;

public record TicketsDto(Long id,
                         String codigo,
                         LocalDateTime fechaCanjeo,
                         boolean isCanjeo) {
    public static TicketsDto convertirTicketATicketDto(Ticket ticket){
        return new TicketsDto(ticket.getId(),ticket.getCodigo(),ticket.getFecha_canjeo(), ticket.isCanjeo());
    }
}
