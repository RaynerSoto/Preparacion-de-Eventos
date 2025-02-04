package org.poty.proyectoevento.core.controller;

import org.poty.proyectoevento.core.dto.evento.EventoDtoInsert;
import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {
    private final EventoService eventoService;

    @Autowired
    private EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/")
    public ResponseEntity<String> insertarEvento(@RequestBody EventoDtoInsert eventoDtoInsert){
        try {
            eventoService.insertarEvento(new Evento(eventoDtoInsert));
            return ResponseEntity.ok("Evento "+ eventoDtoInsert.nombre() + " insertado");
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarEvento(@RequestBody EventoDtoInsert eventoDtoInsert, @PathVariable Long id){
        try {
            eventoService.modificarEvento(new Evento(id,eventoDtoInsert));
            return ResponseEntity.ok("Evento "+ eventoDtoInsert.nombre() + " modificado");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>eliminarEvento(@PathVariable Long id){
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.ok("Evento eliminado");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
