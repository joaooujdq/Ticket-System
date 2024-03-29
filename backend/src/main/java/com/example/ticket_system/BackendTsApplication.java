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
		Empresa e1 = new Empresa(1, "Amazon", "Amazon", "12345678901234", "amazon@hotmail.com", "Rua 22, Setor Antena, Numero 127", "61 99867634");
		Empresa e2 = new Empresa(2, "Facebook", "Facebook", "12345678901235", "facebook@hotmail.com", "Rua 20, Setor Sul, Numero 721", "61 99667678");
		Empresa e3 = new Empresa(3, "Amazon", "Amazon", "12345678901237", "amazon2@hotmail.com", "Rua 22, Setor Antena, Numero 127", "61 99837634");
		Empresa e4 = new Empresa(4, "Facebook", "Facebook", "12345678901238", "facebook2@hotmail.com", "Rua 20, Setor Sul, Numero 721", "61 99267678");
		Empresa e5 = new Empresa(5, "Facebook", "Facebook", "12345612301238", "facebook3@hotmail.com", "Rua 20, Setor Sul, Numero 721", "61 99267123");
		Funcionario f1 = new Funcionario(1, "ADMIN", ".", "gabriel6@hotmail.com", "61 99667123", "senha");
		Funcionario f2 = new Funcionario(2, "Gabriel", "Marketing", "gabriel@hotmail.com", "61 99667630", "123");
		Funcionario f3 = new Funcionario(3, "João Marcelo", "Marketing", "joaomarcelo2@hormail.com", "61 99667320", "123");
		Funcionario f4 = new Funcionario(4, "Gabriel", "Desenvoldedor", "gabriel2@hotmail.com", "61 99668630", "123");
		Funcionario f5 = new Funcionario(5, "Gabriel", "Desenvoldedor", "gabriel3@hotmail.com", "61 99612330", "123");
		Recado r1 = new Recado(1,  "option1", "option5","Teste", "Planejar a próxima aplicação da empresa.", e1, f1);
		Recado r2 = new Recado(2,  "option2", "option3","Teste", "Testar a próxima aplicação da empresa.", e2, f2);
		Recado r3 = new Recado(3,  "option2", "option4","Programação", "Planejar a próxima aplicação da empresa.", e3, f3);
		Recado r4 = new Recado(4,  "option1", "option4","Programação", "Testar a próxima aplicação da empresa.", e4, f4);
		Recado r5 = new Recado(5,  "option1", "option4","Programação", "Testar a próxima aplicação da empresa.", e5, f5);
		empresaDAO.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
		funcionarioDAO.saveAll(Arrays.asList(f1,f2,f3,f4,f5));
		recadoDAO.saveAll(Arrays.asList(r1,r2,r3,r4,r5));
	}
}
