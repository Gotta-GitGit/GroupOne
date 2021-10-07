package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mvc.bean.RegisterBean;
//import com.mvc.dao.RegisterDao;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public RegisterServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
		// Copying all the input parameters in to local variables
		// 根據action的參數值判斷為User註冊或Seller註冊，insertUser或insertSeller
		String action = request.getParameter("action");

		try {

			// User跟Seller共同sql資料行
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");

			// 用SimpleDateFormat()和parse()轉換String為Date oe Date.valueof();
//			Date dob = Date.valueOf(request.getParameter("dob"));;//converting string into sql date 
			String dob = request.getParameter("dob");

			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");

			// User和Seller共用RegisterDAO，但呼叫各自的方法，registerUser()&registerSeller()
			RegisterDAO registerDao = new RegisterDAO();
			String registerStatus = null; // 存放registerDao註冊新用戶是否成功

			// 根據action的參數值判斷為User註冊或Seller註冊
			// 判定為User，action=insertUser
			if (action.equals("insertUser")) {
				// User specific sql columns
				String homeNumber = request.getParameter("homeNumber");
				float bonusPoint = 0; // nullable

				UserBean userBean = new UserBean();
				// Using Java Beans - An easiest way to play with group of related data
				userBean.setUsername(username);
				userBean.setPassword(password);
				userBean.setFullname(fullname);
				userBean.setDob(dob);
				userBean.setGender(gender);
				userBean.setEmail(email);
				userBean.setPhoneNumber(phoneNumber);
				userBean.setHomeNumber(homeNumber);
				userBean.setBonusPoint(bonusPoint);

				// The core Logic of the Registration application is present here. We are going
				// to insert user data in to the database.
				registerStatus = registerDao.registerUser(userBean);
			}

			// 判定為Seller，action=insertSeller
			else if (action.equals("insertSeller")) {
				// Seller specific sql columns
				String telephoneNumber = request.getParameter("telephoneNumber");
				String extensionNumber = request.getParameter("extensionNumber");
				String companyName = request.getParameter("companyName");
				String companyAddress = request.getParameter("companyAddress");
//        	byte[] businessCert = Byte.parseByte(request.getParameter("businessCert")); // TBC, nullable for test
				byte[] businessCert = null;
				boolean verifyStatus = false; // nullable

				SellerBean sellerBean = new SellerBean();
				// Using Java Beans - An easiest way to play with group of related data
				sellerBean.setUsername(username);
				sellerBean.setPassword(password);
				sellerBean.setFullname(fullname);
				sellerBean.setDob(dob);
				sellerBean.setGender(gender);
				sellerBean.setEmail(email);
				sellerBean.setPhoneNumber(telephoneNumber);
				sellerBean.setTelephoneNumber(telephoneNumber);
				sellerBean.setExtensionNumber(extensionNumber);
				sellerBean.setCompanyName(companyName);
				sellerBean.setCompanyAddress(companyAddress);
				sellerBean.setBusinessCert(businessCert);
				sellerBean.setVerifyStatus(verifyStatus);

				// The core Logic of the Registration application is present here. We are going
				// to insert seller data in to the database.
				registerStatus = registerDao.registerSeller(sellerBean);
			}

			// 判斷身分及註冊是否成功，決定導向哪個頁面
			if (registerStatus.equals("買家用戶註冊成功！")) // On success, you can display a message to user on Home page
			{
				request.getRequestDispatcher("/userLogin.jsp").forward(request, response);
			} else if (registerStatus.equals("賣家用戶註冊成功！")) {
				request.getRequestDispatcher("/sellerLogin.jsp").forward(request, response);

				// On Failure, display a meaningful message to the User.
			} else if (registerStatus.equals("買家用戶註冊失敗，請再次確認")) {
				request.setAttribute("errMessage", registerStatus);
				request.getRequestDispatcher("/UserRegisterFrom.jsp").forward(request, response);

				// On Failure, display a meaningful message to the User.
			} else if (registerStatus.equals("賣家用戶註冊失敗，請再次確認")) {
				request.setAttribute("errMessage", registerStatus);
				request.getRequestDispatcher("/SellerRegisterForm.jsp").forward(request, response);

			}

		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			if (action.equals("insertUser")) {
				request.setAttribute("errMessage", "買家資料輸入錯誤，請再次確認");
				request.getRequestDispatcher("/UserRegisterForm.jsp").forward(request, response);

			} else if (action.equals("insertSeller")) {
				request.setAttribute("errMessage", "賣家資料輸入錯誤，請再次確認");
				request.getRequestDispatcher("/SellerRegisterForm.jsp").forward(request, response);

			}

		}
	}

}