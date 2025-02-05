package org.poty.proyectoevento.core.service.implement;

import org.junit.jupiter.api.Test;
import org.poty.proyectoevento.core.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceImplementTest {

    private TicketService ticketService;

    @Autowired
    public TicketServiceImplementTest(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Test
    void venderTicket() throws Exception {
        Long id = 1L;
        ticketService.venderTicket(id);
    }
}