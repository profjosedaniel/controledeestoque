package br.ufma.ppgee.eds.sistemacontroleestoque.cli;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;

public interface CLIInterface <T> {
    void show();
    void showEntity(T o);
    void getEntity();
    void createEntity();
    void updateEntity();
    void deleteEntity();
    void getAllEntity();

    DAOInterface<T,?> getDAO();
    String getName();
    T get() throws Exception;
    T create();
    T update(T o);
}
