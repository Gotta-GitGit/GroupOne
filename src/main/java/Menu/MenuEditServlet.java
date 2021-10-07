package Menu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/MenuEditServlet")

public class MenuEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	
	public static String username;
	
	public Vector<String> menuList;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuEditServlet() {
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
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
//		 // 用if/else做登入狀態判斷，檢查session中的user或seller物件是否為null決定
//        if (request.getSession(true).getAttribute("seller") != null) {// 符合seller身分別
//            // 呼叫seller專屬方法或導向seller專屬頁面 
//        } else {
//            response.sendRedirect("./index.jsp"); //身分別不符合，送回網站首頁
//        }
		
		String turn = request.getParameter("turn");
		if (turn == null) {
			turn = "empty";
		}
		System.out.println(turn);

		if (request.getParameter("submit") != null) {
			// getParameter抓name，前端畫面要定義name值
			gotoSubmitProcess(request, response);}
		
		else if (request.getParameter("alt") != null) {
			System.out.println("alter");
			gotoAlterProcess(request, response);
			
		}else if (turn.equals("fn")) {
			System.out.println("change");
			gotoChangeProcess(request, response);
			
		}else if (turn.equals("fn1")) {
				System.out.println("delete");
				gotoDeleteProcess(request, response);
				
		}else {
			System.out.println("fault!");
		}
	}

	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			String productName;
			// 圖片上傳的部分沒寫到
			String remark;
			String price;

			productName = request.getParameter("productname").trim();
			// 圖片上傳的部分沒寫到
			remark = request.getParameter("remark").trim();
			price = request.getParameter("price").trim();

			MenuBean menuBean = new MenuBean(productName, remark, price); // Meanbean丟進去
			MenuDAO mdao = new MenuDAO(conn); // menuDAO可以連線
//			if (find)(update)
//			else(insert)
			boolean mdao1 = mdao.insertMenu(menuBean);			
			
			Vector<MenuBean> findallMenu = mdao.findallMenu();
			request.getSession(true).setAttribute("findallMenu",findallMenu);
			
			// response.printWriter().print(menu.getProductName() + "<br>" +
			// menu.getRemark() + "<br>" + menu.getPrice() + "<br>");
			// 寫到前端
			request.getRequestDispatcher("./MenuList.jsp").forward(request, response);
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("資料庫連線錯誤");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public void gotoDeleteProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			String productname;
			String remark;
			String price;

			productname = request.getParameter("productname").trim();
			remark = request.getParameter("remark").trim();
			price = request.getParameter("price").trim();

			MenuBean menu = new MenuBean(productname, remark, price); // Meanbean丟進去
			MenuDAO mdao = new MenuDAO(conn); // menuDAO可以連線
			boolean mdao2 = mdao.deletefromMenu(menu);
			
//			MenuListBean menu_list = new MenuListBean(menuList);
//			request.getSession(true).setAttribute("menu_list", menu_list);
			
			Vector<MenuBean> findallMenu = mdao.findallMenu();
			request.getSession(true).setAttribute("findallMenu",findallMenu);
			// response.printWriter().print(menu.getProductName() + "<br>" +
			// menu.getRemark() + "<br>" + menu.getPrice() + "<br>");
			// 寫到前端
			request.getRequestDispatcher("./MenuList.jsp").forward(request, response);
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("資料庫連線錯誤");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public void gotoAlterProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			String productname = request.getParameter("productname");
			String remark = request.getParameter("remark");
			String price = request.getParameter("price");
			System.out.println(123);

			MenuBean menu = new MenuBean(productname, remark, price); // Meanbean丟進去
			MenuDAO mdao = new MenuDAO(conn); // menuDAO可以連線
			boolean mdao3 = mdao.alterMenu(menu);
			System.out.println(456);
			
			
//			MenuListBean menu_list = new MenuListBean(menuList);
//			request.getSession(true).setAttribute("menu_list", menu_list);
//			System.out.println(789);
			Vector<MenuBean> findallMenu = mdao.findallMenu();
			request.getSession(true).setAttribute("findallMenu",findallMenu);
			
			// response.printWriter().print(menu.getProductName() + "<br>" +
			// menu.getRemark() + "<br>" + menu.getPrice() + "<br>");
			// 寫到前端
			request.getRequestDispatcher("./MenuList.jsp").forward(request, response);
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("資料庫連線錯誤");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}
	
	public void gotoChangeProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			String productname = request.getParameter("productname");
			String remark = request.getParameter("remark");
			String price = request.getParameter("price");
			System.out.println(123);

			MenuBean menu = new MenuBean(productname, remark, price); // Meanbean丟進去
			MenuDAO mdao = new MenuDAO(conn); // menuDAO可以連線
			boolean mdao3 = mdao.alterMenu(menu);
			System.out.println(456);
			
			
//			MenuListBean menu_list = new MenuListBean(menuList);
//			request.getSession(true).setAttribute("menu_list", menu_list);
			System.out.println(789);
			Vector<MenuBean> findallMenu = mdao.findallMenu();
			request.getSession(true).setAttribute("menu",menu);
			//request.getSession(true).setAttribute("findallMenu",findallMenu);
			// response.printWriter().print(menu.getProductName() + "<br>" +
			// menu.getRemark() + "<br>" + menu.getPrice() + "<br>");
			// 寫到前端
			request.getRequestDispatcher("/MenuEdit.jsp").forward(request, response);
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("資料庫連線錯誤");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}
	
	public void connSQL() {
		System.out.println("連線成功");
		InitialContext ctxt;
		
		try {
			ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");
			Connection conn = ds.getConnection();
			
			MenuDAO menu = new MenuDAO(conn);
			
			Vector<MenuBean> menuList = menu.findallMenu();
			System.out.println(menuList);
			//this.menuList = menuList;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
			
		}
	}
	

