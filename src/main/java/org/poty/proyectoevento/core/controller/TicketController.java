package org.poty.proyectoevento.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.poty.proyectoevento.core.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
@Tag(name = "Controllador de los tickets", description = "Controlla todos los acceso a los tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{id}")
    @Operation(summary = "Vender ticket",description = "A través de este endpoint se puede vender un ticket")
    public ResponseEntity<String> venderTicket(@PathVariable(value = "id") Long id_evento) {
        try {
            ticketService.venderTicket(id_evento);
            return ResponseEntity.ok("Ticket Vendido");
        }catch (Exception e) {
            if (e.getMessage().contains("comprobar_tickets"))
                return ResponseEntity.badRequest().body("No se pueden comprar más boletos para este evento");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Canjear Ticket",description = "A tráves del id del ticket, se puede canjear este mismo")
    public ResponseEntity<String> canjearTicket(@PathVariable Long id_ticket) {
        try {
            ticketService.canjearTicket(id_ticket);
            return ResponseEntity.ok("Ticket canjado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
