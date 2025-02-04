package org.poty.proyectoevento.core.repository;

import org.poty.proyectoevento.core.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketsRepository  extends CrudRepository<Ticket, Integer> {
    Ticket findById(Long id);
}
