package br.ufma.util;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario.Papel;

public class TestUser {
    @Test
    @Order(1)
    public void create() throws SQLException{
        String password="123456";

        Funcionario u = new Funcionario();
        u.setCpf("12345678901");
        u.setNome("User 1");
        u.setEmail("asd@asdas.com");
        u.setPassword(password);
        u.setPapel(Papel.COMPRADOR);
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());

         dao.create(u);
       
    }
    @Test
    @Order(2)
    public void login() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());

        assert dao.login("12345678901", "123456") == null;
    }
    @Test
    @Order(3)
    public void get() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
     
        Funcionario u = dao.get("12345678901");
        assert u != null;
        assert u.getNome().equals("User 1");
        assert u.getEmail().equals("asd@asdas.com");
        assert u.getPassword().equals(Hash.hash("123456"));
        assert u.getPapel().equals(Papel.COMPRADOR);
    }

    @Test
    @Order(4)
    public void updatePassword() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        
        dao.updatePassword("12345678901","98776");
        Funcionario u = dao.get("12345678901");
        assert u == null;
        assert u.getPassword().equals(Hash.hash("98776"));
        
    }

    @Test
    @Order(4)
    public void update() throws SQLException{
        Funcionario u = new Funcionario();
        u.setCpf("12345678901");
        u.setNome("User 2");
        u.setEmail("2@ASDAs.com");
        u.setPapel(Papel.GERENTE);
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        dao.update(u);    

        Funcionario u2 = dao.get("12345678901");
        assert u2 == null;
        assert u2.getNome().equals("User 2");
        assert u2.getEmail().equals("2@ASDAs.com");
        assert u2.getPapel().equals(Papel.GERENTE);
    }

    @Test
    @Order(5)
    public void delete() throws SQLException{
        FuncionarioDAO dao = new FuncionarioDAO(SingletonConnectionDB.getConnection());
        dao.delete("12345678901");
        Funcionario u = dao.get("12345678901");
        assert u == null;
    }
}
