package Restaurant;

import java.sql.*;


public class RestaurantDAO {
	private Connection conn;
	
	public RestaurantDAO(Connection conn) {
		this.conn = conn;
  }
	
	//新增餐廳
	  public boolean insertRestaurant(RestaurantBean restaurantData) {
	    try {
	      String sqlString = "insert into restaurant values('"
		                  	   	+restaurantData.getUserName()+"','"
			                    +restaurantData.getMobile()+"','"
	                            + restaurantData.getRstname()+"','"
	                            + restaurantData.getLicense()+"','"
	                            + restaurantData.getRstaddress()+"','" 
	                            + restaurantData.getRsttel()+"','"
	                            + restaurantData.getType()+"','"
	                            + restaurantData.getBusinesshour()+"')";
	                           
	      Statement stmt = conn.createStatement();
	      System.out.println(sqlString);
		  int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("新增餐廳時發生錯誤:" + e);
			return false;
	    }
	  }
	  
	 
	 //刪除餐廳
	  public boolean deletefromRestaurant (RestaurantBean restaurantData) {
		  
		    try {
		      String sqlString = "delete from restaurant where username=?";		      
		      PreparedStatement stmt = conn.prepareStatement(sqlString);
		      stmt.setString(1, restaurantData.getUserName());
		      System.out.println(sqlString);
			  int updatecount = stmt.executeUpdate(sqlString);
		      stmt.close();
		      
		      if (updatecount >= 1) return true;
		      else                  return false;
			  } catch (Exception e) {
			    System.err.println("刪除餐廳時發生錯誤:" + e);
				return false;
		    }
		  }
	  
	//修改餐廳資訊
	  public boolean updateRestaurant(RestaurantBean restaurantData) {
	    try {
	      String sqlString = "Update restaurant set mobile = ? where username =?";  
	      PreparedStatement stmt = conn.prepareStatement(sqlString);
	      stmt.setString(1, restaurantData.getMobile());
	      System.out.println(sqlString);
		  int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("修改餐廳資訊時發生錯誤:" + e);
			return false;
	    }
	  }
	  
	  
	  
	  
	//查詢餐廳
	  public boolean findRestaurant(RestaurantBean restaurantData) {
	    try {
	      String sqlString = "select * from restaurant where rstname = ?'";	                           
	      PreparedStatement stmt = conn.prepareStatement(sqlString);
	      stmt.setString(1, restaurantData.getRstname());
	      System.out.println(sqlString);
		  int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("查詢餐廳時發生錯誤:" + e);
			return false;
	    }
	  }
	  
	  


	

}
