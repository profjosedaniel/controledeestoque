
-- Exemplos



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

