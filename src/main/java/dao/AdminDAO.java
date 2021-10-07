package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class AdminDAO {

	public AdminBean checkLogin(String username, String password, String email) throws ClassNotFoundException, SQLException, ServletException {
//		String jdbcURL = "jdbc:sqlserver://localhost;databaseName=FoodDB";
//        String dbUser = "sa";
//        String dbPassword = "password";
//        
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        
        DataSource ds = null;
        InitialContext ctxt = null;
        Connection connection = null;

        
          try 
          {       
        
                
          //建立Context Object,連到JNDI Server
          ctxt = new InitialContext();

          //使用JNDI API找到DataSource
          ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
         
          //向DataSource要Connection
          connection = ds.getConnection();
        }
        catch (NamingException ne)
        {
        	throw new ServletException(ne); //ServletException包裝內部例外事件再丟出去給Server
        }
          
        
        String sql = "SELECT * FROM ADMIN_LOGIN WHERE username = ? and password = ? and email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, email);
 
        ResultSet result = statement.executeQuery();
 
        AdminBean admin = null;
 
        if (result.next()) {
            admin = new AdminBean();
            admin.setFullname(result.getString("full_name"));
            admin.setEmail(email);
        }
 
        connection.close();
 
        return admin;
	}

}
