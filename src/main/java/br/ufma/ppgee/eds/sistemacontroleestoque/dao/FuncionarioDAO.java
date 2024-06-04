package br.ufma.ppgee.eds.sistemacontroleestoque.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Funcionario;

public class FuncionarioDAO implements DAOInterface<Funcionario, String>{

    private Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Funcionario get(String cpf) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf.trim());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(resultSet.getString("CPF"));
            funcionario.setNome(resultSet.getString("Nome"));
            funcionario.setTelefone(resultSet.getString("Telefone"));
            funcionario.setEmail(resultSet.getString("Email"));
            funcionario.setPassword(resultSet.getString("Password"));
            if(resultSet.getString("Papel")!=null)
                funcionario.setPapel( Funcionario.Papel.valueOf(resultSet.getString("Papel").toUpperCase()));

            return funcionario;
        }
        return null;
    }

    public List<Funcionario> getAll() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(resultSet.getString("CPF").trim());
            funcionario.setNome(resultSet.getString("Nome"));
            funcionario.setTelefone(resultSet.getString("Telefone"));
            funcionario.setEmail(resultSet.getString("Email"));
            funcionario.setPassword(resultSet.getString("Password"));
            if(resultSet.getString("Papel")!=null)
                funcionario.setPapel( Funcionario.Papel.valueOf(resultSet.getString("Papel").toUpperCase()));
            
            funcionarios.add(funcionario );
        }
        return funcionarios;
    }

    public String create(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (CPF, Nome, Telefone, Email, password, Papel) VALUES (?, ?, ?, ?, crypt(?, gen_salt('md5')), ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, funcionario.getCpf().trim() );
        statement.setString(2, funcionario.getNome());
        statement.setString(3, funcionario.getTelefone());
        statement.setString(4, funcionario.getEmail());
        //statement.setString(5, Hash.hash(funcionario.getPassword()));
        statement.setString(5, funcionario.getPassword());
        statement.setString(6, funcionario.getPapel().toString());
        statement.executeUpdate();
        return funcionario.getCpf();
    }

    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET Nome = ?, Telefone = ?, Email = ?, Papel = ? WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, funcionario.getNome());
        statement.setString(2, funcionario.getTelefone());
        statement.setString(3, funcionario.getEmail());
        statement.setString(4, ""+funcionario.getPapel());
        statement.setString(5, funcionario.getCpf().trim() );
        statement.executeUpdate();
    }

    public void delete(String cpf) throws SQLException {
        String sql = "DELETE FROM Funcionario WHERE CPF = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.executeUpdate();
    }

    @Override
    public void deleteE(Funcionario o) throws SQLException {
        delete(o.getCpf());
    }

    
    public void updatePassword(String cpf,String password) throws SQLException {
        String sql="UPDATE funcionario SET password=crypt(?, gen_salt('md5')) WHERE cpf= ?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
      //  pstmt.setString(1, Hash.hash( password));
        pstmt.setString(1, password);
        pstmt.setString(2, cpf);
        pstmt.executeUpdate();
    }
     
    public Funcionario login(String cpf, String password) throws SQLException {
        Funcionario u=get(cpf);
        if(u==null){
            throw new SQLException("Usuario não encontrado");
        }else{
            String sql = "SELECT * FROM Funcionario where cpf = ? and password = crypt(?, password)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.setString(2, password);
            if(statement.executeQuery().next()){
                return u;
            }
            throw new SQLException("Senha invalida");
        }
       
    }
  
}
