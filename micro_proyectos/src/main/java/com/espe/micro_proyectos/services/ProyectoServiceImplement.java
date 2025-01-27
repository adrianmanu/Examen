package com.espe.micro_proyectos.services;

import com.espe.micro_proyectos.model.entity.Proyecto;
import com.espe.micro_proyectos.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImplement implements ProyectoService {

    @Autowired
    private ProyectoRepository repository;

    @Override
    public List<Proyecto> findAll() {
        return (List<Proyecto>) repository.findAll();
    }

    @Override
    public Optional<Proyecto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Proyecto save(Proyecto proyecto) {
        return repository.save(proyecto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
