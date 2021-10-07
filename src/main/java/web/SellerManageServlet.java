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

import dao.SellerManageDAO;
import model.Seller;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/SellerAll")
public class SellerManageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	private SellerManageDAO sellerDAO;
	
	public void init() {
		sellerDAO = new SellerManageDAO();
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
			case "newseller":
				showNewForm(request, response);
				break;
			case "insertseller":
				insertSeller(request, response);
				break;
			case "deleteseller":
				deleteSeller(request, response);
				break;
			case "editseller":
				showEditForm(request, response);
				break;
			case "updateseller":
				updateSeller(request, response);
				break;
			case "listseller":
				listSeller(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listSeller(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Seller> listSeller = sellerDAO.selectAllSellers();
		request.setAttribute("listSeller", listSeller);
		RequestDispatcher dispatcher = request.getRequestDispatcher("seller-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("seller-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Seller existingSeller = sellerDAO.selectSeller(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("seller-form.jsp");
		request.setAttribute("seller", existingSeller);
		dispatcher.forward(request, response);

	}

	private void insertSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String telephoneNumber = request.getParameter("telephoneNumber");
		String extensionNumber = request.getParameter("extensionNumber");
		String companyName = request.getParameter("companyName");
		String companyAddress = request.getParameter("companyAddress");
//	byte[] businessCert = Byte.parseByte(request.getParameter("businessCert")); // TBC, nullable for test
		byte[] businessCert = null;
		boolean verifyStatus = false; // nullable

		Seller newSeller = new Seller(username, password, fullname, dob, gender, email, phoneNumber, telephoneNumber, extensionNumber, companyName, companyAddress, businessCert, verifyStatus);
		sellerDAO.insertSeller(newSeller);
		response.sendRedirect("./SellerAll?action=listseller");
	}

	private void updateSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String telephoneNumber = request.getParameter("telephoneNumber");
		String extensionNumber = request.getParameter("extensionNumber");
		String companyName = request.getParameter("companyName");
		String companyAddress = request.getParameter("companyAddress");
//	byte[] businessCert = Byte.parseByte(request.getParameter("businessCert")); // TBC, nullable for test
		byte[] businessCert = null;
		boolean verifyStatus = false; // nullable

		Seller book = new Seller(id, username, password, fullname, dob, gender, email, phoneNumber, telephoneNumber, extensionNumber, companyName, companyAddress, businessCert, verifyStatus);
		sellerDAO.updateSeller(book);
		response.sendRedirect("./SellerAll?action=listseller");
	}

	private void deleteSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sellerDAO.deleteSeller(id);
		response.sendRedirect("./SellerAll?action=listseller");

	}

}
