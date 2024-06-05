package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import br.ufma.util.LerTerminal;
 
public class CLIMenu {
    public static final int OPCAO_PRODUTOS = 1;
    public static final int OPCAO_FUNCIONARIOS = 2;
    public static final int OPCAO_FABRICANTE = 3;
    public static final int OPCAO_ESTOQUE = 4;
    public static final int OPCAO_REPRESENTANTE = 5;
    public static final int OPCAO_MOVIMENTACAO = 6;

    public void show() {
        while(true){
            LerTerminal terminal = new LerTerminal();
            System.out.println("==========Menu================");
            if(CLILogin.papelACL.menuProduto())
                System.out.println(OPCAO_PRODUTOS+" - Produtos");
            if(CLILogin.papelACL.menuFuncionario())
                System.out.println(OPCAO_FUNCIONARIOS+" - Funcionarios");
            if(CLILogin.papelACL.menuFabricante())
                System.out.println(OPCAO_FABRICANTE+" - Fabricante");
            if(CLILogin.papelACL.menuEstoque())
                System.out.println(OPCAO_ESTOQUE+" - Estoque");
            if(CLILogin.papelACL.menuRepresentante())
                System.out.println(OPCAO_REPRESENTANTE+" - Representante");
            if(CLILogin.papelACL.menuMovimentacao())
                System.out.println(OPCAO_MOVIMENTACAO+" - Movimentacao");
            System.out.println("0 - Sair");
            System.out.println("=======================================");
    
            Integer opcao = terminal.nextInt("Selecione uma opção válida",true);
            if (opcao==OPCAO_ESTOQUE && CLILogin.papelACL.menuEstoque()) {
                new CLIProduto(terminal).show();
            } else if (opcao==OPCAO_FUNCIONARIOS && CLILogin.papelACL.menuFuncionario()) {
                new CLIFuncionario(terminal).show();
            } else if (opcao==OPCAO_FABRICANTE && CLILogin.papelACL.menuFabricante() ){
                new CLIFabricante(terminal).show();
            } else if (opcao==OPCAO_PRODUTOS && CLILogin.papelACL.menuProduto()) {
                new CLIProduto(terminal).show();
            } else if (opcao==OPCAO_REPRESENTANTE && CLILogin.papelACL.menuRepresentante()) {
                new CLIRepresentante(terminal).show();
            } else if (opcao==OPCAO_MOVIMENTACAO && CLILogin.papelACL.menuMovimentacao()) {
                new CLIMovimentacao(terminal).show();
            } else if (opcao==0){
                System.exit(0);
            }else{
                System.out.println("Opção inválida");
            }
        }
    
    }

}
