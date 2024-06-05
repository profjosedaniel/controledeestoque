package br.ufma.ppgee.eds.sistemacontroleestoque.model;


public class Produto {
	//auto increment primary key 
	private Integer id;
    private String nome;
    private String codigoDeBarras;
    private Double preco;
    private String descricao;
 //   private Fabricante fabricante;

	public Produto() {
    }
	public Produto(int id, String nome, String codigoDeBarras, Double preco, String descricao, int quantidadeEmEstoque, Fabricante fabricante) {
		this.id = id;
		this.nome = nome;
		this.codigoDeBarras = codigoDeBarras;
		this.preco = preco;
		this.descricao = descricao;
	//	this.fabricante = fabricante;
	}

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}
	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

 
	// public Fabricante getFabricante() {
	// 	return fabricante;
	// }
	// public void setFabricante(Fabricante fabricante) {
	// 	this.fabricante = fabricante;
	// }
    
}
