package br.ufma.ppgee.eds.sistemacontroleestoque.model;



 
public class Estoque {
	//auto increment primary key
	private int id;
    private String nome;
    private String localizacao;
    private int quantidade;
 
	public Estoque(){}
	public Estoque(int id, String nome, String localizacao, int quantidade ) {
			this.id = id;
			this.nome = nome;
			this.localizacao = localizacao;
			this.quantidade = quantidade;
	 
	}

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
 
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

 
}
