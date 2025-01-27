package com.espe.micro_empleados.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado_proyecto")
public class EstudianteProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empleado_id")
    private Long empleadoId;

    @Column(name = "proyecto_id")
    private Long proyectoId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EstudianteProyecto) {
            EstudianteProyecto other = (EstudianteProyecto) obj;
            return this.empleadoId != null && this.empleadoId.equals(other.empleadoId) &&
                    this.proyectoId != null && this.proyectoId.equals(other.proyectoId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * (empleadoId != null ? empleadoId.hashCode() : 0) + (proyectoId != null ? proyectoId.hashCode() : 0);
    }
}
