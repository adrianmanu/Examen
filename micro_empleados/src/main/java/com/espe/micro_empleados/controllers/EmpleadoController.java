package com.espe.micro_empleados.controllers;

import com.espe.micro_empleados.model.entity.Empleado;
import com.espe.micro_empleados.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    // Método de validación reutilizable para empleados
    private ResponseEntity<?> validarEmpleado(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(err.getField(), err.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }
        return null; // No hay errores, continuar con la operación
    }

    // Crear un empleado
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Empleado empleado, BindingResult result) {
        // Validación reutilizable
        ResponseEntity<?> validationResponse = validarEmpleado(result);
        if (validationResponse != null) {
            return validationResponse; // Si hay errores, devolver respuesta con errores
        }

        // La fecha de creación se asignará automáticamente en el método @PrePersist
        Empleado nuevoEmpleado = service.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    // Obtener todos los empleados
    @GetMapping
    public List<Empleado> listar() {
        return service.findAll();
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = service.findById(id);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
    }

    // Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody @Valid Empleado empleado, @PathVariable Long id, BindingResult result) {
        // Validación reutilizable
        ResponseEntity<?> validationResponse = validarEmpleado(result);
        if (validationResponse != null) {
            return validationResponse; // Si hay errores, devolver respuesta con errores
        }

        Optional<Empleado> empleadoExistente = service.findById(id);
        if (empleadoExistente.isPresent()) {
            Empleado empleadoActualizado = empleadoExistente.get();
            empleadoActualizado.setNombre(empleado.getNombre());
            empleadoActualizado.setApellido(empleado.getApellido());
            empleadoActualizado.setEmail(empleado.getEmail());
            empleadoActualizado.setTelefono(empleado.getTelefono());
            // La fecha de creación no se modifica porque está configurada como "updatable = false"
            return ResponseEntity.ok(service.save(empleadoActualizado));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Empleado> empleado = service.findById(id);
        if (empleado.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok("Empleado eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
    }

    // Asignar un proyecto a un empleado (sin usar ProyectoRepository directamente)
    @PostMapping("/{empleadoId}/proyectos/{proyectoId}")
    public ResponseEntity<?> asignarProyecto(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        // Verificar si el empleado existe
        Optional<Empleado> empleadoOpt = service.findById(empleadoId);
        if (!empleadoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
        }

        Empleado empleado = empleadoOpt.get();

        // Delegar la lógica de asignación de proyecto al servicio EmpleadoService
        boolean proyectoAsignado = service.asignarProyectoAEmpleado(empleado, proyectoId);

        if (!proyectoAsignado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado o no pudo ser asignado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Empleado asignado al proyecto correctamente");
    }
}
