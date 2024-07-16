package br.ufma.ppgee.eds.sistemacontroleestoque.entities;


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

    @Override
    public String toString() {
        return "ProdutoFabricante [produto=" + produto + ", fabricante=" + fabricante + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProdutoFabricante){
            ProdutoFabricante pf = (ProdutoFabricante) obj;
            return pf.getProduto().getId() == this.getProduto().getId() && pf.getFabricante().getCnpj().equals(this.getFabricante().getCnpj());
        }
        return false;
    }
}
