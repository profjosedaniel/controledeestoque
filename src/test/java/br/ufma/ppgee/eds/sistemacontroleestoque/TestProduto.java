package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class TestProduto {

    @Test
    public void basicTestProdutoDAO() throws SQLException {
        FabricanteDAO daoFabricante = new FabricanteDAO(SingletonConnectionDB.getConnection());
        Fabricante fabricante = daoFabricante.get("1000000000001");

        ProdutoDAO daoProduto = new ProdutoDAO(SingletonConnectionDB.getConnection());
        Produto produto = new Produto();
        String codigoBarras="99"+new Random().nextLong();
        String nome="Produto 1";
        String descricao="Descricao 1";
        Double preco=10.0;

        int quantidadeEmEstoque=10;
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidadeEmEstoque(quantidadeEmEstoque);
        produto.setCodigoDeBarras(codigoBarras);
        produto.setFabricante(fabricante);
        //save
        daoProduto.create(produto);
        // get e 
        Produto produto2 = daoProduto.get(codigoBarras);
        assert produto2 != null;
        assert produto2.getCodigoDeBarras().equals(codigoBarras);
        assert produto2.getId() > 0; 
        //check
        
        assert produto2.getNome().equals("Produto 1");
        assert produto2.getDescricao().equals("Descricao 1");
        assert produto2.getPreco() == 10.0;
        assert produto2.getQuantidadeEmEstoque() == 10;
        //update
        nome="Produto 2";
        descricao="Descricao 2";
        preco=20.0;
        quantidadeEmEstoque=20;
        produto2.setNome(nome);
        produto2.setDescricao(descricao);
        produto2.setPreco(preco);
        produto2.setQuantidadeEmEstoque(quantidadeEmEstoque);
         
        daoProduto.update(produto2);
        //get
        Produto produto3 = daoProduto.get(produto2.getCodigoDeBarras());
        assert produto3 != null;
        assert produto3.getNome().equals(nome);
        assert produto3.getDescricao().equals(descricao);
        assert produto3.getPreco() == 20.0;
        assert produto3.getQuantidadeEmEstoque() == quantidadeEmEstoque;    
        //delete
        daoProduto.delete(produto3.getId());
        Produto produto4 = daoProduto.get(codigoBarras);
        assert produto4 == null;



        
    }
}
