package org.poty.proyectoevento.core.service;

import org.poty.proyectoevento.core.model.Ticket;

public interface TicketService {
    public void venderTicket(Long id_evento) throws Exception;

    public void canjearTicket(Long id_ticket) throws Exception;
}
