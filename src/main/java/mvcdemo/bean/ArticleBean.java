package mvcdemo.bean;

import java.io.Serializable;

public class ArticleBean implements Serializable
{
	private Integer id;
  private String title;
  private String content;
  private String category;
  private String restaurant;
  
  public ArticleBean()
  {
  }
  public ArticleBean(String pTitle, String pContent, String pCategory,String pRestaurant)
  {
	
    this.title = pTitle;
    this.content=pContent;
    this.category=pCategory;
    this.restaurant=pRestaurant;
   
  }
  public ArticleBean(String pTitle, String pContent, String pCategory,String pRestaurant,Integer id)
  {
	
    this.title = pTitle;
    this.content=pContent;
    this.category=pCategory;
    this.restaurant=pRestaurant;
    this.id=id;
  }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String pTitle) {
	this.title = pTitle;
}

public String getContent() {
	return content;
}

public void setContent(String pContent) {
	this.content = pContent;
}

public String getCategory() {
	return category;
}

public void setCategory(String pCategory) {
	this.category = pCategory;
}

public String getRestaurant() {
	return restaurant;
}

public void setRestaurant(String pRestaurant) {
	this.restaurant = pRestaurant;
}
  
  
}