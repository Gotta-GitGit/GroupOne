
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import mvc.bean.ActivityBean;

public class ActivityDAO {
	
	private Connection conn;
	
	

	  //建構子
	  public ActivityDAO(Connection conn) {
			this.conn = conn;
	  }
	  
	  //新增活動資料
	  public boolean insertActivity(ActivityBean activityData) {
		  System.out.println("呼叫方法新增資料");
			
	    try {
	    	   String [] style = activityData.getStyle();
	    	   StringBuffer sb = new StringBuffer();
			   for(int i = 0; i < style.length; i++) {
			       if(i!=style.length-1) {
			    	   sb.append(style[i]+",");
			       }else {
			    	   sb.append(style[i]);
			       }
			   }
			   String getAllStyle = sb.toString();
			   System.out.println(getAllStyle);
	    	
	    	
		  String sqlString = "insert into Activity(username, s_email, topic, style, address, cost, link, introduce, quota, schedule, notice,"
		  		+ " bonus_point, create_date, revise_date)"
				+ "values ( '" + activityData.getUsername()+ "','" + activityData.getS_email()+"','" + activityData.getTopic()+"','" 
		  		+getAllStyle+"','" +activityData.getAddress()+"',"+activityData.getCost()+",'"+activityData.getLink()+"','"
		  		+activityData.getIntroduce()+"',"+activityData.getQuota()+",'"+activityData.getSchedule()+"','"+activityData.getNotice()+"',"
		  		+activityData.getBonus_point()+",'"+activityData.getCreate_date()+"','"+activityData.getRevise_date() +"')";

//		  
//	      String sqlString = "insert into Activity values('"
////		                  	   	+activityData.getId()+"','"
//			                    +activityData.getUsername()+"','"
//	                            +activityData.getS_email()+"','"
//	                            +activityData.getTopic()+"','"
//	                            +activityData.getStyle()+"','"
//	                            +activityData.getAddress()+"','"
//	                            +activityData.getCost()+"','"
//	                            +activityData.getLink()+"','"
//	                            +activityData.getIntroduce()+"','"
//	                            +activityData.getQuota()+"','"
//	                            +activityData.getSchedule()+"','"
//	                            +activityData.getNotice()+"','"
//	                            +activityData.getBonus_point()+"','"
//	                            +activityData.getCreate_date()+"','"
//	                            +activityData.getRevise_date()+"','"
//	                            +activityData.getActivity_photo()+"')";
//          +activityData.getActivity_photo()+"','"
		  
	      Statement stmt = conn.createStatement();
	      System.out.println(sqlString); //印出SQL指令
	      //看insert幾筆
		  int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;  
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("新增活動資料時發生錯誤拉:" + e);
			return false;
	    }
	  }
	
