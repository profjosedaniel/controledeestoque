package br.ufma.ppgee.eds.sistemacontroleestoque;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;

public class TestConnection {
    @Test
    public void testGetConnection() {
        Connection connection = SingletonConnectionDB.getConnection();
        Assert.assertNotNull("Connection should not be null", connection);
    }
}
