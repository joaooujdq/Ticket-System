
create table recados.recado(
id bigserial primary key,
nome VARCHAR(100) not null,
status VARCHAR(20),
prioridade VARCHAR(20),
setor VARCHAR(60),
mensagem VARCHAR(255) not null,
cargo VARCHAR(60)
email VARCHAR(120) not null,
telefone VARCHAR(11),
data date,
empresa_id bigint REFERENCES recados.empresa(id)
funcionario_id bigint REFERENCES recados.funcionario(id)
);