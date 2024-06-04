package br.ufma.ppgee.eds.sistemacontroleestoque;
import java.sql.SQLException;
import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Movimentacao;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

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

}
