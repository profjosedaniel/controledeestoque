package br.ufma.ppgee.eds.sistemacontroleestoque.acl;

import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;

public class PapelUsuario {
    private static ACLInterface papelACL;
    private static Funcionario funcionario;
     
    public static ACLInterface getPapel(){
        return papelACL;
    }

    public static Funcionario getFuncionario(){
        return PapelUsuario.funcionario;
    }

    public static  void setUsuario(Funcionario funcionario){
        PapelUsuario.funcionario=funcionario;
        setPapel(PapelUsuario.funcionario.getPapel());
    }
    private static void setPapel(Funcionario.Papel papel){
        if(papel==Funcionario.Papel.GERENTE){
            papelACL = new ACLGerente();
        }else if(papel==Funcionario.Papel.VENDEDOR){
            papelACL = new ACLVendedor();
        }else if(papel==Funcionario.Papel.COMPRADOR){
            papelACL = new ACLComprador();
        }else{
            System.exit(0);
        }
    }
}
