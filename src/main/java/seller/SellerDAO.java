package seller;

import java.sql.*;

public class SellerDAO {
	private Connection conn;
	
	  public SellerDAO(Connection conn) {
			this.conn = conn;
	  }
	  public int insertProduct(SellerBean seller) {
		    try {
		  	  int productno = 1;
			    String sqlString = "SELECT PRODUCT_ID FROM ProductImformation";
		  	  Statement stmt = conn.createStatement();
		  	  //自取號機取得新部門的部門代號
			    ResultSet rs = stmt.executeQuery(sqlString);

		      while (rs.next()) {
		    	  productno = rs.getInt(1);
		    	  }
		      productno++;
		      System.out.println(productno);
		      System.out.println(seller.getName());

		      rs.close();

		    //新增部門到Dept Table
		      sqlString = "INSERT INTO ProductImformation(PRODUCT_ID,PRODUCT_NAME,PRICE,PEOPLE_ENOUGH,END_DATE) " +
			                "VALUES(" + productno + ",'" + seller.getName() + "',"+seller.getPrice()+","+ seller.getPeople()+
			                ",'"+seller.getDate()+"')";
		      stmt.executeUpdate(sqlString);
		  	  stmt.close();
		  	  
			    return productno;
			  } catch (Exception e) {
			    System.err.println("建立部門時發生錯誤:" + e);
				  return -1;
			  }
		  }
	  public boolean updateProduct(SellerBean seller) {
		    try {
		  	  
		    	Statement stmt = conn.createStatement();
		    	
		    	String sqlString ="UPDATE ProductImformation SET PRODUCT_NAME = '"+seller.getName()+"',PRICE = "+seller.getPrice()+
		    			",PEOPLE_ENOUGH = "+seller.getPeople()+",END_DATE = '"+seller.getDate()+"' WHERE PRODUCT_ID ="+seller.getId();
		      
		      stmt.executeUpdate(sqlString);
		  	  stmt.close();
		  	  
			    return true;
			  } catch (Exception e) {
			    System.err.println("建立部門時發生錯誤:" + e);
				  return false;
			  }
		  }
	  public boolean deleteProduct(String productNo) {
		    try {
		  	  
		    	Statement stmt = conn.createStatement();
		    	
		    	String sqlString ="DELETE FROM ProductImformation WHERE PRODUCT_ID="+productNo;
		      
		      stmt.executeUpdate(sqlString);
		  	  stmt.close();
		  	  
			    return true;
			  } catch (Exception e) {
			    System.err.println("建立部門時發生錯誤:" + e);
				  return false;
			  }
		  }

}
