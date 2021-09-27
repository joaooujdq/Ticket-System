package com.example.ticket_system;

import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BackendTsApplication implements CommandLineRunner {

	@Autowired
	private RecadoDAO recadoDAO;
	//aula 6 mod 03 assistido


	public static void main(String[] args) {
		SpringApplication.run(BackendTsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Recado r1 = new Recado(1, "nome_emp", "nome_func", true, 3,"setor", "data", "hora");
		Recado r2 = new Recado(2, "2nome_emp", "2nome_func", true, 3,"2setor", "2data", "2hora");

		recadoDAO.saveAll(Arrays.asList(r1,r2));


	}

}
