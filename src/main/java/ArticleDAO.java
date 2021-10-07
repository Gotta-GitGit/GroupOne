// DAO: Database Access Object
// �M�d�PDept Table���s�W,�ק�,�R���P�d��

import java.sql.*;


import mvcdemo.bean.ArticleBean;

public class ArticleDAO {

  private Connection conn;

  //建構子
  public ArticleDAO(Connection conn) {
		this.conn = conn;
  }

 
  //新增資料
  public boolean insertArticle(ArticleBean articleData) {
    try {
      String sqlString = "insert into article values('"
	                  	   	+articleData.getTitle()+"','"
		                    +articleData.getContent()+"','"
		                    +articleData.getCategory()+"','"
                            + articleData.getRestaurant()+"')";
                           
      Statement stmt = conn.createStatement();
      System.out.println(sqlString);
	  int updatecount = stmt.executeUpdate(sqlString);
      stmt.close();
      if (updatecount >= 1) return true;
      else                  return false;
	  } catch (Exception e) {
	    System.err.println("新增文章時發生錯誤:" + e);
		return false;
    }
  }
  //刪除資料
  public boolean deleteArticle(Integer articleData){
	
	  try{String sqlString="delete from article where id=?";
	  PreparedStatement preState = conn.prepareStatement(sqlString);
	  System.out.println(sqlString);
	  preState.setInt(1, articleData);
	  System.out.println(articleData);
	  int count= preState.executeUpdate();
	  preState.close();
	  if(count!=0) return true;
      else                  
    	  return false;
	  
	  }catch (Exception e) {
		    System.err.println("刪除文章時發生錯誤:" + e);
			return false;
	  }
	  }
  //查詢
  public boolean readArticle(String articleData){
		
	  try{String sqlString="select * from article where category=?";
	  PreparedStatement preState = conn.prepareStatement(sqlString);
	  System.out.println(sqlString);
	  preState.setString(1, articleData);
	  System.out.println(articleData);
	 ResultSet rs = preState.executeQuery();
	  preState.close();
	  
     
	  
	  }catch (Exception e) {
		    System.err.println("查詢文章時發生錯誤:" + e);
			return false;
	 }
	return false;
	  }
  //修改
  public boolean updateArticle(ArticleBean edit_article) {
	  try {
	      String sqlString = "update article set title=?,category=?,content=?,restaurant=? where id=?";
	      PreparedStatement preState = conn.prepareStatement(sqlString);
	      Statement stmt = conn.createStatement();
	      System.out.println(edit_article.getTitle());
	      preState.setString(1, edit_article.getTitle());
	      preState.setString(2, edit_article.getCategory());
	      preState.setString(3, edit_article.getContent());
	      preState.setString(4, edit_article.getRestaurant());
	      preState.setInt(5, edit_article.getId());
	      
	      System.out.println(sqlString);
	      System.out.println(edit_article);
		  int updatecount = preState.executeUpdate();
	      stmt.close();
	      preState.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("修改文章時發生錯誤:" + e);
	  
		  }
	 
	return false;
}
  
public boolean selectArticle(String articleData) {
	try{String sqlString="select * from article where id=?";
	  PreparedStatement preState = conn.prepareStatement(sqlString);
	  System.out.println("select * from article where id="+articleData);
	  preState.setString(1, articleData);
	 ResultSet rs = preState.executeQuery();
	  preState.close();
	
	  }catch (Exception e) {
		    System.err.println("查詢文章時發生錯誤:" + e);
			return false;
	 }
	return false;

}
}