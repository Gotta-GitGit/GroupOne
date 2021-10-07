package dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/Userlogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public UserLoginServlet() {
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

		UserDAO userDao = new UserDAO();

		try {
			UserBean user = userDao.checkLogin(username, password, email);
			String destPage = "userLogin.jsp";

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user); // UserBean型態物件user存在session物件上，提供所有頁面做身分驗證
				destPage = "userDash.jsp";
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
