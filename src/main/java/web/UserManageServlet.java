package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserManageDAO;
import model.User;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/UserAll")
public class UserManageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	private UserManageDAO userDAO;
	
	public void init() {
		userDAO = new UserManageDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		
		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getServletPath(); //example, not applicable for multiple models
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "newuser":
				showNewForm(request, response);
				break;
			case "insertuser":
				insertUser(request, response);
				break;
			case "deleteuser":
				deleteUser(request, response);
				break;
			case "edituser":
				showEditForm(request, response);
				break;
			case "updateuser":
				updateUser(request, response);
				break;
			case "listuser":
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String homeNumber = request.getParameter("homeNumber");
		float bonusPoint = 0; // nullable

		User newUser = new User(username, password, fullname, dob, gender, email, phoneNumber, homeNumber, bonusPoint);
		userDAO.insertUser(newUser);
		response.sendRedirect("./UserAll?action=listuser");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String homeNumber = request.getParameter("homeNumber");
		float bonusPoint = 0; // nullable
		User book = new User(id, username, password, fullname, dob, gender, email, phoneNumber, homeNumber, bonusPoint);
		userDAO.updateUser(book);
		response.sendRedirect("./UserAll?action=listuser");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("./UserAll?action=listuser");
	}

}
