

# Sistema de Recados
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/joaooujdq/Ticket-System/blob/main/LICENSE) 

# :pencil2: Sobre o projeto

Sistema de Recados é uma aplicação full stack web parcialmente construída durante a disciplina de Desenvolvimento Web, do curso de Ciência da Computação na Universidade Federal de Jataí. Após a conclusão da disciplina, eu tomei a iniciativa de implementar novas funcionalidades e corrigir os bugs que restavam. O planejamento e desenvolvimento da aplicação é de minha autoria.

A aplicação consiste em um sistema para salvar recados, funcionários e empresas. Representados como entidades. A entidade funcionário e empresa são independentes, elas não precisam de um recado para existir. A entidade recado exige como que o ID de um funcionario e de uma empresa sejam passados como parâmetro. **Quando o funcionario, ou empresa, é excluido, o recado que possui uma relação com ele também é excluído.**

# :camera: Apresentação

 **Aprensentação da aplicação no Youtube:**   
 https://youtu.be/kspNufinFw0
 
 
![Desktop 1](https://github.com/joaooujdq/assets-ticket-system/blob/main/5.png)

# :rocket: Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- MySQL
- Swagger

## Front end
- HTML / CSS / JS / TypeScript
- BootStrap
- ReactJS


# :computer: Instalação e execução
Pré-requisitos: Java 11
Pré-requisitos: Npm

```bash
0. Instale o Maven (https://maven.apache.org/download.cgi)

1. Clonar repositório (git clone https://github.com/joaooujdq/Ticket-System)

2. Pelo CMD, acesse a pasta "backend"  

3. Execute o comando mvn spring-boot:run

4. Use outra janela do CMD e acesse a pasta frontend, localizada na raiz do projeto.

5. Execute o comando npm install, e em seguida npm start

6. Acesse http://localhost:3000/ para exibir o projeto no seu navegador
```

# Autor

João Marcelo Teodoro de Sousa

https://www.linkedin.com/in/joaooujdq/


 
