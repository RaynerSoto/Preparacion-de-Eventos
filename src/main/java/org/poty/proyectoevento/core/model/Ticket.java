package org.poty.proyectoevento.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(generator = "ticketsId")
    @SequenceGenerator(name = "ticketsId",sequenceName = "tickets_id_tickets_seq", allocationSize = 1)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_canjeo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_canjeo;

    @Column(name = "isCanjeo",nullable = false)
    private boolean isCanjeo;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    public Ticket(Evento evento) {
        this.evento = evento;
        this.isCanjeo = false;
        this.fecha_canjeo = null;
        this.codigo = evento.getNombre()+evento.getBoletos_vendidos()+1+LocalDateTime.now().getDayOfYear();
    }
}
