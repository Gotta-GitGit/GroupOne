
import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.PrintWriter;
//import java.io.IOException;
import mvc.bean.*;
import java.io.*;
import java.sql.*;
//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Date;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config); //都是預設
	 }

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		 	request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);

		    // To prevent caching 不讓catch存在
		   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
		   response.setHeader("Pragma","no-cache"); // HTTP 1.0
		   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
		   
		   if (request.getParameter("edit")!=null) {
			   EditActivity(request, response);
		   }
		   
			        String action = request.getServletPath();
			        System.out.println(action);
			        try {
			            switch (action) {


			                case "/delete":
			                	gotoSubmitDelete(request, response);
			                	System.out.println(action);
			                    break;
			                case "/confirmDelete":
			                	deleteActivity(request, response);
			                	System.out.println(action);
			                    break;
			                    
			                case "/edit":
			                	gotoSubmitEdit(request, response);
			                	System.out.println(action);
			                	
			                	break;
			                    
			                default:
			                	searchTopic(request, response);
			                    break;
			            }
			        } catch (SQLException ex) {
			            throw new ServletException(ex);
			        }
			    }
	 	//searchALL
	   private void listActivity(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			    	DataSource ds = null;
				    InitialContext ctxt = null;
				    Connection conn = null;
				    
				    try {
				     
				      //建立Context Object,連到JNDI Server	
				      ctxt = new InitialContext();

				      //使用JNDI API找到DataSource 環靜下DB
//				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
				      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
				      
				      //向DataSource要Connection 建立連線
				      conn = ds.getConnection();

				      //建立Database Access Object,負責Table的Access
				      ActivityDAO activityDAO = new ActivityDAO(conn);
				      //把Activity bean抓回來
//				      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
				      //DAO insertStudent
				      
				        List <ActivityBean> listActivity = activityDAO.selectAllUsers();
				        request.setAttribute("listActivity", listActivity);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("/ActivityRegister.jsp");
				        
				        System.out.println(listActivity.size());
				        
				        dispatcher.forward(request, response);
				    } catch (NamingException ne) {
				      System.out.println("Naming Service Lookup Exception");  
				    } catch (SQLException e) {
				      System.out.println("Database Connection Error");
				      System.out.println(e);
				    } finally {
				      try {
				        if (conn != null) conn.close();
				      } catch (Exception e) {
				        System.out.println("Connection Pool Error la!");
				      }
				    }
			    	
			    }
	   //searchTopic
	   private void searchTopic(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			    	DataSource ds = null;
				    InitialContext ctxt = null;
				    Connection conn = null;
				    
				    String inputTopic;
				    inputTopic= request.getParameter("search").trim();
				    
				    try {
				     
				      //建立Context Object,連到JNDI Server	
				      ctxt = new InitialContext();

				      //使用JNDI API找到DataSource 環靜下DB
//				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
				      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
				      
				      //向DataSource要Connection 建立連線
				      conn = ds.getConnection();

				      //建立Database Access Object,負責Table的Access
				      ActivityDAO activityDAO = new ActivityDAO(conn);
				      //把Activity bean抓回來
//				      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
				      //DAO insertStudent
				      
				      
				        List <ActivityBean> listActivity = null;
				        
				        if(inputTopic == "") {
				        	listActivity = activityDAO.selectAllUsers();
					    }else {
					    	listActivity = activityDAO.selectSearchTopic(inputTopic);
					    }
				        request.setAttribute("listActivity", listActivity);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("/ActivityRegister.jsp");
				        
				        System.out.println(listActivity.size());
				        
				        dispatcher.forward(request, response);
				    } catch (NamingException ne) {
				      System.out.println("Naming Service Lookup Exception");  
				    } catch (SQLException e) {
				      System.out.println("Database Connection Error");
				      System.out.println(e);
				    } finally {
				      try {
				        if (conn != null) conn.close();
				      } catch (Exception e) {
				        System.out.println("Connection Pool Error la!");
				      }
				    }
			    }
	   
	   //gotoSubmitDelete 點擊Delete
	   public void gotoSubmitDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {
		   	DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    int id = Integer.parseInt(request.getParameter("id"));
		    
		    try {
			     
			      //建立Context Object,連到JNDI Server	
			      ctxt = new InitialContext();

			      //使用JNDI API找到DataSource 環靜下DB
//			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
			      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
			      
			      //向DataSource要Connection 建立連線
			      conn = ds.getConnection();

			      //建立Database Access Object,負責Table的Access
			      ActivityDAO activityDAO = new ActivityDAO(conn);
			      //把Activity bean抓回來
//			      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
			      //DAO insertStudent
			      
			      
			        List <ActivityBean> listActivity = activityDAO.selectSearchId(id);
			        
			        request.setAttribute("listActivity", listActivity);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteConfirm.jsp");
			        
			        System.out.println(listActivity.size());
			        
			        dispatcher.forward(request, response);
			    } catch (NamingException ne) {
			      System.out.println("Naming Service Lookup Exception");  
			    } catch (SQLException e) {
			      System.out.println("Database Connection Error");
			      System.out.println(e);
			    } finally {
			      try {
			        if (conn != null) conn.close();
			      } catch (Exception e) {
			        System.out.println("Connection Pool Error la!");
			      }
			    }
		  }
	   //導delete頁面做確認
	   private void deleteActivity(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException {
			    	DataSource ds = null;
				    InitialContext ctxt = null;
				    Connection conn = null;
				    
				    int id = Integer.parseInt(request.getParameter("id"));
				    
				    try {
				     
				      //建立Context Object,連到JNDI Server	
				      ctxt = new InitialContext();

				      //使用JNDI API找到DataSource 環靜下DB
//				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
				      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
				      
				      //向DataSource要Connection 建立連線
				      conn = ds.getConnection();

				      //建立Database Access Object,負責Table的Access
				      ActivityDAO activityDAO = new ActivityDAO(conn);
				      //把Activity bean抓回來
//				      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
				      //DAO insertStudent
				      
				      activityDAO.deleteActivity(id);

				      System.out.println("Get some SQL commands done Delete Activity!");
				      //關閉
				      request.getSession(true).invalidate();
				      //導去delete頁面
				      request.getRequestDispatcher("/ActivityRegister.jsp").forward(request,response);
				      
				    } catch (NamingException ne) {
				      System.out.println("Naming Service Lookup Exception");  
				    } catch (SQLException e) {
				      System.out.println("Database Connection Error");
				      System.out.println(e);
				    } finally {
				      try {
				        if (conn != null) conn.close();
				      } catch (Exception e) {
				        System.out.println("Connection Pool Error la!");
				      }
				    }
			    	
			    }
	 
	 //gotoSubmitEdit
	   public void gotoSubmitEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {
		   	DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    int id = Integer.parseInt(request.getParameter("id"));
		    
		    try {
			     
			      //建立Context Object,連到JNDI Server	
			      ctxt = new InitialContext();

			      //使用JNDI API找到DataSource 環靜下DB
//			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
			      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
			      
			      //向DataSource要Connection 建立連線
			      conn = ds.getConnection();

			      //建立Database Access Object,負責Table的Access
			      ActivityDAO activityDAO = new ActivityDAO(conn);
			      //把Activity bean抓回來
//			      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
			      //DAO insertStudent
			      
			      
			        List <ActivityBean> listActivity = activityDAO.selectSearchId(id);
			        
			        request.setAttribute("listActivity", listActivity);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/Edit.jsp");
			        
			        System.out.println(listActivity.size());
			        
			        dispatcher.forward(request, response);
			    } catch (NamingException ne) {
			      System.out.println("Naming Service Lookup Exception");  
			    } catch (SQLException e) {
			      System.out.println("Database Connection Error");
			      System.out.println(e);
			    } finally {
			      try {
			        if (conn != null) conn.close();
			      } catch (Exception e) {
			        System.out.println("Connection Pool Error la!");
			      }
			    }
		  }
	   //導Edit頁做修改
	   	private void EditActivity(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException {
			    	DataSource ds = null;
				    InitialContext ctxt = null;
				    Connection conn = null;
				    
				    int id = Integer.parseInt(request.getParameter("id"));
				    
				       String username;
					   String s_email;
					   String topic;
					   String address;
					   int cost;
					   String link;
					   String introduce;
					   int quota;
					   String schedule;
					   String notice;
					   byte[] activity_photo;
					   float bonus_point;
					   
					   java.sql.Timestamp revise_date;
					   
					   
					   Timestamp timestampnow = new Timestamp(System.currentTimeMillis());
				   
				    //把報名表資料都抓下來存成string  (.trim=去空白)
					   username= request.getParameter("username").trim();
					   s_email= request.getParameter("s_email").trim();
					   topic= request.getParameter("topic").trim();					   
					   address= request.getParameter("address").trim();
					   cost= Integer.parseInt(request.getParameter("cost").trim());
					   link= request.getParameter("link").trim();
					   introduce= request.getParameter("introduce").trim();
					   quota= Integer.parseInt(request.getParameter("quota").trim());
					   schedule= request.getParameter("schedule").trim();
					   notice= request.getParameter("notice").trim();
//					   activity_photo= request.getParameter("activity_photo").trim();
					   activity_photo = null;
					   bonus_point=Float.parseFloat(request.getParameter("bonus_point").trim());
					   
					   revise_date= timestampnow;
				    
					   
					   ActivityBean activityBean=(ActivityBean)request.getAttribute("activityBean");
					     if(activityBean==null){
					    	activityBean=new ActivityBean();
					     	request.setAttribute("activityBean", activityBean);
					     }
					     activityBean.setStyle(request.getParameterValues("style"));
					    for (int i=0;i<activityBean.getStyle().length;i++) { 
					        System.out.println(i+1+"."+activityBean.getStyle()[i]+"<p>");
					    }
					   
						String[] style  = activityBean.getStyle();
					    
					   System.out.println("test"+style.length);
					   
					   ActivityBean reg_activity =  new ActivityBean(username, s_email, topic, style, address, cost, link, introduce, quota, schedule, notice, activity_photo, bonus_point, revise_date);
					   	//放去session
					    request.getSession(true).setAttribute("reg_activity",reg_activity);
					    //再派遣forward到DisplayActivity
					    request.getRequestDispatcher("/Editconfirm.jsp").forward(request,response);
				    try {
				     
				      //建立Context Object,連到JNDI Server	
				      ctxt = new InitialContext();

				      //使用JNDI API找到DataSource 環靜下DB
//				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
				      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
				      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
				      
				      //向DataSource要Connection 建立連線
				      conn = ds.getConnection();

				      //建立Database Access Object,負責Table的Access
				      ActivityDAO activityDAO = new ActivityDAO(conn);
				      //把Activity bean抓回來
//				      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
				      //DAO insertStudent
				      
				      activityDAO.editActivity(reg_activity,id);

				      System.out.println("Get some SQL commands done Edit Activity!");
				      //關閉
				      
				      //導去
				      request.getRequestDispatcher("/ActivityRegister.jsp").forward(request,response);
				      
				    } catch (NamingException ne) {
				      System.out.println("Naming Service Lookup Exception");  
				    } catch (SQLException e) {
				      System.out.println("Database Connection Error");
				      System.out.println(e);
				    } finally {
				      try {
				        if (conn != null) conn.close();
				      } catch (Exception e) {
				        System.out.println("Connection Pool Error la!");
				      }
				    }
			    	
			    }
	   
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		    
		 
		 	request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);

		  // To prevent caching 不讓catch存在
		   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
		   response.setHeader("Pragma","no-cache"); // HTTP 1.0
		   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server

		  //按下按鈕時!=null 去gotoSubmitProcess
		   if (request.getParameter("submit")!=null)
		     gotoSubmitProcess(request, response);
		   else if (request.getParameter("confirm")!=null)
		     gotoConfirmProcess(request, response);
		}
	 
	 //新增後確認頁
		 public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {
			  
			   String username;
			   String s_email;
			   String topic;
			   String[] style;
			   String address;
			   int cost;
			   String link;
			   String introduce;
			   int quota;
			   String schedule;
			   String notice;
			   byte[] activity_photo;
			   float bonus_point;
			   java.sql.Timestamp create_date;
			   java.sql.Timestamp revise_date;
			   
			   
			   Timestamp timestampnow = new Timestamp(System.currentTimeMillis());
		   
		    //把報名表資料都抓下來存成string  (.trim=去空白)
			   username= request.getParameter("username").trim();
			   s_email= request.getParameter("s_email").trim();
			   topic= request.getParameter("topic").trim();
			   style= request.getParameterValues("style");
			   address= request.getParameter("address").trim();
			   cost= Integer.parseInt(request.getParameter("cost").trim());
			   link= request.getParameter("link").trim();
			   introduce= request.getParameter("introduce").trim();
			   quota= Integer.parseInt(request.getParameter("quota").trim());
			   schedule= request.getParameter("schedule").trim();
			   notice= request.getParameter("notice").trim();
//			   activity_photo= request.getParameter("activity_photo").trim();
			   activity_photo = null;
			   bonus_point=Float.parseFloat(request.getParameter("bonus_point").trim());
			   create_date= timestampnow;
			   revise_date= timestampnow;
			  
		     
		    
		    
		    //做成建構子bean
			ActivityBean reg_activity =  new ActivityBean(username, s_email, topic, style, address, cost, link, introduce, quota, schedule, notice, activity_photo, bonus_point, create_date, revise_date);
		    //放去session 
		    request.getSession(true).setAttribute("reg_activity",reg_activity);
		    //再派遣forward到DisplayActivity
		    request.getRequestDispatcher("/DisplayActivity.jsp").forward(request,response);
		  }
		 //新增完確認後Thanks頁面
		  public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		  {

		    DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    
		    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource 環靜下DB
//		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection 建立連線
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ActivityDAO activityDAO = new ActivityDAO(conn);
		      //把stu bean抓回來
		      ActivityBean activityData = (ActivityBean)request.getSession(true).getAttribute("reg_activity");
		      //DAO insertStudent
		      if (activityDAO.insertActivity(activityData)) //匯回傳insert成功or失敗
		        {
		          System.out.println("Get some SQL commands done!");
		          //關閉
		          request.getSession(true).invalidate();
		          //導去Thanks 
		          request.getRequestDispatcher("/Thanks.jsp").forward(request,response);
		        }
		    } catch (NamingException ne) {
		      System.out.println("Naming Service Lookup Exception");  
		    } catch (SQLException e) {
		      System.out.println("Database Connection Error");
		      System.out.println(e);
		    } finally {
		      try {
		        if (conn != null) conn.close();
		      } catch (Exception e) {
		        System.out.println("Connection Pool Error la!");
		      }
		    }
		           
		  }
		  

}
	 
	
