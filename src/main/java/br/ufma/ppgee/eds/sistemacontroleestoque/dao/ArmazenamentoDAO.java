package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Armazenamento;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class ArmazenamentoDAO  {
    private Connection connection;

    public ArmazenamentoDAO(Connection connection) {
        this.connection = connection;
    }
    public Armazenamento get(Estoque estoque, Produto produto) throws SQLException {
        String sql = "SELECT * FROM Armazenamento WHERE estoque_id = ? AND produto_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, estoque.getId());
        statement.setInt(2, produto.getId());
         ResultSet rs  = statement.executeQuery();
        if(rs.next()){
            Armazenamento armazenamento = new Armazenamento();
            armazenamento.setEstoque(estoque);
            armazenamento.setProduto(produto);
            armazenamento.setQuantidade(rs.getInt("quantidade"));
            return armazenamento;
        }

        return null;
    }
   
    public void save(Armazenamento armazenamento) throws SQLException {
        String sql = "INSERT INTO Armazenamento (estoque_id, produto_id, quantidade) VALUES (?, ?, ?)";
        
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, armazenamento.getEstoque().getId());
            statement.setInt(2, armazenamento.getProduto().getId());
            statement.setInt(3, armazenamento.getQuantidade());
            statement.execute();
        
   }
   
   public void update(Armazenamento armazenamento) throws SQLException {
    String sql="UPDATE Armazenamento SET quantidade = ? WHERE estoque_id = ? AND produto_id = ?";
   
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, armazenamento.getQuantidade());
        statement.setInt(2, armazenamento.getEstoque().getId());
        statement.setInt(3, armazenamento.getProduto().getId());
        statement.execute();
   

   }
    
    public void delete(Armazenamento armazenamento) throws SQLException{
        String sql="DELETE FROM Armazenamento WHERE estoque_id = ? AND produto_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, armazenamento.getEstoque().getId());
        statement.setInt(2, armazenamento.getProduto().getId());
        statement.execute();
    }
    

    public void movimentar(Estoque estoque,Produto produto,int quantidade) throws SQLException{
        Armazenamento armazenamento=get(estoque, produto);
        if(armazenamento==null){
            armazenamento=new Armazenamento();
            armazenamento.setEstoque(estoque);
            armazenamento.setProduto(produto);
            armazenamento.setQuantidade(0);
            save(armazenamento);
        }
        armazenamento=get(estoque, produto);
        if(armazenamento!=null){
            if(armazenamento.getQuantidade()+quantidade<0){
                throw new IllegalArgumentException("Quantidade insuficiente no estoque");
            }
            armazenamento.setQuantidade(armazenamento.getQuantidade()+quantidade);
            update(armazenamento);
        }
    }

}
