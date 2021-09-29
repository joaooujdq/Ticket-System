package com.example.ticket_system;

import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.RecadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BackendTsApplication implements CommandLineRunner {

	//aula 9 mod 01 assistido
	@Autowired
	private RecadoDAO recadoDAO;



	public static void main(String[] args) {
		SpringApplication.run(BackendTsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Recado r1 = new Recado(1, "nome_emp", "nome_func", true, 3,"setor", "mensagem","1234567890","data", "hora");
		Recado r2 = new Recado(2, "2nome_emp", "2nome_func", true, 3,"2setor", "2mensagem","21234567890","2data", "2hora");

		recadoDAO.saveAll(Arrays.asList(r1,r2));


	}

}
