package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import java.sql.Date;
import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Movimentacao;
import br.ufma.util.LerTerminal;

public class CLIMovimentacao extends CLIAbstractCRUD<Movimentacao> {
    public static void main(String[] args) {
        new CLIMovimentacao(new LerTerminal()).show();
    }
    public CLIMovimentacao(LerTerminal t){
        super(t);
    }

    @Override
    public void opcoesMenu() {
        super.opcoesMenu();
        System.out.println("6 - Registrar entrada de produtos");
        System.out.println("7 - Registrar saída de produtos");
        System.out.println("8 - Relatório de movimentações");
        
    }

    @Override
    public boolean acoesMenu(int opcao) {
        if( opcao==6) {
            entrada();
            return true;
        } else if(opcao==7){
            saida();
            return true;
        } else if(opcao==8){
            relatorio();
            return true;
        }
        return super.acoesMenu(opcao);
    }

    @Override
    public void showEntity(Movimentacao movimentacao) {
        System.out.println("============Produto================");

        System.out.println("Id: " + movimentacao.getId());
        System.out.println("Tipo: " + movimentacao.getTipoDeTransacao());
        System.out.println("Quantidade: " + movimentacao.getQuantidade());
        System.out.println("Data: " + movimentacao.getData());
        System.out.println("=======================================");
    }

    @Override
    public MovimentacaoDAO getDAO() {
        return new MovimentacaoDAO(SingletonConnectionDB.getConnection());

    }

    @Override
    public String getName() {
         return Movimentacao.class.getSimpleName();
    }

    @Override
    public Movimentacao get() throws Exception {
        Integer id = terminal.nextInt("Id movimentacao", false);
        Movimentacao movimentacao=getDAO().get(id);
        return movimentacao;
    }

    public Movimentacao loadWhithType(Movimentacao movimentacao) {
        System.out.println("Tipo de entrada ou saída:");
        System.out.println("1- Entrada");
        System.out.println("2- Saída");
        int tipo=terminal.nextInt("tipo", true);
        if(tipo==1){
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.ENTRADA);
        }else if(tipo==2){
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.SAIDA);
        }   
        return load(movimentacao);
    }
    public Movimentacao load(Movimentacao movimentacao) {
       
        movimentacao.setQuantidade(terminal.nextInt("Quantidade", true));
        try{
            
            movimentacao.setFuncionario(new CLIFuncionario(terminal).get());
            if(movimentacao.getFuncionario()==null ){
                System.out.println("Funcionario nao encontrado");
                return null;
            }
        }catch(Exception e){
            System.out.println("Erro ao buscar funcionário");
            
        }
        try {
            movimentacao.setProduto(new CLIProduto(terminal).get());
            if(movimentacao.getProduto()==null ){
                System.out.println("Funcionario nao encontrado");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto");
        }
        try {
            movimentacao.setEstoque(new CLIEstoque(terminal).get());
            if(movimentacao.getEstoque()==null ){
                System.out.println("Estoque nao encontrado");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar estoque");
        }
        movimentacao.setData(new Date(System.currentTimeMillis()));
        return movimentacao;

    }
    @Override
    public Movimentacao create() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao=loadWhithType(movimentacao);
      
        return movimentacao;
    }

   
    public void entrada() {
        Movimentacao movimentacao = new Movimentacao();
        
        try {
            movimentacao=load(movimentacao);
            getDAO().registrarEntrada(movimentacao.getEstoque().getId(), movimentacao.getProduto().getId(),movimentacao.getFuncionario().getCpf(), movimentacao.getQuantidade(), movimentacao.getDescricao());
            System.out.println("Entrada registrada com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao registrar entrada");
            System.out.println(e.getMessage());
        }
        
    }

    public void saida() {
        Movimentacao movimentacao = new Movimentacao();
        
        try {
            movimentacao=load(movimentacao);
            getDAO().registrarSaida(movimentacao.getEstoque().getId(), movimentacao.getProduto().getId(),movimentacao.getFuncionario().getCpf(), movimentacao.getQuantidade(), movimentacao.getDescricao());
            System.out.println("Saída registrada com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao registrar saída");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Movimentacao update(Movimentacao o) {
        Movimentacao movimentacao;
        try {
            movimentacao = get();
            movimentacao=loadWhithType(movimentacao);
            return movimentacao;
        } catch (Exception e) {
           System.out.println("Erro ao buscar movimentação");
        }
        return null;
    }
    void relatorio() {
        try {
            new CliTable().visualize(getDAO().relatorio());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}
