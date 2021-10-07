package dao;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Adminlogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public AdminLoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		AdminDAO adminDao = new AdminDAO();

		try {
			AdminBean admin = adminDao.checkLogin(username, password, email);
			String destPage = "adminLogin.jsp";

			if (admin != null) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				destPage = "adminDash.jsp";
			} else {
				String message = "帳號密碼輸入錯誤或不存在此帳號";
				request.setAttribute("message", message);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
	}
}