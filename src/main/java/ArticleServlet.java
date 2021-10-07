
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


import mvcdemo.bean.ArticleBean;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.print(request.getParameter("action"));	
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
		String aaa= request.getParameter("action");
		if(aaa==null)
			aaa="xxx";
		if (request.getParameter("view")!=null)
		     gotoView(request, response);
		// TODO Auto-generated method stub
		else if(request.getParameter("confirm")!=null)
			gotoInsert(request, response);
		else if(request.getParameter("new")!=null)
			request.getRequestDispatcher("/newArticle.jsp").forward(request, response);
		else if(request.getParameter("ok")!=null)
			request.getRequestDispatcher("/readArticle.jsp").forward(request,response);
		else if(aaa.equals("delete"))
			gotoDelete(request, response);
		else if(aaa.equals("edit"))
			{
			int x=Integer.parseInt(request.getParameter("id"));
			ArticleBean articleBean = new ArticleBean();
			articleBean.setId(x);
			request.setAttribute("articleBean", articleBean);
			System.out.print(request.getParameter("action"));
			//System.out.println(request.getParameter("edit"));
			request.getRequestDispatcher("/editArticle.jsp").forward(request, response);
			}
			//request.getRequestDispatcher("/Home.jsp").forward(request, response);
		else if(request.getParameter("update")!=null)
			gotoUpdate(request,response);
//		else if(request.getParameter("viewEdit")!=null)
//			request.getRequestDispatcher("/DisplayEdit.jsp").forward(request, response);
		
		
	}


	private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ArticleDAO articleDAO = new ArticleDAO(conn);
		      String articleData =String.valueOf(request.getParameter("edit"));
		      request.getSession(true).setAttribute("edit_article", articleDAO.selectArticle(articleData));
		      System.out.println(articleData);
			if (articleDAO.selectArticle(articleData))
		        {
		          System.out.println("Get some SQL commands done!");
		      //    request.getSession(true).invalidate();
		          request.getRequestDispatcher("/editArticle.jsp").forward(request,response);
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

	private void gotoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    String title=request.getParameter("title");
		String content=request.getParameter("content");
		String category=request.getParameter("category");
		String restaurant=request.getParameter("restaurant");
		Integer id=Integer.parseInt(request.getParameter("id"));
		ArticleBean edit_article = new ArticleBean(title,content,category,restaurant,id);
		request.getSession(true).setAttribute("edit_article", edit_article);
	    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ArticleDAO articleDAO = new ArticleDAO(conn);
		      String articleData =String.valueOf(request.getParameter("edit"));
		      System.out.println(articleData);  
		      if (articleDAO.updateArticle(edit_article))
		        {
		          System.out.println("Get some SQL commands done!");
		          //request.getSession(true).invalidate();
		          request.getRequestDispatcher("/Home.jsp").forward(request, response);
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
	

	private void gotoDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ArticleDAO articleDAO = new ArticleDAO(conn);
		      Integer articleData =Integer.valueOf(request.getParameter("id"));
			if (articleDAO.deleteArticle(articleData))
		        {
		          System.out.println("Get some SQL commands done!");
		        //  request.getSession(true).invalidate();
		          request.getRequestDispatcher("/successDelete.jsp").forward(request,response);
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
	

	private void gotoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String category=request.getParameter("category");
		String restaurant=request.getParameter("restaurant");
		ArticleBean new_article = new ArticleBean(title,content,category,restaurant);
		request.getSession(true).setAttribute("new_article", new_article);
		request.getRequestDispatcher("/DisplayArticle.jsp").forward(request, response);
	}

	private void gotoInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ArticleDAO articleDAO = new ArticleDAO(conn);
		      ArticleBean articleData = (ArticleBean)request.getSession(true).getAttribute("new_article");
		      if (articleDAO.insertArticle(articleData))
		        {
		          System.out.println("Get some SQL commands done!");
		        //  request.getSession(true).invalidate();
		          request.getRequestDispatcher("/successNew.jsp").forward(request,response);
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


