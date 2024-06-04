package br.ufma.ppgee.eds.sistemacontroleestoque.database;

import java.sql.Connection;
import java.sql.DriverManager;

//Conecta a aplicação ao base de dados "estoque" no banco de dados PostgreSQL
public class SingletonConnectionDB {
     private static final String URL = "jdbc:postgresql://localhost:5432/estoque";
     private static final String USER = "postgres"; 
     private static final String PASSWORD = "postgres";
     private static final String DRIVER = "org.postgresql.Driver";
     private static Connection connection ;    

        public static java.sql.Connection getConnection() {
            try {
                if(connection == null  ) {
                    Class.forName(DRIVER);
                  
                        String user=System.getenv("pg_user");
                        String password=System.getenv("pg_password");
                      //  System.out.println("user: "+user);
                      //  System.out.println("password: "+password);
                        if(user!=null && password!=null) {
                            System.out.println("user and password found in environment variables");
                            connection = DriverManager.getConnection(URL, user, password);
                            return connection;
                        }else{
                            System.out.println("user or password not found in environment variables");
                            connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        }
             
                    
                    
                }
               
            } catch (ClassNotFoundException | java.sql.SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
}
