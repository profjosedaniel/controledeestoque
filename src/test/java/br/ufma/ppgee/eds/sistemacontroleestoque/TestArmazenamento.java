package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ArmazenamentoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Armazenamento;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class TestArmazenamento {

    @Test
    public void test() throws SQLException {

        // save produto
        ProdutoDAO daoProduto = new ProdutoDAO(SingletonConnectionDB.getConnection());
        Produto produto = new Produto();
        String codigoDeBarras=""+new Random().nextInt(10000)*100;
        produto. setNome("Produto 1");
        produto.setDescricao("Descricao 1");
        produto.setPreco(10.0);
        produto.setQuantidadeEmEstoque(10);
        produto.setCodigoDeBarras(codigoDeBarras);
        daoProduto.create(produto);
        produto = daoProduto.get(codigoDeBarras);
        assert produto != null;

        //save estoque
        EstoqueDAO daoEstoque = new EstoqueDAO(SingletonConnectionDB.getConnection());
        Estoque estoque = new Estoque();
        String nome="Estoque "+new Random().nextInt(1000);
        estoque.setNome(nome);
        estoque.setLocalizacao(codigoDeBarras);
 

        daoEstoque.create(estoque);
        estoque = daoEstoque.findByNome(nome);
        assert estoque != null;
        
        //save armazenamento
        ArmazenamentoDAO daoArmazenamento = new ArmazenamentoDAO(SingletonConnectionDB.getConnection());
        Armazenamento armazenamento = new Armazenamento();
        armazenamento.setEstoque(estoque);
        armazenamento.setProduto(produto);
        armazenamento.setQuantidade(10);
 
        daoArmazenamento.save(armazenamento);
        armazenamento = daoArmazenamento.get(estoque, produto);
        assert armazenamento != null;
        //check
        assert armazenamento.getEstoque().getId() == estoque.getId();
        assert armazenamento.getProduto().getId() == produto.getId();
  
        //check
        armazenamento = daoArmazenamento.get(estoque, produto);
        assert armazenamento.getQuantidade() == 10;
        //delete
        daoArmazenamento.delete(armazenamento);
        armazenamento = daoArmazenamento.get(estoque, produto);
        assert armazenamento == null;
    }
}
