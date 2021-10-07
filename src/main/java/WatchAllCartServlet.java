
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

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
import dao.UserBean;
import shopping.AllCartsBean;

/**
 * Servlet implementation class WatchAllCartServlet
 */
@WebServlet("/WatchAllCartServlet")
public class WatchAllCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String username;
	public static String identity;
	public static Vector<String> vCart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WatchAllCartServlet() {
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
		System.out.println("wwwwww");

		if (request.getSession(true).getAttribute("user") != null) {// 符合user身分別
            // 呼叫user專屬方法或導向user專屬頁面		
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String username = user.getUsername();
			WatchAllCartServlet.username = username;
			System.out.println("tt:"+this.username);
	
        } else if (request.getSession(true).getAttribute("seller") != null) {// 符合seller身分別
            // 呼叫seller專屬方法或導向seller專屬頁面 
        	response.sendRedirect("./index.jsp");
        } else {
            response.sendRedirect("./index.jsp"); //身分別不符合，送回網站首頁
        }
		
//		String testu = (String) request.getSession().getAttribute("test_login");
//		System.out.println("testu:" + testu);
//		WatchAllCartServlet.username = testu;

		String cart = request.getParameter("cart");
		String select = request.getParameter("select");
		String itemNo = request.getParameter("itemNo");

		if (WatchAllCartServlet.username != null && WatchAllCartServlet.username != "") {

			if (cart != null) {
				System.out.println("testCCC");
				request.getRequestDispatcher("./ShoppingCartServlet").forward(request, response);
			} else if (select != null) {
				System.out.println("testSSS");
				connSQL(WatchAllCartServlet.username);
				Vector<String> vCart = WatchAllCartServlet.vCart;
				System.out.println("v:" + vCart);
				AllCartsBean all_carts = new AllCartsBean(vCart);
				request.getSession(true).setAttribute("all_carts", all_carts);
				request.getRequestDispatcher("./AllCarts.jsp").forward(request, response);
			} else if(itemNo != null) {
				System.out.println("testIII");
				
				connSQL2(Integer.parseInt(itemNo));
				Vector<String> vList = WatchAllCartServlet.vCart;
				AllCartsBean all_lists = new AllCartsBean(vList);
				request.getSession(true).setAttribute("all_lists", all_lists);
				request.getRequestDispatcher("./ListItem.jsp").forward(request, response);
				
				
			} else {
				System.out.println("fail");
			}
		} else {
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

	}

	public void connSQL(String username) {
		try {
			InitialContext ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();

			CartsDAO cart = new CartsDAO(conn);
			Vector<String> vCart = cart.findAllCartByUsername(username);
			WatchAllCartServlet.vCart = vCart;

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void connSQL2(int itemNo) {
		try {
			InitialContext ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();
			
			ListDAO list = new ListDAO(conn);
			Vector<String> vList = list.findAllByItemno(itemNo);
			WatchAllCartServlet.vCart = vList;
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
