package br.ufma.ppgee.eds.sistemacontroleestoque.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.entities.Produto;

public class FabricanteDAO implements DAOInterface<Fabricante, String>{

    private Connection connection;

    public FabricanteDAO(Connection connection) {
        this.connection = connection;
    }

    public Fabricante get(String cnpj) throws SQLException {
        String sql = "SELECT * FROM Fabricante WHERE CNPJ = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cnpj);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Fabricante fabricante = new Fabricante(
                    resultSet.getString("cnpj"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("contato")
            );
            return fabricante;
        }
        return null;
    }
    
    public List<Fabricante> findFabricantes(String cnpj) throws SQLException {
        List<Fabricante> fabricantes = new ArrayList<>();
        String sql = "SELECT * FROM representantefabricante inner join fabricante on cnpj = fabricante where representante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cnpj);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Fabricante fabricante = new Fabricante(
                    resultSet.getString("cnpj"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("contato")
            );
            fabricantes.add(fabricante);
        }
        return fabricantes;
    }
    public List<Fabricante> getAll() throws SQLException {
        List<Fabricante> fabricantes = new ArrayList<>();
        String sql = "SELECT * FROM Fabricante order by cnpj asc";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Fabricante fabricante = new Fabricante(
                    resultSet.getString("cnpj"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("contato")
            );
            fabricantes.add(fabricante);
        }
        return fabricantes;
    }

    public String create(Fabricante fabricante) throws SQLException {
        String sql =  "INSERT INTO Fabricante (cnpj, nome, endereco, contato) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fabricante.getCnpj());
        statement.setString(2, fabricante.getNome());
        statement.setString(3, fabricante.getEndereco());
        statement.setString(4, fabricante.getContato());
        statement.executeUpdate();
        return fabricante.getCnpj();
    }

    public void update(Fabricante fabricante) throws SQLException {
        String sql = "UPDATE Fabricante SET nome = ?, endereco = ?, contato = ? WHERE cnpj = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fabricante.getNome());
        statement.setString(2, fabricante.getEndereco());
        statement.setString(3, fabricante.getContato());
        statement.setString(4, fabricante.getCnpj());
        statement.executeUpdate();
    }

    public void delete(String cnpj) throws SQLException {
        String sql = "DELETE FROM Fabricante WHERE cnpj = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cnpj);
        statement.executeUpdate();
    }

    @Override
    public void deleteE(Fabricante o) throws SQLException {
        delete(o.getCnpj());
    }
    public void relacionarProdutoFabricante(Produto produto,Fabricante fabricante) throws SQLException {
        String sql =  "INSERT INTO  ProdutoFabricante  (produto, fabricante) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, produto.getId());
        statement.setString(2, fabricante.getCnpj());
        statement.executeUpdate();
    }

    public boolean checkProdutoFabricante(Produto produto,Fabricante fabricante) throws SQLException {
        String sql =  "select * from  ProdutoFabricante where  produto = ? and fabricante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, produto.getId());
        statement.setString(2, fabricante.getCnpj());
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    public ArrayList<Fabricante> getFabricantes(Integer idProduto) throws SQLException {
        String sql =  "SELECT public.fabricante.* FROM public.produto inner join public.produtofabricante ON produtofabricante.produto = produto.id inner join public.fabricante ON fabricante.cnpj = produtofabricante.fabricante where produto.id = ?";
                        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idProduto);
        ResultSet result = statement.executeQuery();
        ArrayList<Fabricante> fabricantes = new ArrayList();
        while (result.next()) {
            Fabricante fabricante = new Fabricante(
                    result.getString("cnpj"),
                    result.getString("nome"),
                    result.getString("endereco"),
                    result.getString("contato")
            );
            fabricantes.add(fabricante);
        }
        return fabricantes;
    }


    public void removerRelacaoProdutoFabricante(Produto produto,Fabricante fabricante) throws SQLException {
        String sql =  "delete from ProdutoFabricante where produto = ? and fabricante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, produto.getId());
        statement.setString(2, fabricante.getCnpj());
        statement.executeUpdate();
    } 

    public ResultSet relatorioProdutoFabricante() throws SQLException {
        String sql =  "select * from viewProdutoFabricante";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    @Override
    public ResultSet getAllResultSet() throws SQLException {
 
        String sql = "SELECT * FROM Fabricante";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    } 
}
