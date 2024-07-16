package br.ufma.ppgee.eds.sistemacontroleestoque;

import br.ufma.ppgee.eds.sistemacontroleestoque.acl.PapelUsuario;
import br.ufma.ppgee.eds.sistemacontroleestoque.cli.CLILogin;
import br.ufma.ppgee.eds.sistemacontroleestoque.cli.CLIMenu;

public class APP {
    public static void main(String[] args) {
        do{
            new CLILogin().show();
        }while(PapelUsuario.getFuncionario()==null);
        while (true) {
            new CLIMenu().show();
        }
        
        
    }
}
