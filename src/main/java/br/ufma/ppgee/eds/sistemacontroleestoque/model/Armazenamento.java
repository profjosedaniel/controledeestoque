package br.ufma.ppgee.eds.sistemacontroleestoque.model;

public class Armazenamento {
    public   Estoque estoque;
    public  Produto produto;
    public  int quantidade;

    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
