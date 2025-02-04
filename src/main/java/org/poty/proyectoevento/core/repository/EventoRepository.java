package org.poty.proyectoevento.core.repository;

import org.poty.proyectoevento.core.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento,Long> {
    Optional<Evento> findById(long id);

    boolean existsById(long id);

    Long id(long id);
}
