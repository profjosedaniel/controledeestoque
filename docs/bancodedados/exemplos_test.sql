-- Fabricantes
INSERT INTO Fabricante (cnpj, nome, endereco, contato) VALUES
('12345678000195', 'Ambev S.A.', 'Av. Paulista, 1234', 'contato@ambev.com.br'),
('23456789000106', 'Nestlé Brasil Ltda.', 'Rua das Flores, 567', 'contato@nestle.com.br'),
('34567890000117', 'Unilever Brasil Ltda.', 'Av. Brigadeiro, 890', 'contato@unilever.com.br'),
('45678901200188', 'Pepsico do Brasil', 'Rua da Paz, 1011', 'contato@pepsico.com.br'),
('56789012300199', 'Procter & Gamble', 'Av. dos Estados, 2022', 'contato@pg.com.br');

-- Produtos
INSERT INTO Produto (nome, codigoDeBarras, preco, descricao, fabricante) VALUES
('Cerveja Skol', '7891000012345', 2.5, 'Cerveja Pilsen 350ml', '12345678000195'),
('Nescau 2.0', '7891000023456', 6.0, 'Achocolatado em Pó 400g', '23456789000106'),
('Shampoo Dove', '7891000034567', 10.0, 'Shampoo Nutrição 200ml', '34567890000117'),
('Pepsi', '7891000045678', 3.0, 'Refrigerante 350ml', '45678901200188'),
('Sabão em Pó Ariel', '7891000056789', 12.0, 'Sabão em Pó 1kg', '56789012300199'),
('Coca-Cola', '7891000067890', 3.5, 'Refrigerante 350ml', '45678901200188'),
('Cerveja Brahma', '7891000078901', 2.8, 'Cerveja Pilsen 350ml', '12345678000195'),
('Toddy', '7891000089012', 7.0, 'Achocolatado em Pó 400g', '23456789000106'),
('Condicionador Dove', '7891000090123', 11.0, 'Condicionador Nutrição 200ml', '34567890000117'),
('Ruffles', '7891000101234', 4.5, 'Batata Chips 200g', '45678901200188');

-- ProdutoFabricante
INSERT INTO ProdutoFabricante (fabricante, produto) VALUES
('12345678000195', 1),
('23456789000106', 2),
('34567890000117', 3),
('45678901200188', 4),
('56789012300199', 5),
('45678901200188', 6),
('12345678000195', 7),
('23456789000106', 8),
('34567890000117', 9),
('45678901200188', 10);

-- Estoque
INSERT INTO Estoque (nome, localizacao) VALUES
('Estoque Central', 'Galpão A - Centro'),
('Estoque Leste', 'Galpão B - Zona Leste'),
('Estoque Oeste', 'Galpão C - Zona Oeste');

-- Funcionários
INSERT INTO Funcionario (cpf, nome, telefone, email, papel, password) VALUES
('12345678901', 'Carlos Silva', '11987654321', 'carlos.silva@empresa.com', 'Gerente', 'senhaSegura12345'),
('23456789012', 'Maria Oliveira', '21987654321', 'maria.oliveira@empresa.com', 'Vendedor', 'senhaSegura12345'),
('34567890123', 'João Souza', '31987654321', 'joao.souza@empresa.com', 'Estoquista', 'senhaSegura12345');

-- Representantes
INSERT INTO Representante (cpf, nome, telefone, email) VALUES
('45678901234', 'Ana Pereira', '41987654321', 'ana.pereira@empresa.com'),
('56789012345', 'Paulo Lima', '51987654321', 'paulo.lima@empresa.com'),
('67890123456', 'Luciana Mendes', '61987654321', 'luciana.mendes@empresa.com');

-- RepresentanteFabricante
INSERT INTO RepresentanteFabricante (representante, fabricante) VALUES
('45678901234', '12345678000195'),
('56789012345', '23456789000106'),
('67890123456', '34567890000117');

-- Armazenamento
INSERT INTO armazenamento (estoque_id, produto_id, quantidade) VALUES
(1, 1, 100),
(2, 2, 200),
(3, 3, 300),
(1, 4, 150),
(2, 5, 250),
(3, 6, 350),
(1, 7, 120),
(2, 8, 220),
(3, 9, 320),
(1, 10, 180);

-- Movimentações
INSERT INTO Movimentacao (dataEHora, quantidade, produto, funcionario, estoque, tipoDeTransacao, descricao, valorunitario) VALUES
('2024-07-15 10:00:00', 10, 1, '12345678901', 1, 'Entrada', 'Entrada de Cerveja Skol', 2.5),
('2024-07-16 11:00:00', 20, 2, '23456789012', 2, 'Saída', 'Saída de Nescau 2.0', 6.0),
('2024-07-17 12:00:00', 30, 3, '34567890123', 3, 'Entrada', 'Entrada de Shampoo Dove', 10.0),
('2024-07-18 13:00:00', 15, 4, '12345678901', 1, 'Entrada', 'Entrada de Pepsi', 3.0),
('2024-07-19 14:00:00', 25, 5, '23456789012', 2, 'Saída', 'Saída de Sabão em Pó Ariel', 12.0),
('2024-07-20 15:00:00', 35, 6, '34567890123', 3, 'Entrada', 'Entrada de Coca-Cola', 3.5),
('2024-07-21 16:00:00', 10, 7, '12345678901', 1, 'Entrada', 'Entrada de Cerveja Brahma', 2.8),
('2024-07-22 17:00:00', 20, 8, '23456789012', 2, 'Saída', 'Saída de Toddy', 7.0),
('2024-07-23 18:00:00', 30, 9, '34567890123', 3, 'Entrada', 'Entrada de Condicionador Dove', 11.0),
('2024-07-24 19:00:00', 15, 10, '12345678901', 1, 'Entrada', 'Entrada de Ruffles', 4.5),
('2024-07-25 10:00:00', 12, 1, '12345678901', 1, 'Saída', 'Saída de Cerveja Skol', 2.5),
('2024-07-26 11:00:00', 22, 2, '23456789012', 2, 'Entrada', 'Entrada de Nescau 2.0', 6.0),
('2024-07-27 12:00:00', 32, 3, '34567890123', 3, 'Saída', 'Saída de Shampoo Dove', 10.0),
('2024-07-28 13:00:00', 18, 4, '12345678901', 1, 'Saída', 'Saída de Pepsi', 3.0),
('2024-07-29 14:00:00', 28, 5, '23456789012', 2, 'Entrada', 'Entrada de Sabão em Pó Ariel', 12.0),
('2024-07-30 15:00:00', 38, 6, '34567890123', 3, 'Saída', 'Saída de Coca-Cola', 3.5),
('2024-07-31 16:00:00', 11, 7, '12345678901', 1, 'Saída', 'Saída de Cerveja Brahma', 2.8),
('2024-08-01 17:00:00', 21, 8, '23456789012', 2, 'Entrada', 'Entrada de Toddy', 7.0),
('2024-08-02 18:00:00', 31, 9, '34567890123', 3, 'Saída', 'Saída de Condicionador Dove', 11.0
