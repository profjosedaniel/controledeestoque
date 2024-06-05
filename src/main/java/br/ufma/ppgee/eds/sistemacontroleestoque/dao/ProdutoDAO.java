package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Produto;

public class ProdutoDAO implements DAOInterface<Produto, Integer>{

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public Produto get(String codigoDeBarras) throws SQLException {
        String sql = "SELECT ID, Nome, CodigoDeBarras, Preco, Descricao  FROM Produto WHERE CodigoDeBarras = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, codigoDeBarras);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Produto produto=new Produto();
            produto.setId(resultSet.getInt("ID"));
            produto.setNome(resultSet.getString("nome"));
            produto.setCodigoDeBarras(resultSet.getString("CodigoDeBarras"));
            produto.setPreco(resultSet.getDouble("Preco"));
            produto.setDescricao(resultSet.getString("Descricao"));
            // produto.setQuantidadeEmEstoque(resultSet.getInt("QuantidadeEmEstoque"));


            return produto;
        }
        return null;
    }
    public Produto get(Integer id) throws SQLException {
        String sql = "SELECT ID, Nome, CodigoDeBarras, Preco, Descricao  FROM Produto WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Produto produto=new Produto();
            produto.setId(resultSet.getInt("ID"));
            produto.setNome(resultSet.getString("nome"));
            produto.setCodigoDeBarras(resultSet.getString("CodigoDeBarras"));
            produto.setPreco(resultSet.getDouble("Preco"));
            produto.setDescricao(resultSet.getString("Descricao"));
     
            return produto;
        }
        return null;
    }

    public List<Produto> getAll() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Produto produto=new Produto();
            produto.setId(resultSet.getInt("ID"));
            produto.setNome(resultSet.getString("nome"));
            produto.setCodigoDeBarras(resultSet.getString("CodigoDeBarras"));
            produto.setPreco(resultSet.getDouble("Preco"));
            produto.setDescricao(resultSet.getString("Descricao"));
           
            produtos.add(produto );
        }
        return produtos;
    }

    public Integer create(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (Nome, CodigoDeBarras, Preco, Descricao) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, produto.getNome());
        statement.setString(2, produto.getCodigoDeBarras());
        statement.setDouble(3, produto.getPreco());
        statement.setString(4, produto.getDescricao());
        
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys(); 
        if (rs.next()) { 
            return rs.getInt(1); 
        } 
        return null;  
    }

    public void update(Produto produto) throws SQLException {
        String sql =  "UPDATE Produto SET Nome = ?, CodigoDeBarras = ?, Preco = ?, Descricao = ? WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, produto.getNome());
        statement.setString(2, produto.getCodigoDeBarras());
        statement.setDouble(3, produto.getPreco());
        statement.setString(4, produto.getDescricao());
    
        statement.setInt(5, produto.getId());
        statement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void delete(String codigoBarras){ 
        String sql = "DELETE FROM Produto WHERE CodigoDeBarras = ?";
        try (Connection conn = this.connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoBarras);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteE(Produto o) throws SQLException {
        delete(o.getId());
    }

    public ResultSet relatorio() throws SQLException {
        String sql = "SELECT * FROM viewrelatorioproduto";
        PreparedStatement pstmt  = this.connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }

 
}
