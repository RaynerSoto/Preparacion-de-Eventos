package org.poty.proyectoevento.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.poty.proyectoevento.core.dto.evento.EventoDtoInsert;
import org.poty.proyectoevento.core.utils.Validacion;
import org.poty.proyectoevento.core.utils.ValidacionFecha;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(generator = "eventos")
    @SequenceGenerator(name = "eventos", sequenceName = "eventos_id_evento_seq",allocationSize = 1)
    @Column(name = "id_evento")
    private long id;

    @Column(name = "nombre",nullable = false,length = 255)
    @NotBlank(message = "El nombre del evento no puede ser nulo o estar vacío")
    @Size(min = 3,max = 255,message = "El nombre del evento debe estar entre 3 y 255 caractares")
    private String nombre;

    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha de inicio no puede ser null")
    private LocalDateTime fecha_inicio;

    @Column(name = "fecha_fin",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha de fin no puede ser null")
    private LocalDateTime fecha_fin;

    @Column(name = "descripcion",nullable = false)
    @NotBlank(message = "La descripción del evento no puede ser nullo o vacío")
    @Size(min = 3,max = 400,message = "La descripción debe estar entre 3 y 400 caracteres")
    private String descripcion;

    @Column(name = "cantidad_boletos",nullable = false)
    @Min(message = "El valor mínimo de los boletos debe ser 1",value = 1)
    @Max(message = "El valor máximo de los boletos ser 300",value = 300)
    private int cantidad_boletos;

    @Column(name = "boletos_vendidos",nullable = false)
    private int boletos_vendidos;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "evento",cascade = CascadeType.ALL)
    List<Ticket> tickets;

    public Evento(EventoDtoInsert eventoDtoInsert){
        this.nombre = eventoDtoInsert.nombre();
        this.fecha_inicio = eventoDtoInsert.fecha_inicio();
        this.fecha_fin = eventoDtoInsert.fecha_fin();
        this.descripcion = eventoDtoInsert.descripcion();
        this.cantidad_boletos = eventoDtoInsert.cantidad_boletos();
        this.estado = true;
    }

    public Evento(Long id, EventoDtoInsert eventoDtoInsert){
        this.id = id;
        this.nombre = eventoDtoInsert.nombre();
        this.fecha_inicio = eventoDtoInsert.fecha_inicio();
        this.fecha_fin = eventoDtoInsert.fecha_fin();
        this.descripcion = eventoDtoInsert.descripcion();
        this.cantidad_boletos = eventoDtoInsert.cantidad_boletos();
        this.estado = true;
    }

    @PrePersist
    @PreUpdate
    protected void onCreateorUpdate() {
        Validacion.validarElemento(this);
        if (!ValidacionFecha.validarFechaPosteriorHoy(this.fecha_inicio)) {
            throw new IllegalArgumentException("Las fechas de inicio debe ser posterior al día de hoy");
        }
        if (ValidacionFecha.validarFechaRango(this.fecha_inicio, this.fecha_fin)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
    }
}

