package seller;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/*
 * This servlet simply prints out HTTP method name and query string in it's service method.
 * 
 * 
 * Servlet implementation class AccountServlet
 */
@WebServlet("/Seller")
public class Seller extends HttpServlet 
{
	
	//If somebody types up the URL for this servlet 
	//in the address field of the browser.
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException 
	{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException 
	{
		
		String name = req.getParameter("name1");
		String price = req.getParameter("price1");
		String people = req.getParameter("people1");	

		if(name != null )
		{
			// userid available. retrieve the data and generate the page.
			

			PrintWriter out = res.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>價格 "+price+" 元");				
		

			out.println("</body>");
			out.println("</html>");		
			
		}
		else
		{

			//No userid. Send login.html to the user.
			//observe the use of relative path.
			req.getRequestDispatcher("/seller.html").forward(req, res);
			return;	
		}			
	}
  
}