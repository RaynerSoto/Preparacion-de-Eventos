package org.poty.proyectoevento.core.controller;

import org.poty.proyectoevento.core.dto.evento.EventoDtoInsert;
import org.poty.proyectoevento.core.dto.evento.EventoDtoObtener;
import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.repository.EventoRepository;
import org.poty.proyectoevento.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    @Autowired
    private EventoController(EventoService eventoService,EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
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

    @GetMapping("/")
    public ResponseEntity<?> listarEventos(){
        try {
            return ResponseEntity.ok(eventoRepository.findAll().stream().map(EventoDtoObtener::convertirEventoAEventoDtoObtener).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el listado de los eventos. Póngase en contacto con el servicio técnico.");
        }
    }
}
