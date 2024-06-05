package br.ufma.ppgee.eds.sistemacontroleestoque.acl;

public class ACLGerente implements ACLInterface{

    @Override
    public boolean menuProduto() {
        return true;
    }

    @Override
    public boolean menuEstoque() {
        return true;
    }

    @Override
    public boolean menuMovimentacao() {
        return true;
    }

    @Override
    public boolean menuFuncionario() {
        return true;
    }

    @Override
    public boolean menuFabricante() {
        return true;
    }
    @Override
    public boolean menuRepresentante() {
        return true;
    }   
}
