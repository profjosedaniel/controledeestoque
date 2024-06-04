package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import br.ufma.util.LerTerminal;
 
public class CLIMenu {
    public static final int OPCAO_PRODUTOS = 1;
    public static final int OPCAO_FUNCIONARIOS = 2;
    public static final int OPCAO_FABRICANTE = 3;
    public static final int OPCAO_ESTOQUE = 4;
    public static final int OPCAO_REPRESENTANTE = 5;
    public static final int OPCAO_MOVIMENTACAO = 5;

    public void show() {
        while(true){
            LerTerminal terminal = new LerTerminal();
            System.out.println("==========Menu================");
            System.out.println(OPCAO_PRODUTOS+" - Produtos");
            System.out.println(OPCAO_FUNCIONARIOS+" - Funcionarios");
            System.out.println(OPCAO_FABRICANTE+" - Fabricante");
            System.out.println(OPCAO_ESTOQUE+" - Estoque");
            System.out.println(OPCAO_ESTOQUE+" - Representante");
            System.out.println(OPCAO_MOVIMENTACAO+" - Movimentacao");
            System.out.println("0 - Sair");
            System.out.println("=======================================");
    
            Integer opcao = terminal.nextInt("Selecione uma opção válida",true);
            if (opcao==OPCAO_ESTOQUE) {
                new CLIProduto(terminal).show();
            } else if (opcao==OPCAO_FUNCIONARIOS) {
                new CLIFuncionario(terminal).show();
            } else if (opcao==OPCAO_FABRICANTE) {
                new CLIFabricante(terminal).show();
            } else if (opcao==OPCAO_PRODUTOS) {
                new CLIProduto(terminal).show();
            } else if (opcao==OPCAO_REPRESENTANTE) {
                new CLIRepresentante(terminal).show();
            } else if (opcao==OPCAO_MOVIMENTACAO) {
                new CLIMovimentacao(terminal).show();
            } else if (opcao==0)
                System.exit(0);
            }
        }
    
    

}
