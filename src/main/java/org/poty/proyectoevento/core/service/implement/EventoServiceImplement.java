package org.poty.proyectoevento.core.service.implement;

import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.repository.EventoRepository;
import org.poty.proyectoevento.core.service.EventoService;
import org.poty.proyectoevento.core.utils.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImplement implements EventoService {
    private EventoRepository eventoRepository;

    @Autowired
    public EventoServiceImplement(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public void insertarEvento(Evento evento) throws Exception {
        Validacion.validarElemento(evento);
        eventoRepository.save(evento);
    }

    @Override
    public void modificarEvento(Evento evento) throws Exception {
        if (eventoRepository.existsById(evento.getId())){
            if (eventoRepository.findById(evento.getId()).get().getBoletos_vendidos()<= evento.getCantidad_boletos()){
                Validacion.validarElemento(evento);
                eventoRepository.save(evento);
            }else {
                throw new Exception("El evento no se puede modificar porque ya cantidad de boletos vendidos es mayor que la nueva cantidad de boletos");
            }
        }else {
            throw new Exception("El evento a modificar no existe");
        }
    }

    @Override
    public void eliminarEvento(Long evento) throws Exception {
        if (eventoRepository.existsById(evento)){
            eventoRepository.deleteById(evento);
        }else {
            throw new Exception("El evento a eliminar no existe");
        }
    }

    @Override
    public List<Evento> listarEventos() throws Exception {
        return eventoRepository.findAll();
    }

    @Override
    public List<Evento> listarEventosActivos() throws Exception {
        return eventoRepository.findAll().stream()
                .filter(Evento::isEstado).toList();
    }

    @Override
    public List<Evento> listarEventosInactivos() throws Exception {
        return eventoRepository.findAll().stream()
                .filter(evento -> !evento.isEstado()).toList();
    }
}
