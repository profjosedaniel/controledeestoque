package br.ufma.ppgee.eds.sistemacontroleestoque.acl;

public class ACLVendedor implements ACLInterface{
    @Override
    public boolean menuProduto() {
        return false;
    }

    @Override
    public boolean menuEstoque() {
        return false;
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
        return false;
    } 
}
