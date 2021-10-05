package com.example.ticket_system;

import com.example.ticket_system.models.Empresa;
import com.example.ticket_system.models.Funcionario;
import com.example.ticket_system.models.Recado;
import com.example.ticket_system.repositories.EmpresaDAO;
import com.example.ticket_system.repositories.FuncionarioDAO;
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

	@Autowired
	private RecadoDAO recadoDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private FuncionarioDAO funcionarioDAO;



	public static void main(String[] args) {
		SpringApplication.run(BackendTsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		Empresa e1 = new Empresa(1, "nome_emp", "razao_emp", "12345678901234", "email_emp", "endereco_emp", "1234567890");
		Empresa e2 = new Empresa(2, "2nome_emp", "2razao_emp", "12345678901234", "2email_emp", "2endereco_emp", "21234567890");

		Funcionario f1 = new Funcionario(1, "nome_func", "cargo_func", "email_func", "1234567990");
		Funcionario f2 = new Funcionario(2, "2nome_func", "2cargo_func", "2email_func", "21234567890");

		Recado r1 = new Recado(1,  "status", "prioridade","setor", "mensagem","data", "hora", e1, f1);
		Recado r2 = new Recado(2,  "status", "prioridade","2setor", "2mensagem","2data", "2hora", e2, f2);


		empresaDAO.saveAll(Arrays.asList(e1,e2));
		funcionarioDAO.saveAll(Arrays.asList(f1,f2));
		recadoDAO.saveAll(Arrays.asList(r1,r2));


	}

}
