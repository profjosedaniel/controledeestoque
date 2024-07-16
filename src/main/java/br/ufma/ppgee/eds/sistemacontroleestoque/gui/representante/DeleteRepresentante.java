package br.ufma.ppgee.eds.sistemacontroleestoque.gui.representante;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.RepresentanteAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.DeleteActionRow;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class DeleteRepresentante extends DeleteActionRow{

    @Override
    public AdapterInterface getAdapter() {
        return new RepresentanteAdapter();
    }

    @Override
    public DAOInterface getDao() {
        return new RepresentanteDAO(SingletonConnectionDB.getConnection());
    }

    @Override
    public boolean check(Object t) throws Exception {
        if(t!=null && ((Representante)t).getCpf()!=null)
            return getDao().get(((Representante)t).getCpf())!=null;	
        return false;
    }

}
