package Restaurant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
@WebServlet("/NewARestaurantServlet")
public class NewARestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewARestaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		    request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);

		    // To prevent caching 
		   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
		   response.setHeader("Pragma","no-cache"); // HTTP 1.0
		   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
		   
		   // 用if/else做登入狀態判斷，檢查session中的user或seller物件是否為null決定
//	        if (request.getSession(true).getAttribute("seller") != null) {// 符合seller身分別
//	            // 呼叫seller專屬方法或導向seller專屬頁面 
//	        } else {
//	            response.sendRedirect("./index.jsp"); //身分別不符合，送回網站首頁
//	        }

		    
		   if (request.getParameter("submit")!=null)
		     gotoSubmitProcess(request, response);
		   else if (request.getParameter("confirm")!=null)
		     gotoConfirmProcess(request, response);
		}
		
		 public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {
		    String username;
		    String mobile;
		    String rstname;
		    String license;
		    String rstaddress;
		    String rsttel;
		    String type;
		    String businesshour;
		   
		    username = request.getParameter("name").trim();
		    mobile = request.getParameter("mobile").trim();
		    rstname = request.getParameter("rstname").trim();
		    license = request.getParameter("license").trim();
		    rstaddress = request.getParameter("rstaddress").trim();
		    rsttel = request.getParameter("rsttel").trim();
		    type = request.getParameter("type").trim();
		    businesshour = request.getParameter("businesshour").trim();
		    
		   
		    RestaurantBean open_rst =  new RestaurantBean(username,mobile,rstname,license,rstaddress,rsttel, type, businesshour);
		    request.getSession(true).setAttribute("open_rst",open_rst);
		    //response.getWriter().print(open_rst.getUserName()+"<br>"+open_rst.getMobile()+"<br>"+open_rst.getRstname()+"<br>"
            //        +open_rst.getLicense()+"<br>"+open_rst.getRstaddress()+"<br>"+open_rst.getRsttel());
		    request.getRequestDispatcher("./DoubleCheck.jsp").forward(request,response);
		  }

		  public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {

		    DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    
		    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      RestaurantDAO restaurantDAO = new RestaurantDAO(conn);
		      RestaurantBean restaurantData = (RestaurantBean)request.getSession(true).getAttribute("open_rst");
		      if (restaurantDAO.insertRestaurant(restaurantData))
		        {
		          System.out.println("新增成功！");
		          //request.getSession(true).invalidate();
		          request.getRequestDispatcher("./OpenSuccess.jsp").forward(request,response);
		        }
		    } catch (NamingException ne) {
		      System.out.println("Naming Service Lookup Exception");  
		    } catch (SQLException e) {
		      System.out.println("Database Connection Error"); 
		    } finally {
		      try {
		        if (conn != null) conn.close();
		      } catch (Exception e) {
		        System.out.println("Connection Pool Error!");
		      }
		    }
		           
		  }

	}
