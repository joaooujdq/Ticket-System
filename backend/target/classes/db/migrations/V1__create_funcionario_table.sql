create SCHEMA if not EXISTS recados;

create table recados.funcionario(
id bigserial primary key,
nome VARCHAR(100) not null,
cargo VARCHAR(60)
email VARCHAR(120) not null,
telefone VARCHAR(11)
);