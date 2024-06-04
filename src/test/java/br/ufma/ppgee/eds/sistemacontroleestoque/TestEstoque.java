package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class TestEstoque {
    
    public void test ()   throws Exception{
        ProdutoDAO daoProduto = new ProdutoDAO(SingletonConnectionDB.getConnection());
   
        Produto produto = new Produto();
        String nomeP="Produto 1";
        String descricaoP="Descricao 1";
        String codigoBarrasP="12345678901234";
        Double precoP=10.0;
        int quantidadeEmEstoqueP=10;
        produto.setNome(nomeP);
        produto.setDescricao(descricaoP);
        produto.setPreco(precoP);
        produto.setQuantidadeEmEstoque(quantidadeEmEstoqueP);
        produto.setCodigoDeBarras(codigoBarrasP);
        //save
        daoProduto.create(produto);
    }

    @Test
    public void testEstoque() throws Exception {
        String nome="Estoque 1";
        EstoqueDAO daoEstoque = new EstoqueDAO(SingletonConnectionDB.getConnection());
        Estoque estoque2 = daoEstoque.findByNome(nome);
   
        assert estoque2 != null;
        System.out.println(estoque2.getNome());
    }

    @Test
    @Order(1)
    public void testSave() throws Exception {
        
        EstoqueDAO daoEstoque = new EstoqueDAO(SingletonConnectionDB.getConnection());
        ProdutoDAO daoProduto = new ProdutoDAO(SingletonConnectionDB.getConnection());
        Produto produto = daoProduto.get("1234567890123");
        assert produto != null;
 

        Estoque estoque = new Estoque();
        String nome="Estoque "+new Random().nextInt(1000);
        String endereco="Endereco 1";
        int quantidade=10;
        estoque.setNome(nome);
        estoque.setLocalizacao(endereco);
        estoque.setQuantidade(quantidade);
     
        //save
        Integer id =daoEstoque.create(estoque);
        //check
        Estoque estoque2 = daoEstoque.findByNome(nome);
        System.out.println(estoque2.getNome());
        assert estoque2 != null;
        assert estoque2.getNome().equals(nome);
        assert estoque2.getLocalizacao().equals(endereco);
 
        //update

        endereco="Endereco 2";
        quantidade=20;


        estoque2.setLocalizacao(endereco);
        estoque2.setQuantidade(quantidade);
        daoEstoque.update(estoque2);
        //get
        Estoque estoque3 = daoEstoque.findByNome(estoque2.getNome());
        assert estoque3 != null;
        assert estoque3.getLocalizacao().equals(endereco);
        assert estoque3.getNome().equals(nome);

        //delete
        daoEstoque.delete(estoque3.getId());
        Estoque estoque4 = daoEstoque.get(estoque3.getId());
        Assert.assertNull(estoque4);
    }

}
