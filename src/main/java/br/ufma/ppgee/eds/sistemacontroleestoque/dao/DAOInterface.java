package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface <T,E>  {
 
    E create(T o) throws Exception;
    T get(E o) throws Exception;
    void update(T o) throws Exception;

    void delete(E o) throws Exception;
    void deleteE(T o) throws Exception;
    List<T> getAll() throws Exception;
    ResultSet getAllResultSet() throws Exception;
}
