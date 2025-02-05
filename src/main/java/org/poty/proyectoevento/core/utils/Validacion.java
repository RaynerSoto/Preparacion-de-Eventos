package org.poty.proyectoevento.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validacion{
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Validacion(){}

    /**
     * @apiNote {@summary = Función para listar las valiciones de los elemntos no cumplidas}
     * @exception UnsupportedOperationException
     * @param elemento
     * return Set
     */
    public static <T> Set<String> listarValidacionesNoCumplidastoSet(T elemento){
        return validator.validate(elemento).
                stream().map(con -> con.getMessage())
                .collect(Collectors.toSet());
    }

    /**
     * @apiNote {@summary = Función para listar las valiciones de los elemntos no cumplidas}
     * @exception UnsupportedOperationException
     * @param elemento
     * return List
     */
    public static <T> List<String> listarValidacionesNoCumplidastoList(T elemento){
        return validator.validate(elemento).
                stream().map(con -> con.getMessage())
                .toList();
    }

    /**
     * @apiNote {@summary = Función de validación de los elementos}
     * @exception UnsupportedOperationException
     * @param elemento
     */
    public static <T> void validarElemento(T elemento){
        for(ConstraintViolation con : validator.validate(elemento)){
            throw new UnsupportedOperationException(con.getMessage());
        }
    }
}
