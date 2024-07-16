package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;

public class TestRepresentate {
@Test
@Order(1)
public void basicTesteRepresentanteDAO() throws Exception {
    RepresentanteDAO daoProduto = new RepresentanteDAO(SingletonConnectionDB.getConnection());
    Representante representante = new Representante();
    String nome="Representante 1";
    String cpf="12345678901";
    String telefone="98999999999";
    String email="asds@asdasd.com";
    representante.setNome(nome);
    representante.setCpf(cpf);
    representante.setTelefone(telefone);
    representante.setEmail(email);
    //save
    daoProduto.create(representante);
    //get
    representante = daoProduto.get(cpf);
    assert representante != null;
    //check
    assert representante.getNome().equals(nome);
    assert representante.getCpf().equals(cpf);
    assert representante.getTelefone().equals(telefone);
    assert representante.getEmail().equals(email);
    //update
    String nome2="Representante 2";
    String telefone2="98999999998";
    String email2="";

    representante.setNome(nome2);
    representante.setTelefone(telefone2);
    representante.setEmail(email2);
    daoProduto.update(representante);
    //get
    Representante representante2 = daoProduto.get(cpf);
    assert representante2 != null;
    //check
    assert representante2.getNome().equals(nome2);
    assert representante2.getCpf().equals(cpf);
    assert representante2.getTelefone().equals(telefone2);
    assert representante2.getEmail().equals(email2);
    //delete
    daoProduto.delete(cpf);
    //get
    representante = daoProduto.get(cpf);
    assert representante == null;
    

}

@Test
@Order(1)
public void basicTesteFabricanteRepresentanteDAO() throws SQLException {
    FabricanteDAO daoFabricante = new FabricanteDAO(SingletonConnectionDB.getConnection());
    Fabricante fabricante = daoFabricante.get("1000000000001");
    assert fabricante != null;

    RepresentanteDAO daoRepresentante = new RepresentanteDAO(SingletonConnectionDB.getConnection());
    Representante representante = daoRepresentante.get("400000003");
    assert representante != null;

    representante.addFabricante(fabricante);
    daoRepresentante.updateListaRepresentacoes(representante);
    representante = daoRepresentante.get("400000003");
    assert representante != null;
    assert representante.getFabricante() != null;
    assert representante.getFabricante().size() > 0;
    System.out.println(representante.getFabricante().get(0).getCnpj());
    assert representante.getFabricante().get(0).getCnpj().equals( fabricante.getCnpj());
    

    representante.removeFabricante(fabricante);
    daoRepresentante.updateListaRepresentacoes(representante);
    representante = daoRepresentante.get("400000003");
    assert representante.getFabricante().size() == 0;


    }

}
