package br.ufma.ppgee.eds.sistemacontroleestoque.entities;


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
	public String toString() {
		return "Fabricante [cnpj=" + cnpj + ", nome=" + nome  + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Fabricante){
			Fabricante f = (Fabricante) obj;
			return f.getCnpj().equals(this.getCnpj());
		}
		return false;
	}

}
