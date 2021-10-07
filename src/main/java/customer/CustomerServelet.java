package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/CustomerServelet")
public class CustomerServelet extends HttpServlet {
	
	private Connection conn;
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	
		if (request.getSession(true).getAttribute("user") != null) {// 符合user身分別
			
        }else {
            response.sendRedirect("./index.jsp"); //身分別不符合，送回網站首頁
        }
*/
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("Cart") != null) {
				String id;
				String number;
				String category = "group";
				
				id = request.getParameter("id").trim();
				number = request.getParameter("number").trim();
				CartBean cart = new CartBean(id, null, null, number, category);
				cart = Query(cart,id);
				
				System.out.println(cart.getId());
				System.out.println(cart.getName());
				System.out.println(cart.getPrice());
				System.out.println(cart.getNumber());
				System.out.println(cart.getCategory());
				
				request.getSession(true).setAttribute("cart",cart);
			    request.getRequestDispatcher("/ShoppingCartServlet").forward(request,response);
			}

		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public CartBean Query(CartBean cart,String id) {
		
		DataSource ds = null;
		InitialContext ctxt = null;
		conn = null;
		
		try {
			ctxt = new InitialContext();

			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");

			conn = ds.getConnection();
			
			cart = QueryProcess(cart,id);
			
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		return cart;
		
	}
	public CartBean QueryProcess(CartBean cart,String id) {
		
		try {
			  String sqlString = "SELECT * FROM ProductImformation where PRODUCT_ID ="+id;
		  	  Statement stmt = conn.createStatement();
		  	  //自取號機取得新部門的部門代號
			    ResultSet rs = stmt.executeQuery(sqlString);

		      if (rs.next()) {
		    	  cart.setName(rs.getString("PRODUCT_NAME"));
		    	  cart.setPrice(rs.getString("PRICE"));
		    	  }

		      rs.close();
		      return cart;
		    //新增部門到Dept Table
			  } catch (Exception e) {
			    System.err.println("建立部門時發生錯誤:" + e);
				  return null;
			  }
		
		
		
		
	}
}
