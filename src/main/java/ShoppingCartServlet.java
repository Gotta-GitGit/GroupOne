
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
import customer.CartBean;
import dao.UserBean;
import shopping.NowCartBean;
import shopping.ShoppingBean;
//import usertest.UserBeen;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String username;
	public static String identity;
	public int itemNo;
	public Vector<String> shoppingCarts;

	public Vector<String> nowCart;

	public String type;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF8");
		// 測試傳送來源
		String sss = request.getHeader("Referer");
		System.out.println(sss);
		String ssss = request.getLocalName();
		System.out.println(ssss);

		// String testu = (String) request.getSession().getAttribute("test_login");

		if (request.getSession(true).getAttribute("user") != null) {// 符合user身分別
			// 呼叫user專屬方法或導向user專屬頁面
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String username = user.getUsername();
			ShoppingCartServlet.username = username;
			System.out.println("t1");

		} else if (request.getSession(true).getAttribute("seller") != null) {// 符合seller身分別
			// 呼叫seller專屬方法或導向seller專屬頁面
			System.out.println("t2");
			response.sendRedirect("./index.jsp");
		} else {
			System.out.println("t3");
			response.sendRedirect("./index.jsp"); // 身分別不符合，送回網站首頁
		}
		System.out.println("t4:" + this.username);
		String productName = null;
		int price = 0;
		int number = 0;
		
		if (request.getSession(true).getAttribute("cart") != null) {

			CartBean buyAny = (CartBean) request.getSession().getAttribute("cart");
			productName = buyAny.getName();
			price = Integer.parseInt(buyAny.getPrice());
			number = Integer.parseInt(buyAny.getNumber());
			String type = buyAny.getCategory();
			if (type == "group") {
				type = "coupon";
			}
			this.type = type;
			System.out.println("======");
			System.out.println(productName);
			System.out.println(price);
			System.out.println(number);
			System.out.println(type);
			System.out.println("======");
			request.getSession().removeAttribute("cart");
		}

		/*
		 * String username = buyAny.get; String identity = buyAny.get; this.username =
		 * username; this.identity = identity;
		 */

//		System.out.println("testu:" + testu);
//		ShoppingCartServlet.username = testu;
		/*
		 * UserBeen u = new UserBeen(); String uu = u.getUsername();
		 * System.out.println("uu:" + uu);
		 */
		// String type = request.getParameter("type");
		this.type = type;

		if (ShoppingCartServlet.username != null && ShoppingCartServlet.username != "") {
			if ((request.getParameter("cart")) != null) {
				System.out.println(request.getParameter("cart"));
				System.out.println("twotest");
				connSQL2(ShoppingCartServlet.username);

				Vector<String> nowCart = this.nowCart;

				NowCartBean now_cart = new NowCartBean(username, nowCart);
				request.getSession(true).setAttribute("now_cart", now_cart);
				request.getRequestDispatcher("./TwoCart.jsp").forward(request, response);
			} else {

				// 取得帳號 --> 購物
				System.out.println("this.type :" + this.type);
				/*
				 * String productName = request.getParameter("productName");
				 * System.out.println("product:"+ productName);
				 */
				/*
				 * 券+餐廳 (CartBean) buyAny = (CartBean)
				 * request.getSession().getAttribute("CartBean"); String poductName =
				 * coupon.getName(); int price = coupon.getPrice int number = conpon.getNumber
				 * String type = conpon.getCartgory if(type.equals("group")){ type = "coupon"
				 * }else if(type.equals("food"){ type = "food" }
				 * request.getSession().removeAttribute("CartBean");
				 */

				System.out.println("ttt23");
				if (productName != null && productName != "") {

					// int number = Integer.parseInt(request.getParameter("number"));
					System.out.println("int1" + number);
					// int price = Integer.parseInt(request.getParameter("price"));
					System.out.println("p-s:" + username);
					connSQL(productName, price, number, type);
				} else {
					int itemNo = Integer.parseInt(request.getParameter("itemNo"));
					System.out.println("TNo:" + itemNo);
					this.itemNo = itemNo;
					connSQL3(itemNo);
				}
				System.out.println("[] : " + shoppingCarts);
				System.out.println("[]len: " + shoppingCarts.size());
				for (int i = 0; i < (shoppingCarts.size()) / 3; i++) {
					System.out.println(shoppingCarts.get(0 + 3 * i));
					System.out.println(shoppingCarts.get(1 + 3 * i));
					System.out.println(shoppingCarts.get(2 + 3 * i));
				}

				String username = ShoppingCartServlet.username;
				System.out.println("t158" + username);
				int itemNo = this.itemNo;
				System.out.println("thit:" + itemNo);
				Vector<String> shop = shoppingCarts;

				ShoppingBean shop_bean = new ShoppingBean(username, itemNo, shop);
				request.getSession(true).setAttribute("shop_bean", shop_bean);
				request.getRequestDispatcher("./CartList.jsp").forward(request, response);
//			}
			}

		} else {
			System.out.println("t5");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

	}

	public void connSQL(String productName, int price, int number, String type) {
		System.out.println("tosul:" + username);
		try {
			// 連線SQL
			System.out.println("test sql conn");
			InitialContext ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
//				System.out.println("test 1");
			Connection conn = ds.getConnection();
//				System.out.println("test 2");
			System.out.println(ShoppingCartServlet.username);
			// Cart : select / insert
			CartsDAO cart = new CartsDAO(conn);
			int cartNo = cart.findByUsername(ShoppingCartServlet.username, type);
			this.itemNo = cartNo;
			System.out.println(cartNo);
			if (cartNo == 0) {
				cartNo = cart.findByUsername(ShoppingCartServlet.username, type);
				this.itemNo = cartNo;
			}

			// List : insert / select
			ListDAO list = new ListDAO(conn);
			boolean exist = list.findByItemnoAndProductname(cartNo, productName);
			if (exist) {
				System.out.println("start update");
				list.updateByItemno(number, cartNo, productName);
			} else {
				System.out.println("start insert");
				list.insertList(cartNo, productName, number, price);
			}
			Vector<String> shoppingCarts = list.findAllByItemno(cartNo);
			this.shoppingCarts = shoppingCarts;

			int listAmount = list.findAmountByItemno(cartNo);
			System.out.println("總金額 :" + listAmount);

			System.out.println("test sql success");

		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			System.out.println("sql conn fail");
		}
	}

	public void connSQL2(String username) {

		System.out.println("test sql conn");
		InitialContext ctxt;
		try {
			ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();

			CartsDAO cart = new CartsDAO(conn);

			Vector<String> nowCart = cart.findTwoCart(ShoppingCartServlet.username);
			System.out.println(nowCart);
			this.nowCart = nowCart;

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void connSQL3(int itemNo) {

		System.out.println("test sql conn");
		InitialContext ctxt;
		try {
			ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();

			ListDAO list = new ListDAO(conn);

			Vector<String> shoppingCarts = list.findAllByItemno(itemNo);
			this.shoppingCarts = shoppingCarts;

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

}
