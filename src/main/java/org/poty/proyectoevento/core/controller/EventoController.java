package org.poty.proyectoevento.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.poty.proyectoevento.core.dto.evento.EventoDtoInsert;
import org.poty.proyectoevento.core.dto.evento.EventoDtoObtener;
import org.poty.proyectoevento.core.model.Evento;
import org.poty.proyectoevento.core.repository.EventoRepository;
import org.poty.proyectoevento.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poty.org/evento")
@Tag(name = "Controllador de eventos", description = "Controlla todos los aspectos de los eventos")
public class EventoController {
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    @Autowired
    private EventoController(EventoService eventoService,EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping("/")
    @Operation(summary = "Insertar Evento", description = "Este endpoint permite insertar un evento")
    public ResponseEntity<String> insertarEvento(@RequestBody EventoDtoInsert eventoDtoInsert){
        try {
            eventoService.insertarEvento(new Evento(eventoDtoInsert));
            return ResponseEntity.ok("Evento "+ eventoDtoInsert.nombre() + " insertado");
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar Evento",description = "Este endpoint permite modificar un evento")
    public ResponseEntity<String> modificarEvento(@RequestBody EventoDtoInsert eventoDtoInsert, @PathVariable Long id){
        try {
            eventoService.modificarEvento(new Evento(id,eventoDtoInsert));
            return ResponseEntity.ok("Evento "+ eventoDtoInsert.nombre() + " modificado");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Evento",description = "Este endpoint permite eliminar un evento a raíz de su ID")
    public ResponseEntity<String>eliminarEvento(@PathVariable Long id){
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.ok("Evento eliminado");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Listar Eventos",description = "Este endpoint permite listar un evento de manera simple")
    public ResponseEntity<?> listarEventos(){
        try {
            return ResponseEntity.ok(eventoRepository.findAll().stream().map(EventoDtoObtener::convertirEventoAEventoDtoObtener).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el listado de los eventos. Póngase en contacto con el servicio técnico.");
        }
    }
}
