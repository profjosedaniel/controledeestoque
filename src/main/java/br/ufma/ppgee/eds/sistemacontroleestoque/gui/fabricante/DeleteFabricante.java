package br.ufma.ppgee.eds.sistemacontroleestoque.gui.fabricante;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.FabricanteAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.DeleteActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class DeleteFabricante extends DeleteActionRow{

    @Override
    public AdapterInterface getAdapter() {
        return new FabricanteAdapter();
    }

    @Override
    public DAOInterface getDao() {
        return new FabricanteDAO(SingletonConnectionDB.getConnection());
    }

    @Override
    public boolean check(Object t) throws Exception {
         if(t!=null && ((Fabricante)t).getCnpj()!=null)
            return getDao().get(((Fabricante)t).getCnpj())!=null;	
        return false;
    }

}
