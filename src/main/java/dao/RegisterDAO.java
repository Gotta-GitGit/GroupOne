package dao;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import dao.*;
 
public class RegisterDAO { 
	
	String fullname;
	String email;
	String username;
	String password;
	String dob;
	String gender;
	String phoneNumber;
	
	private Connection conn;
	private PreparedStatement preState;         
	
     public String registerUser(UserBean userBean)
     {
    	 username = userBean.getUsername();
    	 password = userBean.getPassword();
         fullname = userBean.getFullname();
         dob = userBean.getDob();
         gender = userBean.getGender();
         email = userBean.getEmail();
         phoneNumber = userBean.getPhoneNumber();
         String homeNumber = userBean.getHomeNumber();
         float bonusPoint = 0;
         
         conn = null;
         preState = null;         
         try
         {
             conn = DBConnection.createConnection();
             //Insert user details into the table 'USERS'
             String insertUserQuery = "insert into USER_DETAILS(username, password, full_name, dob, gender, email, phone_number, home_number, bonus_point) values (?,?,?,?,?,?,?,?,?)"; 
             //Making use of prepared statements here to insert bunch of data
             preState = conn.prepareStatement(insertUserQuery); 
             preState.setString(1, username);
             preState.setString(2, password);
             preState.setString(3, fullname);
             preState.setString(4, dob); //long型態毫秒時間轉換成SQL Date
             preState.setString(5, gender);
             preState.setString(6, email);
             preState.setString(7, phoneNumber);
             preState.setString(8, homeNumber);
             preState.setFloat(9, bonusPoint);
             
//             preparedStatement.addBatch();
//             preparedStatement.executeBatch();
             
             int i= preState.executeUpdate();
             
             preState.close();
             conn.close(); // 關閉SQL連線
             
             if (i!=0)  //Just to ensure data has been inserted into the database
             return "買家用戶註冊成功！";
             
         }catch(SQLException e) {
            e.printStackTrace();
         } catch (ServletException e) {
			e.printStackTrace();
		}       
         return "買家用戶註冊失敗，請再次確認";  // On failure, send a message from here.
     }
     
     
     
     public String registerSeller(SellerBean sellerBean)
     {
    	 username = sellerBean.getUsername();
    	 password = sellerBean.getPassword();
    	 fullname = sellerBean.getFullname();
    	 dob = sellerBean.getDob();
    	 gender = sellerBean.getGender();
    	 email = sellerBean.getEmail();
    	 phoneNumber = sellerBean.getPhoneNumber();
    	 String telephoneNumber = sellerBean.getTelephoneNumber();
    	 String extensionNumber = sellerBean.getExtensionNumber();
    	 String companyName = sellerBean.getCompanyName();
    	 String companyAddress = sellerBean.getCompanyAddress();
    	 byte[] businessCert = sellerBean.getBusinessCert();
    	 boolean verifyStatus = false;
    	 
    	 conn = null;
    	 preState = null;         
    	 try
    	 {
    		 conn = DBConnection.createConnection();
    		 //Insert user details into the table 'USERS'
    		 String insertSellerQuery = "insert into SELLER_DETAILS(username, password, full_name, dob, gender, "
    		 		+ "email, phone_number, telephone_number, extension_number, company_name, company_address, "
    		 		+ "business_cert, verify_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    		 //Making use of prepared statements here to insert bunch of data
    		 preState = conn.prepareStatement(insertSellerQuery); 
    		 preState.setString(1, username);
    		 preState.setString(2, password);
    		 preState.setString(3, fullname);
    		 preState.setString(4, dob); //long型態毫秒時間轉換成SQL Date
    		 preState.setString(5, gender);
    		 preState.setString(6, email);
    		 preState.setString(7, phoneNumber);
    		 preState.setString(8, telephoneNumber);
    		 preState.setString(9, extensionNumber);
    		 preState.setString(10, companyName);
    		 preState.setString(11, companyAddress);
    		 preState.setBytes(12, businessCert); // 暫時用byte[] 需要對應資料型態
    		 preState.setBoolean(13, verifyStatus);
    		 
    		 int i= preState.executeUpdate();
    		 
    		 preState.close();
    		 conn.close(); // 關閉SQL連線
    		 
    		 if (i!=0)  //Just to ensure data has been inserted into the database
    			 return "賣家用戶註冊成功！"; 
    		 
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 } catch (ServletException e) {
			e.printStackTrace();
		}       
    	 return "賣家用戶註冊失敗，請再次確認";  // On failure, send a message from here.
     }
}