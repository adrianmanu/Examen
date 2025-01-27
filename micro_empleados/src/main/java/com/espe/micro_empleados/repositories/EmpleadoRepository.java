package com.espe.micro_empleados.repositories;

import com.espe.micro_empleados.model.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
}
