package org.poty.proyectoevento.core.service.implement;

import jdk.jfr.Event;
import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.model.Ticket;
import org.poty.proyectoevento.core.repository.EventoRepository;
import org.poty.proyectoevento.core.repository.TicketsRepository;
import org.poty.proyectoevento.core.service.EventoService;
import org.poty.proyectoevento.core.service.TicketService;
import org.poty.proyectoevento.core.utils.ValidacionFecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketServiceImplement implements TicketService {

    private EventoService eventoService;
    private EventoRepository eventoRepository;
    private TicketsRepository ticketsRepository;

    @Autowired
    public TicketServiceImplement(EventoService eventoService, EventoRepository eventoRepository, TicketsRepository ticketsRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
        this.ticketsRepository = ticketsRepository;
    }

    @Override
    public void venderTicket(Long id_evento) throws Exception{
        if (eventoRepository.existsById(id_evento)) {
            Evento evento = eventoRepository.findById(id_evento).get();
            ticketsRepository.save(new Ticket(evento));
        }else {
            throw new Exception("El evento no existe");
        }
    }

    @Override
    public void canjearTicket(Long id_ticket) throws Exception {
        if (eventoRepository.existsById(id_ticket)) {
            Ticket ticket = ticketsRepository.findById(id_ticket);
            if (ValidacionFecha.validarFechaEnRango(ticket.getEvento().getFecha_inicio(), ticket.getEvento().getFecha_fin(), LocalDateTime.now())) {
                ticket.setCanjeo(true);
                ticket.setFecha_canjeo(LocalDateTime.now());
                ticketsRepository.save(ticket);
            }
            else {
                throw new Exception("La fecha de canjeo del ticket del evento no es válida");
            }
        }
        else {
            throw new Exception("El ticket seleccionado no existe");
        }
    }
}
