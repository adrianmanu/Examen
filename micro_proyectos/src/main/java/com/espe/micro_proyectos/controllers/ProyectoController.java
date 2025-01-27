package com.espe.micro_proyectos.controllers;


import com.espe.micro_proyectos.model.entity.Proyecto;
import com.espe.micro_proyectos.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        try {
            Proyecto nuevoProyecto = service.save(proyecto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProyecto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ocurrió un error al crear el proyecto");
        }
    }

    // Obtener todos los proyectos
    @GetMapping
    public List<Proyecto> listar() {
        return service.findAll();
    }

    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Proyecto> proyecto = service.findById(id);
        if (proyecto.isPresent()) {
            return ResponseEntity.ok(proyecto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
    }

    // Actualizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Proyecto proyecto, @PathVariable Long id) {
        Optional<Proyecto> proyectoExistente = service.findById(id);
        if (proyectoExistente.isPresent()) {
            Proyecto proyectoActualizado = proyectoExistente.get();
            proyectoActualizado.setNombre(proyecto.getNombre());
            proyectoActualizado.setDescripcion(proyecto.getDescripcion());
            proyectoActualizado.setFechaInicio(proyecto.getFechaInicio());
            proyectoActualizado.setFechaFin(proyecto.getFechaFin());
            return ResponseEntity.ok(service.save(proyectoActualizado));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
    }

    // Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Proyecto> proyecto = service.findById(id);
        if (proyecto.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok("Proyecto eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
    }
}
