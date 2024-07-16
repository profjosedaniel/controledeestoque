package br.ufma.ppgee.eds.sistemacontroleestoque.gui.funcionario;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.FuncionarioAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.DeleteActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class DeleteFuncionario extends DeleteActionRow{

    @Override
    public AdapterInterface getAdapter() {
        return new FuncionarioAdapter();
    }

    @Override
    public DAOInterface getDao() {
        return new FuncionarioDAO(SingletonConnectionDB.getConnection());
    }

    @Override
    public boolean check(Object t) throws Exception {
        if(t!=null && ((Funcionario)t).getCpf()!=null)
            return getDao().get(((Funcionario)t).getCpf())!=null;	
        return false;
    }

}
