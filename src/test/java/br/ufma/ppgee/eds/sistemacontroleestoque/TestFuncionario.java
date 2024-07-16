package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.SQLException;
 

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario.Papel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFuncionario {
    
   
    
    @Test
    @Order(1)
    public void basicTesteFuncionarioDAO() throws SQLException {
        String cpf="99999999999";
        String nome = "Funcionario 1";
        String telefone = "98999999999";
        String email = cpf+"@adsaasd.com";
        String password = "123123123";
        Papel papel = Funcionario.Papel.GERENTE;

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(cpf);    
        funcionario.setNome(nome);
        funcionario.setTelefone(telefone);
        funcionario.setPassword(password);
        funcionario.setEmail(email);
        funcionario.setPapel(papel);
 
 
        //save
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        funcionarioDAO.create(funcionario);
        //get
        Funcionario funcionario2 = funcionarioDAO.get(cpf);
        Assert.assertNotNull(funcionario2);
        Assert.assertEquals(cpf.trim(), funcionario2.getCpf().trim());
        Assert.assertEquals(nome, funcionario2.getNome());
        Assert.assertEquals(telefone, funcionario2.getTelefone());
        Assert.assertEquals(email, funcionario2.getEmail());
        Assert.assertEquals(Funcionario.Papel.GERENTE, funcionario2.getPapel());
        
 
    }

    @Test
    @Order(2)
    public void updatePassword() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
       
        Funcionario u = dao.get("12345678901");
        assert u != null;

        String newPassoword="123123123";
        dao.updatePassword("12345678901",newPassoword);
        u=dao.login("12345678901", newPassoword);
        assert  u != null;
        
    }

    @Test
    @Order(3)
    public void login() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        Funcionario u=dao.login("12345678901", "123123123");
        assert u != null;
    }

  

 
    @Test 
    @Order(5)
    public void update() throws SQLException{
        String cpf="300000002";
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        Funcionario funcionario = funcionarioDAO.get(cpf);
        //create
        String nome2= "Funcionario 2";
        String telefone2= "98999999998";
        String email2= "asdqwewqe@asadasda.edu";
        Funcionario.Papel papel2=Funcionario.Papel.COMPRADOR;
        funcionario.setNome(nome2);
        funcionario.setTelefone(telefone2);
        funcionario.setEmail(email2);
        funcionario.setPapel(papel2);
        funcionario.setCpf(cpf);
        funcionarioDAO.update(funcionario);
        //get
        Funcionario funcionario3 = funcionarioDAO.get(cpf);
        Assert.assertNotNull(funcionario3);
        Assert.assertEquals(cpf, funcionario3.getCpf());
        Assert.assertEquals(nome2, funcionario3.getNome());
        Assert.assertEquals(telefone2, funcionario3.getTelefone());
        Assert.assertEquals( email2, funcionario3.getEmail());
        Assert.assertEquals(Funcionario.Papel.COMPRADOR, funcionario3.getPapel());
    }
    @Test
    @Order(6)
    public void delete() throws SQLException{
        String cpf="99999999999";
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        funcionarioDAO.delete(cpf);
        Funcionario funcionario4 = funcionarioDAO.get(cpf);
        Assert.assertNull(funcionario4);
    }


}
