package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.Movimentacao;

public class MovimentacaoDAO implements DAOInterface<Movimentacao,Integer>{
    private Connection connection;
    public MovimentacaoDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Movimentacao get(Integer id ) throws SQLException {
        String sql="SELECT * FROM Movimentacao WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setId(resultSet.getInt("id"));
            movimentacao.setData(resultSet.getDate("dataEHora"));
            movimentacao.setQuantidade(resultSet.getInt("quantidade"));
            movimentacao.setValorUnitario(resultSet.getDouble("valorUnitario"));
            movimentacao.setDescricao(resultSet.getString("descricao"));
            movimentacao.setProduto(new ProdutoDAO(connection).get(resultSet.getInt("produto")));
            movimentacao.setEstoque(new EstoqueDAO(connection).get(resultSet.getInt("estoque")));
            movimentacao.setFuncionario(new FuncionarioDAO(connection).get(resultSet.getString("funcionario")));
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.valueOf(resultSet.getString("tipoDeTransacao").toUpperCase()));
            return movimentacao;
        }

        return null;
    }

    @Override
    public Integer create(Movimentacao o) throws SQLException {
        String sql="INSERT INTO Movimentacao (dataEHora, quantidade, valorunitario, descricao, produto, estoque, funcionario, tipoDeTransacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, o.getData());
        statement.setInt(2, o.getQuantidade());
        statement.setDouble(3, o.getValorUnitario());
        statement.setString(4, o.getDescricao());
        statement.setInt(5, o.getProduto().getId());
        statement.setInt(6, o.getEstoque().getId());
        statement.setString(7, o.getFuncionario().getCpf());
        statement.setString(8, o.getTipoDeTransacao().toString());
        statement.executeUpdate();   
        ResultSet rs = statement.getGeneratedKeys(); 
        if (rs.next()) { 
            return rs.getInt(1); 
        } 
        return null;      
    }

 

    @Override
    public void update(Movimentacao o) throws SQLException{
       String sql="UPDATE Movimentacao SET dataEHora=?, quantidade=?, valorUnitario=?, descricao=?, produto=?, estoque=?, funcionario=?, tipoDeTransacao=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, o.getData());
        statement.setInt(2, o.getQuantidade());
        statement.setDouble(3, o.getValorUnitario());
        statement.setString(4, o.getDescricao());
        statement.setInt(5, o.getProduto().getId());
        statement.setInt(6, o.getEstoque().getId());
        statement.setString(7, o.getFuncionario().getCpf());
        statement.setString(8, o.getTipoDeTransacao().toString());
        statement.setInt(9, o.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException{
        String sql="DELETE FROM Movimentacao WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Movimentacao> getAll() throws SQLException {
        String sql="SELECT * FROM Movimentacao";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
        while (resultSet.next()) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setId(resultSet.getInt("id"));
            movimentacao.setData(resultSet.getDate("dataEHora"));
            movimentacao.setQuantidade(resultSet.getInt("quantidade"));
            movimentacao.setValorUnitario(resultSet.getDouble("valorUnitario"));
            movimentacao.setDescricao(resultSet.getString("descricao"));
            movimentacao.setProduto(new ProdutoDAO(connection).get(resultSet.getInt("produto")));
            movimentacao.setEstoque(new EstoqueDAO(connection).get(resultSet.getInt("estoque")));
            movimentacao.setFuncionario(new FuncionarioDAO(connection).get(resultSet.getString("funcionario")));
            movimentacao.setTipoDeTransacao(Movimentacao.TipoDeTransacao.valueOf(resultSet.getString("tipoDeTransacao")));
            movimentacoes.add(movimentacao);
        }
        return movimentacoes;

    }

    @Override
    public void deleteE(Movimentacao o) throws SQLException {
        delete(o.getId());
    }

}
