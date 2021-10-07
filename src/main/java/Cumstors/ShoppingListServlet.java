package Cumstors;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Menu.MenuBean;

public class ShoppingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ShoppingListServlet() {
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

		if (request.getParameter("submit") != null)
			gotoSubmitProcess(request, response);
//		else if (request.getParameter("confirm") != null)
//			gotoConfirmProcess(request, response);
	}

	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productname;
		//圖片上傳的部分沒寫到
		String remark;
		String price;
		

		productname = request.getParameter("productname").trim();
		//圖片上傳的部分沒寫到
		remark = request.getParameter("remark").trim();
		price = request.getParameter("price").trim();

		MenuBean menu = new MenuBean(productname, remark, price);
		request.getSession(true).setAttribute("shoppinglist", menu);
		response.getWriter().print(menu.getProductName()+"<br>"+menu.getRemark()+"<br>"+menu.getPrice()+"<br>");
		//request.getRequestDispatcher("./DoubleCheck.jsp").forward(request, response);
	}
	
	

}
