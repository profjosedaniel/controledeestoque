package br.ufma.ppgee.eds.sistemacontroleestoque.gui.estoque;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FabricanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class SaveEstoque extends SaveActionRow{

    @Override
    public AdapterInterface getAdapter() {
        return new EstoqueAdapter();
    }

    @Override
    public DAOInterface getDao() {
        return new EstoqueDAO(SingletonConnectionDB.getConnection());
    }

    @Override
    public boolean check(Object t) throws Exception {
         if(t!=null && ((Estoque)t).getId()>0){
            Estoque e = (Estoque) getDao().get(((Estoque)t).getId());
            return e.getId()>0;	
         }
            
        return false;
    }

}