	//顯示所有活動資料
	  public List < ActivityBean > selectAllUsers() {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < ActivityBean > activity = new ArrayList < > ();
	        // Step 1: Establishing a Connection.
	        try  {
	        	String sqlString = "Select * from Activity";
	        	Statement stmt = conn.createStatement();
	  	      	System.out.println(sqlString); //印出SQL指令
	  	      	
	  	        ResultSet rs = stmt.executeQuery(sqlString);
	        	

	            // Step 2: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("username");
	                String email = rs.getString("s_email");
	                String topic = rs.getString("topic");
	                String[] style = rs.getString("style").split(",");
	                String address = rs.getString("address");
	                int cost = rs.getInt("cost");
	                String link = rs.getString("link");
	                String introduce = rs.getString("introduce");
	                int quota = rs.getInt("quota");
	                String schedule = rs.getString("schedule");
	                String notice = rs.getString("notice");
	                float bonus_point = rs.getFloat("bonus_point");
	                Timestamp create_date = rs.getTimestamp("create_date");
	                Timestamp revise_date = rs.getTimestamp("revise_date");
	                
	                
	                activity.add(new ActivityBean(id, name, email, topic,style,address,cost,link,introduce,quota,schedule,notice,bonus_point,create_date,revise_date));
	            }
	        } catch (Exception e) {
			    System.err.println("查詢全部活動資料時發生錯誤:" + e);
		    }
	        return activity;
	    }
	  
	//查詢活動資料
	  public List < ActivityBean > selectSearchTopic(String inputTopic) {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < ActivityBean > activity = new ArrayList < > ();
	        // Step 1: Establishing a Connection.
	        try  {
	        	String sqlString = "Select * from Activity Where topic LIKE '%"+ inputTopic +"%'ORDER BY ID";
	        	Statement stmt = conn.createStatement();
	  	      	System.out.println(sqlString); //印出SQL指令
	  	      	
	  	        ResultSet rs = stmt.executeQuery(sqlString);
	        	

	            // Step 2: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("username");
	                String email = rs.getString("s_email");
	                String topic = rs.getString("topic");
	                String[] style = rs.getString("style").split(",");
	                String address = rs.getString("address");
	                int cost = rs.getInt("cost");
	                String link = rs.getString("link");
	                String introduce = rs.getString("introduce");
	                int quota = rs.getInt("quota");
	                String schedule = rs.getString("schedule");
	                String notice = rs.getString("notice");
	                Float bonus_point = rs.getFloat("bonus_point");
	                Timestamp create_date = rs.getTimestamp("create_date");
	                Timestamp revise_date = rs.getTimestamp("revise_date");
	                
	                
	                activity.add(new ActivityBean(id, name, email, topic,style,address,cost,link,introduce,quota,schedule,notice,bonus_point,create_date,revise_date));
	            }
	        } catch (Exception e) {
			    System.err.println("查詢活動資料時發生錯誤:" + e);
		    }
	        return activity;
	    }
	  
	  //刪除活動資料
	  public List < ActivityBean > selectSearchId(int searchId) {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < ActivityBean > activity = new ArrayList < > ();
	        // Step 1: Establishing a Connection.
	        try  {
	        	String sqlString = "Select * from Activity Where id = "+ searchId +" ";
	        	Statement stmt = conn.createStatement();
	  	      	System.out.println(sqlString); //印出SQL指令
	  	      	
	  	        ResultSet rs = stmt.executeQuery(sqlString);
	        	

	            // Step 2: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("username");
	                String email = rs.getString("s_email");
	                String topic = rs.getString("topic");
	                String[] style = rs.getString("style").split(",");
	                String address = rs.getString("address");
	                int cost = rs.getInt("cost");
	                String link = rs.getString("link");
	                String introduce = rs.getString("introduce");
	                int quota = rs.getInt("quota");
	                String schedule = rs.getString("schedule");
	                String notice = rs.getString("notice");
	                Float bonus_point = rs.getFloat("bonus_point");
	                Timestamp create_date = rs.getTimestamp("create_date");
	                Timestamp revise_date = rs.getTimestamp("revise_date");
	                
	                
	                activity.add(new ActivityBean(id, name, email, topic,style,address,cost,link,introduce,quota,schedule,notice,bonus_point,create_date,revise_date));
	            }
	        } catch (Exception e) {
			    System.err.println("查詢活動資料時發生錯誤:" + e);
		    }
	        return activity;
	    }
	//修改活動資料
	// public List < ActivityBean > update(int searchId)
	  public void editActivity(ActivityBean editActivity,int id) {
		  System.out.println("呼叫方法新增資料");
			
		  String [] style = editActivity.getStyle();
   	   	  StringBuffer sb = new StringBuffer();
		   for(int i = 0; i < style.length; i++) {
		       if(i!=style.length-1) {
		    	   sb.append(style[i]+",");
		       }else {
		    	   sb.append(style[i]);
		       }
		   }
		   String getAllStyle = sb.toString();
		   System.out.println(getAllStyle);
		   
		  
		   String username = editActivity.getUsername();
		   String s_email = editActivity.getS_email();
		   String topic = editActivity.getTopic();
		   String Allstyle = getAllStyle;
		   String address = editActivity.getAddress();
		   int cost = editActivity.getCost();
		   String link = editActivity.getLink();
		   String introduce = editActivity.getIntroduce();
		   int quota = editActivity.getQuota();
		   String schedule = editActivity.getSchedule();
		   String notice = editActivity.getNotice();
		   byte[] activity_photo = editActivity.getActivity_photo();
		   float bonus_point = editActivity.getBonus_point();
		   
		   java.sql.Timestamp revise_date = editActivity.getRevise_date();
		  
	    try {
	    	
	    	String sqlString = "Update Activity Set username = "
					+ "'"+ username + "',s_email = '" + s_email + "',topic = '"+ topic + "',style = '" + Allstyle+ "',address = '" + address+"',cost = "+ cost +",link = '" + link 
					+"',introduce = '" + introduce +"',quota = "+ quota +",schedule = '" + schedule +"',notice = '" + notice +"',bonus_point = " + bonus_point +",revise_date = '" + revise_date +"' where id = '"+id +"'";
	    	

	      Statement stmt = conn.createStatement();
	      System.out.println(sqlString); //印出SQL指令
	      //看insert幾筆
		  stmt.executeUpdate(sqlString);
	      stmt.close();
	    }catch (Exception e) {
		    System.err.println("查詢活動資料時發生錯誤:" + e);
	    }
	  }
	  

	  public void deleteActivity(int confirmId) {
		  System.out.println("呼叫方法新增資料");
			
	    try {
	    	String sqlString = "Delete FROM Activity Where id = " + confirmId;

	      Statement stmt = conn.createStatement();
	      System.out.println(sqlString); //印出SQL指令
	      //看insert幾筆
		  stmt.executeUpdate(sqlString);
	      stmt.close();
	    }catch (Exception e) {
		    System.err.println("查詢活動資料時發生錯誤:" + e);
	    }
	  }
	  
}
