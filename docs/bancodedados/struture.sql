drop table if exists representanteFabricante CASCADE ;
drop table if exists ProdutoFabricante CASCADE ;
drop table if exists movimentacao CASCADE;
drop table if exists armazenamento CASCADE;
drop table if exists representante CASCADE;
drop table if exists funcionario CASCADE;
drop table if exists estoque CASCADE;
drop table if exists produto CASCADE;
drop table if exists fabricante CASCADE;
drop table if exists LOG_Movimentacao CASCADE;
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
	nome VARCHAR(255) check(LENGTH(nome)>2) not null,
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
	nome VARCHAR(255) check( LENGTH(nome)>0) not null,
	telefone CHAR(11),
	email VARCHAR(100) check( LENGTH(nome)>0) not null
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
	quantidade INT check(quantidade>0),
	produto integer,
	funcionario varCHAR(11),
	estoque integer,
	tipoDeTransacao varCHAR(11),
	descricao varCHAR(250),
	valorunitario float check(valorunitario>0),
	FOREIGN KEY (produto) REFERENCES Produto(id),
	FOREIGN KEY (funcionario) REFERENCES Funcionario(cpf),
	FOREIGN KEY (estoque) REFERENCES Estoque(id)
);

CREATE TABLE LOG_Movimentacao (
    id integer primary key generated always as identity,
    Movimentacao_id integer,
    dataEHoraOld timestamp,
    dataEHoraNew timestamp,
    quantidadeOld INT,
    quantidadeNew INT,
    produtoOld integer,
    produtoNew integer,
    funcionarioOld varCHAR(11),
    funcionarioNew varCHAR(11),
    estoqueOld integer,
    estoqueNew integer,
    tipoDeTransacaoOld varCHAR(11),
    tipoDeTransacaoNew varCHAR(11),
    descricao varCHAR(250),
    valorunitarioOld float,
    valorunitarioNew float,
    FOREIGN KEY (Movimentacao_id) REFERENCES Movimentacao(id),
    FOREIGN KEY (produtoOld) REFERENCES Produto(id),
    FOREIGN KEY (produtoNew) REFERENCES Produto(id),
    FOREIGN KEY (funcionarioOld) REFERENCES Funcionario(cpf),
    FOREIGN KEY (funcionarioNew) REFERENCES Funcionario(cpf),
    FOREIGN KEY (estoqueOld) REFERENCES Estoque(id),
    FOREIGN KEY (estoqueNew) REFERENCES Estoque(id)
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

 
-- Função que será chamada pela trigger
CREATE OR REPLACE FUNCTION log_movimentacao_update() RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO LOG_Movimentacao (
        Movimentacao_id,
        dataEHoraOld,
        dataEHoraNew,
        quantidadeOld,
        quantidadeNew,
        produtoOld,
        produtoNew,
        funcionarioOld,
        funcionarioNew,
        estoqueOld,
        estoqueNew,
        tipoDeTransacaoOld,
        tipoDeTransacaoNew,
        descricao,
        valorunitarioOld,
        valorunitarioNew
    ) VALUES (
        OLD.id,
        OLD.dataEHora,
        NEW.dataEHora,
        OLD.quantidade,
        NEW.quantidade,
        OLD.produto,
        NEW.produto,
        OLD.funcionario,
        NEW.funcionario,
        OLD.estoque,
        NEW.estoque,
        OLD.tipoDeTransacao,
        NEW.tipoDeTransacao,
        NEW.descricao,
        OLD.valorunitario,
        NEW.valorunitario
    );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para a tabela Movimentacao
CREATE TRIGGER trg_log_movimentacao_update
AFTER UPDATE ON Movimentacao
FOR EACH ROW
EXECUTE FUNCTION log_movimentacao_update();