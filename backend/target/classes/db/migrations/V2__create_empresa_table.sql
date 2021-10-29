

create table recados.empresa(
id bigserial primary key,
nome VARCHAR(100) not null,
razao VARCHAR(100) not null,
cnpj VARCHAR(14) not null,
email VARCHAR(120) not null,
endereco VARCHAR(200)
telefone VARCHAR(11)
);