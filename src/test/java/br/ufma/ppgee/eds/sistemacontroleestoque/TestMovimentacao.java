package br.ufma.ppgee.eds.sistemacontroleestoque;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ArmazenamentoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Armazenamento;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Movimentacao;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;

public class TestMovimentacao {

    @Test
    public void testMovimentacao() throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        Funcionario funcionario = funcionarioDAO.get("12345678901");
        assert funcionario != null;
        
        ProdutoDAO produtoDAO = new ProdutoDAO(SingletonConnectionDB.getConnection());
        Produto produto = produtoDAO.get("1234567890123");    
        assert produto != null;

        EstoqueDAO estoqueDAO = new EstoqueDAO(SingletonConnectionDB.getConnection());
        Estoque estoque= estoqueDAO.get(1);
        assert estoque != null;

        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(SingletonConnectionDB.getConnection());

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDescricao("Movimentacao de teste");
        movimentacao.setQuantidade(10);
        movimentacao.setValorUnitario(10.0);
        movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.ENTRADA);
        movimentacao.setProduto( produto);
        movimentacao.setEstoque(estoque);
        movimentacao.setFuncionario(funcionario);
        
        movimentacaoDAO.create(movimentacao);
        
    }

    @Test
    public void saida() throws Exception{
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(SingletonConnectionDB.getConnection());
        ArmazenamentoDAO armazenamento=new ArmazenamentoDAO(SingletonConnectionDB.getConnection());
        EstoqueDAO estoqueDAO=new EstoqueDAO(SingletonConnectionDB.getConnection());
        ProdutoDAO produtoDAO=new ProdutoDAO(SingletonConnectionDB.getConnection());
       
        int quantidadeInicial=armazenamento.get(estoqueDAO.get(1), produtoDAO.get(1)).getQuantidade();
        int quatidade = 5;
        movimentacaoDAO.registrarSaida(1,1,"12345678901",quatidade,"jssjsjsj");
        int quantidadeFinal=armazenamento.get(estoqueDAO.get(1), produtoDAO.get(1)).getQuantidade();
        assert quantidadeInicial-quatidade==quantidadeFinal;
    }    

    @Test
public void entrada() throws Exception{
    MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(SingletonConnectionDB.getConnection());
    ArmazenamentoDAO armazenamento=new ArmazenamentoDAO(SingletonConnectionDB.getConnection());
    EstoqueDAO estoqueDAO=new EstoqueDAO(SingletonConnectionDB.getConnection());
    ProdutoDAO produtoDAO=new ProdutoDAO(SingletonConnectionDB.getConnection());
    int quantidadeInicial=armazenamento.get(estoqueDAO.get(1), produtoDAO.get(1)).getQuantidade();
    int quatidade = 5;
    movimentacaoDAO.registrarEntrada(1,1,"12345678901",quatidade,"jssjsjsj");

    int quantidadeFinal=armazenamento.get(estoqueDAO.get(1), produtoDAO.get(1)).getQuantidade();
    assert quantidadeInicial+quatidade==quantidadeFinal;
 }


 
 
 @Test
 public void quantidadeInsuficiente() throws Exception{
     MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(SingletonConnectionDB.getConnection());
 
     int quatidade = 100000000;

     IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
     movimentacaoDAO.registrarSaida(1,1,"12345678901",quatidade,"jssjsjsj"));

   //  assert IllegalArgumentException.class.getName()== exception.getClass().getName();
 }  
}
