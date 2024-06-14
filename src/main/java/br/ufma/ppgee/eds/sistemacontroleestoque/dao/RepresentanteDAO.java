package br.ufma.ppgee.eds.sistemacontroleestoque.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Fabricante;
import br.ufma.ppgee.eds.sistemacontroleestoque.model.Representante;

public class RepresentanteDAO implements DAOInterface<Representante, String> {

    private Connection connection;
    private FabricanteDAO fabricanteDAO;
    public RepresentanteDAO(Connection connection) {
        this.connection = connection;
        fabricanteDAO = new FabricanteDAO(connection);
    }

    public List<Fabricante> getAllFabricantes(String cpf) throws SQLException {
        String sql = "SELECT * FROM RepresentanteFabricante WHERE Representante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        ResultSet resultSet = statement.executeQuery();
        List<Fabricante> representantes = new ArrayList<>();
        while (resultSet.next()) {
            Fabricante fabricante = new Fabricante();
            fabricante.setCnpj(resultSet.getString("Fabricante"));
            representantes.add(fabricante);
        }
        return representantes;
    }

    public void createRepresentacao(Representante representante, Fabricante fabricante) throws SQLException {
        String sql="insert into RepresentanteFabricante (Representante, Fabricante) values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, representante.getCpf());
        statement.setString(2, fabricante.getCnpj());
        statement.executeUpdate();
    }

    public void deleteRepresentacao(Representante representante, Fabricante fabricante) throws SQLException {
        String sql= "delete from RepresentanteFabricante where Representante = ? and Fabricante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, representante.getCpf());
        statement.setString(2, fabricante.getCnpj());
        statement.executeUpdate();
    }

    public void updateListaRepresentacoes(Representante representante) throws SQLException {
        List<Fabricante> fabricantesAtuais = getAllFabricantes(representante.getCpf());
        for (Fabricante fabricante : representante.getFabricante()) {
            if (!fabricantesAtuais.contains(fabricante)) {
                createRepresentacao(representante, fabricante);
            }
        }
        for (Fabricante fabricante : fabricantesAtuais) {
             if (!representante.getFabricante().contains(fabricante)) {
                 deleteRepresentacao(representante, fabricante);
             }
        }
    }

    public Representante get(String cpf) throws SQLException {
        String sql = "SELECT * FROM Representante WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Representante representante = new Representante();
            representante.setCpf(resultSet.getString("CPF"));
            representante.setNome(resultSet.getString("Nome"));
            representante.setTelefone(resultSet.getString("Telefone"));
            representante.setEmail(resultSet.getString("Email"));
            representante.setFabricante(getAllFabricantes(cpf));
            List<Fabricante> fabricantes= fabricanteDAO.findFabricantes(representante.getCpf());
            representante.setFabricante(fabricantes);
            return  representante;
        }
        return null;
    }

    public List<Representante> getAll() throws SQLException {
        List<Representante> representantes = new ArrayList<>();
        String sql = "SELECT * FROM Representante";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Representante representante = new Representante();
            representante.setCpf(resultSet.getString("CPF"));
            representante.setNome(resultSet.getString("Nome"));
            representante.setTelefone(resultSet.getString("Telefone"));
            representante.setEmail(resultSet.getString("Email"));
            List<Fabricante> fabricantes= fabricanteDAO.findFabricantes(representante.getCpf());
            representante.setFabricante(fabricantes);
            representantes.add(representante);
        }
        return representantes;
    }

    public String create(Representante representante) throws SQLException {
        String sql = "INSERT INTO Representante (CPF, Nome, Telefone, Email) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, representante.getCpf());
        statement.setString(2, representante.getNome());
        statement.setString(3, representante.getTelefone());
        statement.setString(4, representante.getEmail());
        statement.executeUpdate();
        return representante.getCpf();
    }

    public void update(Representante representante) throws SQLException {
        String sql = "UPDATE Representante SET Nome = ?, Telefone = ?, Email = ? WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, representante.getNome());
        statement.setString(2, representante.getTelefone());
        statement.setString(3, representante.getEmail());
        statement.setString(4, representante.getCpf());
        statement.executeUpdate();

        updateListaRepresentacoes(representante);
    }

    public void delete(String cpf) throws SQLException {
        String sql = "DELETE FROM Representante WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.executeUpdate();
    }
    @Override
    public void deleteE(Representante o) throws SQLException {
        delete(o.getCpf());
    }

    public void addRepresentacao(String cpf,String cnpj) throws SQLException {
        String sql = "insert into RepresentanteFabricante (Representante, Fabricante) values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, cnpj);
        statement.executeUpdate();
    }
    public void removeRepresentacao(String cpf,String cnpj) throws SQLException {
        String sql = "delete from RepresentanteFabricante where Representante = ? and Fabricante = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, cnpj);
        statement.executeUpdate();
    }

  
    public ResultSet relatorio() throws SQLException {
        String sql = "SELECT  * from representantes_fabricantes";
        PreparedStatement statement = connection.prepareStatement(sql);
 
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    @Override
    public ResultSet getAllResultSet() throws SQLException {
        String sql = "SELECT * FROM Representante";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }
 }
