package br.ufma.ppgee.eds.sistemacontroleestoque.model;

import java.util.ArrayList;
import java.util.List;

public class Representante{

    	//primary key 
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
 

    private List<Fabricante> fabricante;

    public Representante(){

    }
    public Representante(String cpf, String nome, String telefone, String email, List<Fabricante> fabricante) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.fabricante = fabricante;
    }
   

    public List<Fabricante> getFabricante() {
		return fabricante;
	}

	public void setFabricante(List<Fabricante> fabricante) {
		this.fabricante = fabricante;
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

    public void addFabricante(Fabricante fabricante) {
        if(this.fabricante == null){
            this.fabricante = new ArrayList<>();
        }
        this.fabricante.add(fabricante);
	}
    public void removeFabricante(Fabricante fabricante) {
        if(this.fabricante != null){
            this.fabricante.remove(fabricante);
        }
	}
}
