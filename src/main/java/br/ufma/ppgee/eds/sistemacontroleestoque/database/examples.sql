
 
insert into fabricante values('1000000000001','Fabricante 1','Rua 1, 123','(98) 99999-9999');
insert into fabricante values('1000000000002','Fabricante 2','Rua 2, 123','(98) 99999-9999');


insert into produto values(1001,'Produto 1','1234567890123',10.0,'Descricao do produto 1',10,'1000000000001');
insert into produto values(1002,'Produto 2','1234567890124',20.0,'Descricao do produto 2',20,'1000000000002');


insert into estoque values(1001,'Estoque 1','Rua 1, 123',10);
insert into estoque values(1002,'Estoque 2','Rua 2, 123',20);


insert into funcionario values('300000001','Funcionario 1','98999999999','Gerente','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
insert into funcionario values('300000002','Funcionario 2','98999999999','Vendedor','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');


insert into representante values('400000001','Representante 1','98999999999','asd@asda');
insert into representante values('400000002','Representante 2','98999999999','asd@asda');
insert into representante values('400000003','Representante 3','98999999999','asd@asda');


insert into representanteFabricante values('400000001','1000000000001');
insert into representanteFabricante values('400000002','1000000000001');
insert into representanteFabricante values('400000002','1000000000002');


insert into armazenamento values(1001,1001,10);
insert into armazenamento values(1002,1002,20);

insert into movimentacao values(1001,'2020-01-01 00:00:00',10,1001,'300000001',1001,'entrada');
insert into movimentacao values(1002,'2020-01-01 00:00:00',20,1002,'300000002',1002,'saida');

 