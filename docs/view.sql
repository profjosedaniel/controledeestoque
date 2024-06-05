-- viewrepresentantes_fabricantes
drop view if exists viewrepresentantes_fabricantes;

create  view viewrepresentantes_fabricantes as
SELECT cpf, representante.nome as "funcionario", email, telefone,fabricante.cnpj, fabricante.nome as "fabricante"
FROM public.representante inner join public.representantefabricante ON representantefabricante.representante = representante.cpf
inner join  public.fabricante ON fabricante.cnpj = representantefabricante.fabricante
ORDER BY cpf ASC ;

-- viewprodutofabricante
drop  view if exists  viewprodutofabricante;

create  view viewprodutofabricante as
SELECT produto.nome as "Produto", id, fabricante.nome as "Fabricante", cnpj
FROM public.produto inner join public.produtofabricante ON produtofabricante.produto = produto.id
inner join public.fabricante ON fabricante.cnpj = produtofabricante.fabricante
ORDER BY fabricante.cnpj ASC ;


-- viewprodutofabricante
drop  view if exists  viewrelatorioproduto;

create  view viewrelatorioproduto as
select id, nome, codigodebarras, preco, sum(quantidade) as quantidade  from
public.produto produto join public.armazenamento ON armazenamento.produto_id = produto.id
group by id, nome, codigodebarras, preco
order by nome ASC;


-- viewrelatorioestoque

drop  view if exists  viewrelatorioestoque;

create  view viewrelatorioestoque as
select id, nome, localizacao, sum(quantidade) as quantidade  from 
estoque join armazenamento ON armazenamento.estoque_id = estoque.id
group by  id, nome, localizacao
order by nome ASC ;


drop  view if exists  viewmovimentacao;
create  view viewmovimentacao as
select dataehora, quantidade, estoque.nome as "estoque", produto.nome as "produto", funcionario.nome as "funcionario"  from
movimentacao 
join estoque ON estoque.id = movimentacao.estoque
join produto ON produto.id = movimentacao.produto
join funcionario on funcionario.cpf = movimentacao.funcionario
order by dataehora asc