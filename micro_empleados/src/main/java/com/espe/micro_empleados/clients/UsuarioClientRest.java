package com.espe.micro_empleados.clients;

import com.espe.micro_empleados.model.Proyectos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-proyectos", url = "http://localhost:8082/api/proyectos")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Proyectos findById(@PathVariable("id") long id);
}

