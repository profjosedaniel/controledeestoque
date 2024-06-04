package br.ufma.ppgee.eds.sistemacontroleestoque.model;


public class Fabricante  {
	//primary key 
	private String cnpj;
    private String nome;
    private String endereco;
    private String contato;
	
	public Fabricante(){}
	public Fabricante(String cnpj, String nome, String endereco, String contato) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.contato = contato;
	}
    // gette
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	@Override
	public boolean equals(Object obj) {
		return cnpj.equals(((Fabricante)obj).getCnpj());
	}
	


}
