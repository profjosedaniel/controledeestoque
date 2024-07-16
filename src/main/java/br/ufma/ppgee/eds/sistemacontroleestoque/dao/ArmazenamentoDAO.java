package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Armazenamento;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Estoque;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;
import br.ufma.ppgee.eds.sistemacontroleestoque.exception.InvalidOperation;

public class ArmazenamentoDAO implements DAOInterface<Armazenamento, String> {
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
   
    public String create(Armazenamento armazenamento) throws SQLException {
        String sql = "INSERT INTO Armazenamento (estoque_id, produto_id, quantidade) VALUES (?, ?, ?)";
        
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, armazenamento.getEstoque().getId());
            statement.setInt(2, armazenamento.getProduto().getId());
            statement.setInt(3, armazenamento.getQuantidade());
            statement.execute();
        return null;
   }
   
   public void update(Armazenamento armazenamento) throws SQLException {
    String sql="UPDATE Armazenamento SET quantidade = ? WHERE estoque_id = ? AND produto_id = ?";
   
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, armazenamento.getQuantidade());
        statement.setInt(2, armazenamento.getEstoque().getId());
        statement.setInt(3, armazenamento.getProduto().getId());
        statement.execute();
   

   }
    
    public void deleteE(Armazenamento armazenamento) throws SQLException{
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
            create(armazenamento);
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

    public ResultSet relatorio() throws SQLException {
        String sql = "SELECT * FROM viewrelatorioestoque";
        PreparedStatement pstmt  = this.connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }
 

    @Override
    public List<Armazenamento> getAll() throws SQLException {
        ArrayList<Armazenamento> list=new ArrayList<Armazenamento>();
        String sql = "SELECT * FROM Armazenamento";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Armazenamento armazenamento = new Armazenamento();
            armazenamento.setEstoque(new EstoqueDAO(connection).get(resultSet.getInt("estoque_id")));
            armazenamento.setProduto(new ProdutoDAO(connection).get(resultSet.getInt("produto_id")));
            armazenamento.setQuantidade(resultSet.getInt("quantidade"));
            list.add(armazenamento);
        }
        return list;
    }
    @Override
    public ResultSet getAllResultSet() throws SQLException {
        ArrayList<Armazenamento> list=new ArrayList<Armazenamento>();
        String sql = "SELECT * FROM Armazenamento";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    @Override
    public Armazenamento get(String o) throws Exception {
        throw new InvalidOperation();
    }
    @Override
    public void delete(String o) throws Exception {
     
        throw new InvalidOperation();
    }
    
}
