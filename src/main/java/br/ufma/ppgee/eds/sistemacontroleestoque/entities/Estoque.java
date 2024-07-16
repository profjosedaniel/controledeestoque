package br.ufma.ppgee.eds.sistemacontroleestoque.entities;



 
public class Estoque {
	//auto increment primary key
	private int id;
    private String nome;
    private String localizacao;

 
	public Estoque(){}
	public Estoque(int id, String nome, String localizacao ) {
			this.id = id;
			this.nome = nome;
			this.localizacao = localizacao;

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
 
	@Override
	public String toString() {
		return "Estoque [id=" + id + ", nome=" + nome +"]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Estoque){
			Estoque e = (Estoque) obj;
			return e.getId() == this.getId();
		}
		return false;
	}
 
}
