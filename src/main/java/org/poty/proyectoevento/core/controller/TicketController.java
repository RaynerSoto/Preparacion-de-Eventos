package org.poty.proyectoevento.core.controller;

import org.poty.proyectoevento.core.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> venderTicket(@PathVariable(value = "id") Long id_evento) {
        try {
            ticketService.venderTicket(id_evento);
            return ResponseEntity.ok("Ticket Vendido");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> canjearTicket(@PathVariable Long id_ticket) {
        try {
            ticketService.canjearTicket(id_ticket);
            return ResponseEntity.ok("Ticket canjado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
