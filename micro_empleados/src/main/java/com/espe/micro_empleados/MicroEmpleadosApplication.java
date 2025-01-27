package com.espe.micro_empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Asegúrate de que esta anotación esté presente

public class MicroEmpleadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroEmpleadosApplication.class, args);
	}

}
