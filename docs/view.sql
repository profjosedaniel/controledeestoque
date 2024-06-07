-- viewrepresentantes_fabricantes
-- A view combina dados das tabelas representante, representantefabricante e fabricante. Ela mostra informações sobre os representantes e os fabricantes com os quais estão associados.
drop view if exists viewrepresentantes_fabricantes;

create  view viewrepresentantes_fabricantes as
SELECT cpf, representante.nome as "funcionario", email, telefone,fabricante.cnpj, fabricante.nome as "fabricante"
FROM public.representante inner join public.representantefabricante ON representantefabricante.representante = representante.cpf
inner join  public.fabricante ON fabricante.cnpj = representantefabricante.fabricante
ORDER BY cpf ASC ;

-- viewprodutofabricante
-- A view combina dados das tabelas produto, produtofabricante e fabricante. Ela mostra informações sobre os produtos e os fabricantes associados.
drop  view if exists  viewprodutofabricante;

create  view viewprodutofabricante as
SELECT produto.nome as "Produto", id, fabricante.nome as "Fabricante", cnpj
FROM public.produto inner join public.produtofabricante ON produtofabricante.produto = produto.id
inner join public.fabricante ON fabricante.cnpj = produtofabricante.fabricante
ORDER BY fabricante.cnpj ASC ;


-- viewprodutofabricante
-- A view combina dados das tabelas produto e armazenamento para fornecer um relatório resumido dos produtos e suas quantidades armazenadas.
drop  view if exists  viewrelatorioproduto;

create  view viewrelatorioproduto as
select id, nome, codigodebarras, preco, sum(quantidade) as quantidade  from
public.produto produto join public.armazenamento ON armazenamento.produto_id = produto.id
group by id, nome, codigodebarras, preco
order by nome ASC;


-- viewrelatorioestoque
-- A view combina dados das tabelas estoque e armazenamento para fornecer um relatório resumido dos estoques e as quantidades de produtos armazenados em cada um. 
drop  view if exists  viewrelatorioestoque;

create  view viewrelatorioestoque as
select id, nome, localizacao, sum(quantidade) as quantidade  from 
estoque join armazenamento ON armazenamento.estoque_id = estoque.id
group by  id, nome, localizacao
order by nome ASC ;

-- viewmovimentacao
-- A view combina dados das tabelas movimentacao, estoque, produto e funcionario para fornecer um relatório detalhado das movimentações de produtos. 
drop  view if exists  viewmovimentacao;
create  view viewmovimentacao as
select dataehora, quantidade, estoque.nome as "estoque", produto.nome as "produto", funcionario.nome as "funcionario"  from
movimentacao 
join estoque ON estoque.id = movimentacao.estoque
join produto ON produto.id = movimentacao.produto
join funcionario on funcionario.cpf = movimentacao.funcionario
order by dataehora asc