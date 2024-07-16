package br.ufma.ppgee.eds.sistemacontroleestoque.entities;

import java.sql.Date;


public class Movimentacao {
	public enum TipoDeTransacao{
		ENTRADA, SAIDA
	}
	private int id;
    private Date data;
	private int quantidade;
	private double valorUnitario;
	private String descricao;
	private Produto produto;
    private Funcionario funcionario;
    private Estoque estoque;
    private TipoDeTransacao tipoDeTransacao;

    // getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public TipoDeTransacao getTipoDeTransacao() {
		return tipoDeTransacao;
	}
	public void setTipoDeTransacao(TipoDeTransacao tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", data=" + data +"]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Movimentacao){
			Movimentacao m = (Movimentacao) obj;
			return m.getId() == this.getId();
		}
		return false;
	}

}
