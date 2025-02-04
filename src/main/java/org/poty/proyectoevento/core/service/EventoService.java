package org.poty.proyectoevento.core.service;

import org.poty.proyectoevento.core.model.Evento;

import java.util.List;

public interface EventoService {
    public void insertarEvento(Evento evento) throws Exception;

    public void modificarEvento(Evento evento) throws Exception;

    public void eliminarEvento(Long evento) throws Exception;

    public List<Evento> listarEventos() throws Exception;

    public List<Evento> listarEventosActivos() throws Exception;

    public List<Evento> listarEventosInactivos() throws Exception;
}
