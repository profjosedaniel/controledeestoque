package br.ufma.ppgee.eds.sistemacontroleestoque.gui.movimentacao;

import javax.swing.JOptionPane;

import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.AdapterInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.EstoqueAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.MovimentacaoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.adapter.ProdutoAdapter;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.DAOInterface;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Movimentacao;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.gui.basic.SaveActionRow;

public class SaveMovimentacaoController extends SaveActionRow<Movimentacao> {
    
    public void save(Movimentacao t) throws Exception {
        MovimentacaoDAO dao= (MovimentacaoDAO) getDao();
        if(!check(t)){
           
            try{
                if(t.getTipoDeTransacao()==Movimentacao.TipoDeTransacao.ENTRADA){ 
                    dao.registrarEntrada(t.getEstoque().getId(), t.getProduto().getId(), t.getFuncionario().getCpf(), t.getQuantidade(), t.getDescricao());
                }else{
                    dao.registrarSaida(t.getEstoque().getId(), t.getProduto().getId(), t.getFuncionario().getCpf(), t.getQuantidade(), t.getDescricao());
                }
            }catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Quantidade de itens invalida");
            }catch(Exception e){
                throw new Exception("Erro ao salvar movimentação");
            }
        }else{
            dao.update(t);
        }
        
    }

    @Override
    public AdapterInterface getAdapter() {
        return new MovimentacaoAdapter();
      
    }
    @Override
    public DAOInterface getDao() {

        return new MovimentacaoDAO(SingletonConnectionDB.getConnection());
        
    }
    @Override
    public boolean check(Movimentacao t) throws Exception {
       return  t.getId()!=0;
    }
      

    
}
