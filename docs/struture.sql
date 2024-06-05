drop table if exists representanteFabricante;
drop table if exists ProdutoFabricante;
drop table if exists movimentacao;
drop table if exists armazenamento;
drop table if exists representante;
drop table if exists funcionario;
drop table if exists estoque;
drop table if exists produto;
drop table if exists fabricante;

drop EXTENSION pgcrypto;

-- drop table if exists user;
CREATE EXTENSION pgcrypto;
CREATE TABLE Fabricante (
	cnpj CHAR(14) primary key,
	nome VARCHAR(200) not null,
	endereco VARCHAR(255),
	contato VARCHAR(255)
);



CREATE TABLE Produto (
	id integer   primary key generated always as identity,
	nome VARCHAR(255) not null,
	codigoDeBarras VARCHAR(255) unique not null,
	preco float check(preco>0) not null,
	descricao TEXT,
	fabricante CHAR(14),
	FOREIGN KEY (fabricante) REFERENCES Fabricante(cnpj)

);




CREATE TABLE ProdutoFabricante (
	fabricante CHAR(14) not null,
	produto integer not null,
	FOREIGN KEY (fabricante) REFERENCES Fabricante(cnpj),
	FOREIGN KEY (produto) REFERENCES Produto(id),
	primary key(fabricante,produto)
);



CREATE TABLE Estoque (
    id integer  primary key generated always as identity,
    nome VARCHAR(100) not null unique,
    localizacao VARCHAR(255)
);


CREATE TABLE Funcionario (
	cpf VARCHAR(11) primary key,
	nome VARCHAR(255) not null,
	telefone VARCHAR(11),
	email VARCHAR(255) unique,
	papel VARCHAR(150),
	password VARCHAR(120),
CONSTRAINT ck_password CHECK ( LENGTH("password") > 14)

);


CREATE TABLE Representante (
	cpf CHAR(11) primary key,
	nome VARCHAR(255) not null,
	telefone CHAR(11),
	email VARCHAR(100)
);



CREATE TABLE RepresentanteFabricante (
	representante CHAR(11),
	fabricante CHAR(14),
	FOREIGN KEY (representante) REFERENCES Representante(cpf),
	FOREIGN KEY (fabricante) REFERENCES Fabricante(cnpj)
);


create table armazenamento(
    estoque_id integer references estoque(id),
    produto_id integer references produto(id),
    quantidade integer check(quantidade>=0),
    primary key(estoque_id,produto_id)
 );




CREATE TABLE Movimentacao (
	id integer  primary key generated always as identity,
	dataEHora timestamp,
	quantidade INT,
	produto integer,
	funcionario varCHAR(11),
	estoque integer,
	tipoDeTransacao varCHAR(11),
	descricao varCHAR(250),
	valorunitario float,
	FOREIGN KEY (produto) REFERENCES Produto(id),
	FOREIGN KEY (funcionario) REFERENCES Funcionario(cpf),
	FOREIGN KEY (estoque) REFERENCES Estoque(id)
);

drop  INDEX if exists idx_Fabricante_cnpj;
drop  INDEX if exists idx_Fabricante_nome;
CREATE INDEX idx_Fabricante_cnpj ON Fabricante (cnpj);
CREATE INDEX idx_Fabricante_nome ON Fabricante (nome);


drop INDEX if exists idx_Produto_id;
drop INDEX if exists idx_Produto_codigoDeBarras;
drop INDEX if exists idx_Produto_nome;
CREATE INDEX idx_Produto_id ON Produto (id );
CREATE INDEX idx_Produto_codigoDeBarras ON Produto (codigoDeBarras);
CREATE INDEX idx_Produto_nome ON Produto (nome);


drop INDEX if exists idx_ProdutoFabricante;
CREATE INDEX idx_ProdutoFabricante ON ProdutoFabricante  (fabricante,produto);


drop INDEX if exists idx_Estoque_id;
drop INDEX if exists idx_Estoque_nome;
CREATE INDEX idx_Estoque_id ON Estoque (id);
CREATE INDEX idx_Estoque_nome ON Estoque (nome);


drop INDEX if exists idx_Funcionario_cpf;
drop INDEX if exists idx_Funcionario_nome;
CREATE INDEX idx_Funcionario_cpf ON Funcionario (cpf);
CREATE INDEX idx_Funcionario_nome ON Funcionario (nome);


drop INDEX if exists idx_Representante_cpf;
drop INDEX if exists idx_Representante_nome;
CREATE INDEX idx_Representante_cpf ON Representante (cpf);
CREATE INDEX idx_Representante_nome ON Representante (nome);


drop INDEX if exists idx_RepresentanteFabricante;
CREATE INDEX idx_RepresentanteFabricante ON RepresentanteFabricante (representante,fabricante);

drop INDEX if exists idx_armazenamento;
CREATE INDEX idx_armazenamento ON armazenamento (estoque_id,produto_id);

drop INDEX if exists idx_Movimentacao_id;
CREATE INDEX idx_Movimentacao_id ON Movimentacao (id);






insert into fabricante values('1000000000001','Fabricante 1','Rua 1, 123','(98) 99999-9999');
insert into fabricante values('1000000000002','Fabricante 2','Rua 2, 123','(98) 99999-9999');


insert into produto (nome,codigoDeBarras,preco,descricao ,fabricante ) values('Produto 1','1234567890123',10.0,'Descricao do produto 1','1000000000001');
insert into produto (nome,codigoDeBarras,preco,descricao ,fabricante ) values('Produto 2','1234567890124',20.0,'Descricao do produto 2','1000000000002');


insert into estoque (nome,localizacao ) values('Estoque 1','Rua 1, 123');
insert into estoque (nome,localizacao ) values('Estoque 2','Rua 2, 123');


insert into funcionario (cpf,nome ,telefone ,email ,papel ,password  ) values('300000001','Funcionario 1','98999999999','123@asda.com','Gerente','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');

insert into funcionario (cpf,nome ,telefone ,email ,papel ,password  ) values('300000002','Funcionario 2','98999999999','1234@asda.com','Gerente','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
insert into funcionario (cpf,nome ,telefone ,email ,papel ,password  ) values('123123123','Funcionario 2','98999999999','1235@asda.com','Gerente','$1$d3z2/Xh2$JliDxFNA/y5dav2zuT1A60');
insert into funcionario (cpf,nome ,telefone ,email ,papel ,password  ) values('12345678901','Funcionario 2','98999999999','1236@asda.com','Gerente','$1$d3z2/Xh2$JliDxFNA/y5dav2zuT1A60');

insert into representante values('400000001','Representante 1','98999999999','asd@asda');
insert into representante values('400000002','Representante 2','98999999999','asd@asda');
insert into representante values('400000003','Representante 3','98999999999','asd@asda');


insert into representanteFabricante values('400000001','1000000000001');
insert into representanteFabricante values('400000002','1000000000001');
insert into representanteFabricante values('400000002','1000000000002');

 
insert into armazenamento (estoque_id,produto_id, quantidade ) values(1,1,10);
insert into armazenamento (estoque_id,produto_id, quantidade ) values(1,2,20);

insert into movimentacao (dataEHora,quantidade ,produto ,funcionario ,estoque ,tipoDeTransacao ,descricao,valorunitario) 
values('2020-01-01 00:00:00',2,1,'300000001',1,'ENTRADA','asdasd',12);
insert into movimentacao (dataEHora,quantidade ,produto ,funcionario ,estoque ,tipoDeTransacao ,descricao,valorunitario) 
values('2020-01-01 00:00:00',10,2,'300000002',1,'ENTRADA','asdasd',20);

