package org.poty.proyectoevento.core.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
public class ValidacionFecha {
    public static boolean validarFechaPrevioHoy(Date fecha){
        if (new Date().before(fecha)){
            return true;
        }
        return false;
    }

    public static boolean validarFechaPosteriorHoy(Date fecha){
        if (new Date().after(fecha)){
            return true;
        }
        return false;
    }

    public static boolean validarFechaPosteriorHoy(LocalDateTime fecha){
        LocalDateTime hoy = LocalDateTime.now();
        if (LocalDateTime.now().isBefore((fecha))){
            return true;
        }
        return false;
    }

    public static boolean validarFechaRango(Date fechaPrevia, Date fechaPosterior){
        if (fechaPrevia.before(fechaPosterior)){
            return false;
        }
        return true;
    }

    public static boolean validarFechaRango(LocalDateTime fechaPrevia, LocalDateTime fechaPosterior){
        if (fechaPrevia.isBefore(fechaPosterior)){
            return false;
        }
        return true;
    }

    public static boolean validarFechaEnRango(Date fechaPrevia, Date fechaPosterior,Date fecha){
        if ((fechaPrevia.before(fecha) || fechaPrevia.equals(fecha)) && (fechaPosterior.after(fecha) || fechaPosterior.equals(fecha))){
            return true;
        }
        return false;
    }

    public static boolean validarFechaEnRango(LocalDateTime fechaPrevia, LocalDateTime fechaPosterior,LocalDateTime fecha){
        if ((fechaPrevia.isBefore(fecha) || fechaPrevia.equals(fecha)) && (fechaPosterior.isAfter(fecha) || fechaPosterior.equals(fecha))){
            return true;
        }
        return false;
    }
}
