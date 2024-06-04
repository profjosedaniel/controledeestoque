package br.ufma.ppgee.eds.sistemacontroleestoque.dao;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufma.ppgee.eds.sistemacontroleestoque.model.User;
import br.ufma.util.Hash;
@Deprecated
public class LoginDAO implements  DAOInterface<User,String>{
    private Connection connection;

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

 
    public User login(String cpf, String password) throws SQLException {
        User u=get(cpf);
        if(u !=null && u.getPassword().equals(""+Hash.hash(password))){
            return u;
        }
        return null;
    }

    @Override
    public String create(User o) throws SQLException {
        
        String sql="INSERT INTO public.user (cpf,name,email,password,role) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, o.getCpf());
        pstmt.setString(2, o.getName());
        pstmt.setString(3, o.getEmail());
        pstmt.setString(4, Hash.hash( o.getPassword()));
        pstmt.setString(5, o.getRole());
        pstmt.executeUpdate();
        return o.getCpf();


    }

    @Override
    public User get(String o) throws SQLException {
        String sql="SELECT * FROM public.user WHERE cpf=?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, o);
        ResultSet res = pstmt.executeQuery();
        
        if(res.next()){
            User u=new User();
            u.setCpf(res.getString("cpf"));
            u.setName(res.getString("name"));
            u.setEmail(res.getString("email"));
            u.setPassword(res.getString("password"));
            u.setRole(res.getString("role"));
            return u;
        }
        return null;
    }

    @Override
    public void update(User o) throws SQLException {
        String sql="UPDATE \"user\" SET name=?,email=?,role=? WHERE cpf=?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, o.getName());
        pstmt.setString(2, o.getEmail());
        pstmt.setString(3, o.getRole());
        pstmt.setString(4, o.getCpf());
        pstmt.executeUpdate();
    }

    public void updatePassword(String cpf,String password) throws SQLException {
        String sql="UPDATE \"user\" SET password=? WHERE cpf=?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, Hash.hash( password));
        pstmt.setString(2, cpf);
        pstmt.executeUpdate();
    }

    @Override
    public void delete(String o) throws SQLException {
        String sql="DELETE FROM public.user WHERE cpf=?";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, o);
        pstmt.executeUpdate();
    }

    @Override
    public void deleteE(User o) throws SQLException {
        delete(o.getCpf());
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql="SELECT * FROM user";
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();
        List<User> users=new ArrayList<User>();
        while(res.next()){
            User u=new User();
            u.setCpf(res.getString("cpf"));
            u.setName(res.getString("name"));
            u.setEmail(res.getString("email"));
            u.setPassword(res.getString("password"));
            u.setRole(res.getString("role"));
            users.add(u);
        }
        return users;
    }
    
}
