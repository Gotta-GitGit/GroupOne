package seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/SellerServeltDB")
public class SellerServeltDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SellerServeltDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	
		if (request.getSession(true).getAttribute("seller") != null) {// 符合seller身分別
            // 呼叫seller專屬方法或導向seller專屬頁面 
        } else {
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
			SellerDAO sellerDAO = new SellerDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("DELETE") != null) {
				processDelete(request, response, sellerDAO);
			}

			if (request.getParameter("UPDATE") != null) {
				processUpdate(request, response, sellerDAO);
			}
			if (request.getParameter("INSERT") != null) {
				processInsert(request, response, sellerDAO);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response, SellerDAO sellerDAO)
			throws SQLException, IOException {

		//String productno = request.getParameter("productno");
		String productname = request.getParameter("productname").trim();
		String price = request.getParameter("price").trim();
		String people = request.getParameter("people").trim();
		String date= request.getParameter("date").trim();
		SellerBean seller= new SellerBean(null,productname,price,people,date);

//透過DAO元件Access Dept Table
		int sellerno = sellerDAO.insertProduct(seller);
		seller.setId(String.valueOf(sellerno));
		
		if (sellerno == -1)
			showError(response, " can not find this product " + productname);
		else
			try {
				request.getRequestDispatcher("/Seller.html").forward(request,response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, SellerDAO sellerDAO)
			throws SQLException, IOException {
		String productno = request.getParameter("productno").trim();
		String productname = request.getParameter("productname").trim();
		String price = request.getParameter("price").trim();
		String people = request.getParameter("people").trim();
		String date= request.getParameter("date").trim();
		SellerBean seller= new SellerBean(productno,productname,price,people,date);

			boolean update = sellerDAO.updateProduct(seller);
			
			if (update == false) {
				showError(response, " can not find this product " + productname);
			}
			else {
				try {
					request.getRequestDispatcher("/SellerUpdate.jsp").forward(request,response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
	}
	private void processDelete(HttpServletRequest request, HttpServletResponse response, SellerDAO sellerDAO)
			throws SQLException, IOException {
			String productno = request.getParameter("productno").trim();


			boolean delete = sellerDAO.deleteProduct(productno);
			
			if (delete == false) {
				showError(response, " can not find this product ");
			}
			else {
				try {
					request.getRequestDispatcher("/SellerUpdate.jsp").forward(request,response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
	}


	private void showError(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Department Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

}
