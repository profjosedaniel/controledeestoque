package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;

public class TestFabricante {

    @Test
    public void testSave() throws SQLException {
        Fabricante fabricante = new Fabricante();
        String cnpj="12345678901234";
        fabricante.setCnpj(cnpj);
        fabricante.setNome("Fabricante 1");
        fabricante.setEndereco("Endereco 1");
        fabricante.setContato("Contato 1");
        //save
        FabricanteDAO fabricanteDAO = new FabricanteDAO(SingletonConnectionDB.getConnection());
        fabricanteDAO.create(fabricante);
        //get
        Fabricante fabricante2 = fabricanteDAO.get(cnpj);
        Assert.assertNotNull(fabricante2);
        //update
        String nome= "Fabricante 2";
        fabricante2.setNome(nome);
        fabricanteDAO.update(fabricante2);
        Fabricante fabricante3 = fabricanteDAO.get(cnpj);
        Assert.assertEquals(nome, fabricante3.getNome());
        //delete
        fabricanteDAO.delete(cnpj);
        Fabricante fabricante4 = fabricanteDAO.get(cnpj);
        Assert.assertNull(fabricante4);
    }
}
