package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface <T,E>  {
 
    E create(T o) throws SQLException;
    T get(E o) throws SQLException;
    void update(T o) throws SQLException;

    void delete(E o) throws SQLException;
    void deleteE(T o) throws SQLException;
    List<T> getAll() throws SQLException;
    ResultSet getAllResultSet() throws SQLException;
}
