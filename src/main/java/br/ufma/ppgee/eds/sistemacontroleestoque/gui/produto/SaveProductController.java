package br.ufma.ppgee.eds.sistemacontroleestoque.gui.produto;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.ProdutoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class SaveProductController extends SaveActionRow<Produto> {
    


    @Override
    public AdapterInterface getAdapter() {
        return new ProdutoAdapter();
      
    }
    @Override
    public DAOInterface getDao() {

        return new ProdutoDAO(SingletonConnectionDB.getConnection());
        
    }
    @Override
    public boolean check(Produto t) throws Exception {
       return t.getId()!=null&&t.getId()!=0;
    }
      

    
}
