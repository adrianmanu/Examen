package com.espe.micro_empleados.services;

import com.espe.micro_empleados.model.Proyectos;
import com.espe.micro_empleados.model.entity.Empleado;
import com.espe.micro_empleados.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    @Autowired
    private ProyectoRepository proyectoRepository;  // Asegúrate de que el repositorio de Proyecto está autoinyectado

    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) repository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Implementación del método asignarProyectoAEmpleado
    @Override
    public boolean asignarProyectoAEmpleado(Empleado empleado, Long proyectoId) {
        // Verificar si el proyecto existe
        Optional<Proyectos> proyectoOpt = proyectoRepository.findById(proyectoId);
        if (!proyectoOpt.isPresent()) {
            return false;  // El proyecto no existe
        }

        Proyectos proyecto = proyectoOpt.get();

        // Asignar el proyecto al empleado (suponiendo una relación ManyToMany)
        if (empleado.getProyectos() == null) {
            empleado.setProyectos(new HashSet<>());  // Si no tiene proyectos asignados, inicializar el conjunto
        }

        empleado.getProyectos().add(proyecto);  // Añadir el proyecto al conjunto de proyectos del empleado

        // Guardar el empleado con el nuevo proyecto asignado
        repository.save(empleado);
        return true;  // Proyecto asignado correctamente
    }
}
