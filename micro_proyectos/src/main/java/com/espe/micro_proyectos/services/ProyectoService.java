package com.espe.micro_proyectos.services;

import com.espe.micro_proyectos.model.entity.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoService {
    List<Proyecto> findAll();
    Optional<Proyecto> findById(Long id);
    Proyecto save(Proyecto proyecto);
    void deleteById(Long id);
}