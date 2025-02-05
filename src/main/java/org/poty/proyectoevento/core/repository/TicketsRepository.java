package org.poty.proyectoevento.core.repository;

import org.poty.proyectoevento.core.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketsRepository  extends JpaRepository<Ticket, Long> {
    @Override
    Optional<Ticket> findById(Long aLong);
}
