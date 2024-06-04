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

    public Movimentacao load(Movimentacao movimentacao) {
        System.out.println("Tipo de entrada ou saída:");
        System.out.println("1- Entrada");
        System.out.println("2- Saída");
        int tipo=terminal.nextInt("tipo", false);
        if(tipo==1){
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.ENTRADA);
        }else if(tipo==2){
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.SAIDA);
        }   
        movimentacao.setQuantidade(terminal.nextInt("Quantidade", false));
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
        movimentacao=load(movimentacao);
      
        return movimentacao;
    }

    @Override
    public Movimentacao update(Movimentacao o) {
        Movimentacao movimentacao;
        try {
            movimentacao = get();
            movimentacao=load(movimentacao);
            return movimentacao;
        } catch (Exception e) {
           System.out.println("Erro ao buscar movimentação");
        }
        return null;
    }
    
}
