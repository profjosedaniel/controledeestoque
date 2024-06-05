package br.ufma.ppgee.eds.sistemacontroleestoque.acl;

public class ACLComprador implements ACLInterface {
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
        return false;
    }

    @Override
    public boolean menuFabricante() {
        return false;
    }

    @Override
    public boolean menuRepresentante() {
        return true;
    }   
}
