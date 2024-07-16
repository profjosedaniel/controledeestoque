package br.ufma.ppgee.eds.sistemacontroleestoque.entities;


public class Funcionario{
	public enum Papel{
		GERENTE, VENDEDOR,COMPRADOR
	}
	//primary key 
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String password;

	private Papel papel;

	public Funcionario() {
    }
	public Funcionario(String cpf, String nome, String telefone, String email, Papel papel,String password) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.papel = papel;
		this.password =	password;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static Papel strToPapel(String papel) {
		if(papel.equals("GERENTE")) {
			return Papel.GERENTE;
		}else if(papel.equals("VENDEDOR")) {
			return Papel.VENDEDOR;
		}else if(papel.equals("COMPRADOR")) {
			return Papel.COMPRADOR;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Funcionario [cpf=" + cpf + ", nome=" + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Funcionario){
			Funcionario f = (Funcionario) obj;
			return f.getCpf().equals(this.getCpf());
		}
		return false;
	}
}
