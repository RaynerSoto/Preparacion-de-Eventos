package org.poty.proyectoevento.core.utils;

import org.poty.proyectoevento.core.model.Evento;

public class ValidacionEvento {

    public static void validarEvento(Evento evento){
        Validacion.validarElemento(evento);
        if (!ValidacionFecha.validarFechaPosteriorHoy(evento.getFecha_inicio())) {
            throw new IllegalArgumentException("Las fechas de inicio debe ser posterior al día de hoy");
        }
        if (ValidacionFecha.validarFechaRango(evento.getFecha_inicio(), evento.getFecha_fin())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
    }
}
