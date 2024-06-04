package br.ufma.ppgee.eds.sistemacontroleestoque.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Estoque;
 
public class EstoqueDAO implements DAOInterface<Estoque, Integer> {
 

    private Connection connection;

    public EstoqueDAO(Connection connection) {
        this.connection = connection;
    }
    public Integer create(Estoque estoque) throws SQLException {
        String sql = "INSERT INTO Estoque (nome, localizacao) VALUES (?, ?)";
       
            PreparedStatement pstmt = this.connection.prepareStatement(sql) ;
            pstmt.setString(1, estoque.getNome());
            pstmt.setString(2, estoque.getLocalizacao());
 
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys(); 
            if (rs.next()) { 
                return rs.getInt(1); 
            } 
            return null; 
      
    }
    public void update(Estoque estoque) throws SQLException{
        String sql = "UPDATE Estoque SET nome = ?, localizacao = ? WHERE id = ?";
       
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, estoque.getNome());
            pstmt.setString(2, estoque.getLocalizacao());
             pstmt.setInt(3, estoque.getId());
            pstmt.executeUpdate();
     
    }


    public Estoque get(Integer id) throws SQLException {
        String sql = "SELECT * FROM Estoque WHERE id = ?";
        Estoque estoque = null;

     
            PreparedStatement pstmt  = this.connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();

            if (rs.next()) {
                estoque = new Estoque();
                estoque.setId(rs.getInt("id"));
                estoque.setNome(rs.getString("nome"));
                estoque.setLocalizacao(rs.getString("localizacao"));
                 

            }
    
        return estoque;
    }
    public Estoque findByNome(String nome) throws SQLException {
        String sql = "SELECT * FROM Estoque WHERE nome = ?";
        Estoque estoque = null;

        
            PreparedStatement pstmt  = this.connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            
            ResultSet rs  = pstmt.executeQuery();

            if (rs.next()) {
                estoque = new Estoque();
                estoque.setId(rs.getInt("id"));
                estoque.setNome(rs.getString("nome"));
                estoque.setLocalizacao(rs.getString("localizacao"));
               
            }
 
        return estoque;
    }

    public List<Estoque> getAll() throws SQLException {
        List<Estoque> list = new ArrayList<>();
        String sql = "SELECT * FROM Estoque";

        
             PreparedStatement pstmt  = this.connection.prepareStatement(sql);
             ResultSet rs    = pstmt.executeQuery();

            while (rs.next()) {
                Estoque estoque=new Estoque();
                estoque.setId(rs.getInt("id"));
                estoque.setNome(rs.getString("nome"));
                estoque.setLocalizacao(rs.getString("localizacao"));
                 list.add(estoque);
                 
            }
    
        return list;
    }
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Estoque WHERE id = ?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql) ;
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    @Override
    public void deleteE(Estoque o) throws SQLException {
        delete(o.getId());
    }
     
}
