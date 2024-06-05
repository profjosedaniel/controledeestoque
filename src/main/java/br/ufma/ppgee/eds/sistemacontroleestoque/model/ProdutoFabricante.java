package br.ufma.ppgee.eds.sistemacontroleestoque.model;


public class ProdutoFabricante {

    Produto produto;
    Fabricante fabricante;
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Fabricante getFabricante() {
        return fabricante;
    }
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}
