
import java.io.IOException;
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

import carts.list.CartsDAO;
import carts.list.ListDAO;

/**
 * Servlet implementation class PayProcessServlet
 */
@WebServlet("/PayProcessServlet")
public class PayProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean deleteAll = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayProcessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF8");
		
		String sss = request.getHeader("Referer");
		System.out.println(sss);

		System.out.println("test pay");
		String username = request.getParameter("username");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));

		int amount = Integer.parseInt(request.getParameter("amount"));
		float point = Float.parseFloat(request.getParameter("point"));
		if (amount == 0 && point == 0) {
			this.deleteAll = true;
			System.out.println("t/f1:" + this.deleteAll);
			connSQL2(itemNo);
		} else {
			System.out.println("t/f2:" + this.deleteAll);

			String[] productName = request.getParameterValues("productName");
			String[] priceS = request.getParameterValues("price");
			int[] price = new int[priceS.length];
			for (int i = 0; i < priceS.length; i++) {
				price[i] = Integer.parseInt(priceS[i]);
			}
			String[] numberS = request.getParameterValues("number");
			int[] number = new int[numberS.length];
			for (int i = 0; i < numberS.length; i++) {
				number[i] = Integer.parseInt(numberS[i]);
			}
			connSQL(username, itemNo, productName, price, number, amount, point);
		}

//		Date d = new Date();
//		System.out.println("t1:"+d);
//		System.out.println("t2:"+d.getTime());
//		java.sql.Date dd = new java.sql.Date(d.getTime());
//		System.out.println("t3:"+dd);

		request.getRequestDispatcher("./Pay.html").forward(request, response);

	}

	public void connSQL(String username, int itemNo, String[] productNameA, int[] priceA, int[] numberA, int amount,
			float point) {
		System.out.println("rrrrrr");
		System.out.println(username);
		System.out.println(itemNo);
		// 付款成功後
		try {
			System.out.println("test sql conn");
			InitialContext ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();

			CartsDAO cart = new CartsDAO(conn);
			cart.updateByUsername(amount, point, username, itemNo);

			ListDAO list = new ListDAO(conn);
			for (int i = 0; i < productNameA.length; i++) {
				String productName = productNameA[i];
				int price = priceA[i];
				int number = numberA[i];
				System.out.println("p:" + price + ",n:" + number);

				list.updateByItemnoAndExist(number, "Y", itemNo, productName);
			}
			list.deteleByItemno(itemNo);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

	}
	public void connSQL2(int itemNo) {
		System.out.println("test sql conn");
		InitialContext ctxt;
		try {
			ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();
			
			CartsDAO cart = new CartsDAO(conn);
			cart.deleteByitemNo(itemNo);
			
			ListDAO list = new ListDAO(conn);
			list.deteleByItemno(itemNo);
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
