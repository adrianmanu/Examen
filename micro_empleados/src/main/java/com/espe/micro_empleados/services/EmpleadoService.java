package com.espe.micro_empleados.services;

import com.espe.micro_empleados.model.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll(); // Obtener todos los empleados
    Optional<Empleado> findById(Long id); // Obtener un empleado por su ID
    Empleado save(Empleado empleado); // Guardar un empleado
    void deleteById(Long id); // Eliminar un empleado por su ID
    boolean asignarProyectoAEmpleado(Empleado empleado, Long proyectoId); // Asignar un proyecto a un empleado
}
