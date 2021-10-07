package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class DBConnection {
	public static Connection createConnection() throws ServletException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection connection = null;

		
			try {

				// 建立Context Object,連到JNDI Server
				ctxt = new InitialContext();

				// 使用JNDI API找到DataSource
				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");

				// 向DataSource要Connection
				connection = ds.getConnection();
				boolean status = !connection.isClosed();
			} catch (NamingException ne) {
				throw new ServletException(ne); // ServletException包裝內部例外事件再丟出去給Server
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		

	}
}