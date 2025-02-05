package org.poty.proyectoevento.core.repository;

import org.poty.proyectoevento.core.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketsRepository  extends CrudRepository<Ticket, Long> {
    @Override
    Optional<Ticket> findById(Long aLong);
}
