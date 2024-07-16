package br.ufma.ppgee.eds.sistemacontroleestoque.adapter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufma.ppgee.eds.sistemacontroleestoque.dao.EstoqueDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.FuncionarioDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.MovimentacaoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.ProdutoDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.dao.RepresentanteDAO;
import br.ufma.ppgee.eds.sistemacontroleestoque.database.SingletonConnectionDB;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Funcionario;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Movimentacao;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Representante;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.Validation;
import br.ufma.ppgee.eds.sistemacontroleestoque.validation.ValidationFieldException;

public class MovimentacaoAdapter implements AdapterInterface<Movimentacao>{
    private MovimentacaoDAO dao;
    public MovimentacaoAdapter(){
         dao =new MovimentacaoDAO(SingletonConnectionDB.getConnection());
    }
    public String [] getColumns(){
        return new String[] { "id","quantidade","data","valorUnitario","descricao"};
    }

    public Movimentacao load(  Map<String,String>  values) throws ValidationFieldException{
        Validation.minmaxFloat(values.get("quantidade"), "quantidade", 0,100000000);
        Validation.minmaxFloat(values.get("valorUnitario"), "valorUnitario", 0,100000000);
        Validation.minMaxSizeString(values.get("descricao"), "descricao",8,100);
       

        Movimentacao movimentacao = new Movimentacao();
        if(!(values.get("id")==null||values.get("id").isEmpty())){
            movimentacao.setId( Integer.parseInt(values.get("id")));
        }
        if(Movimentacao.TipoDeTransacao.ENTRADA.toString().equals(values.get("tipo"))){
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.ENTRADA);
        }else{
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.SAIDA);
        }

        int idProduto=Integer.parseInt(values.get("produto"));
        String cpf=values.get("funcionario");
        int estoque=Integer.parseInt(values.get("estoque"));
        try{
            Produto produto2 = new ProdutoDAO(SingletonConnectionDB.getConnection()).get(idProduto);
            Funcionario funcionario2 = new FuncionarioDAO(SingletonConnectionDB.getConnection()).get(cpf);
            Estoque estoque2 = new EstoqueDAO(SingletonConnectionDB.getConnection()).get(estoque);
            movimentacao.setProduto(produto2);
            movimentacao.setFuncionario(funcionario2);
            movimentacao.setEstoque(estoque2);
            movimentacao.setQuantidade(Integer.parseInt(values.get("quantidade")));
            movimentacao.setValorUnitario(Double.parseDouble(values.get("valorUnitario")));
            movimentacao.setDescricao(values.get("descricao"));
        }catch(Exception e){
            System.out.println("Erro ao buscar funcionário");

        }

      
        return movimentacao;

    }

    public Map<String,String> get(Movimentacao movimentacao) {
        HashMap<String,String> map=new HashMap<String,String>();
        
        map.put("id",String.valueOf(movimentacao.getId()));
        map.put("tipo",movimentacao.getTipoDeTransacao().toString());
        map.put("produto",String.valueOf(movimentacao.getProduto().getId()));
        map.put("funcionario",movimentacao.getFuncionario().getCpf());
        map.put("estoque",String.valueOf(movimentacao.getEstoque().getId()));
        map.put("quantidade",String.valueOf(movimentacao.getQuantidade()));
        map.put("valorUnitario",String.valueOf(movimentacao.getValorUnitario()));
        map.put("descricao",movimentacao.getDescricao());
        Date d = movimentacao.getData();
        if(d!=null){
            map.put("data", d.toString());
        }
       


        return map;
    }
    
    public List<Map> getList() throws SQLException{
        List<Map> list=new ArrayList<Map>();
        
        dao.getAll().forEach(value -> {
            list.add(get(value));
        });

        return list;
    }
}
