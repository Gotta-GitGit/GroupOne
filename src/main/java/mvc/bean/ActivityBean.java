package mvc.bean;

import java.io.Serializable;
import java.util.Date;

public class ActivityBean implements Serializable{

	  private int id;
	  private String username;
	  private String s_email;
	  private String topic;
	  private String[] style;
	  private String address;
	  private int cost;
	  private String link;
	  private String introduce;
	  private int quota;
	  private String schedule;
	  private String notice;
	  private byte[] activity_photo;
	  private float bonus_point;
	  private java.sql.Timestamp create_date;
	  private java.sql.Timestamp revise_date;

	  
	  public ActivityBean()
	  {
	  }
	  
	  //get all
	  public ActivityBean(int aId,String aUsername, String aS_email, String aTopic, String[] aStyle, String aAddress, int aCost, String aLink, String aIntroduce,
			  int aQuota, String aSchedule, String aNotice,  float aBonus_point, java.sql.Timestamp aCreate_date, java.sql.Timestamp aRevise_date)
	  {
		this.id = aId;
	    this.username = aUsername;
	    this.s_email = aS_email;
	    this.topic = aTopic;
	    this.style = aStyle;
	    this.address =aAddress;
	    this.cost = aCost;
	    this.link = aLink;
	    this.introduce = aIntroduce;
	    this.quota = aQuota;
	    this.schedule = aSchedule;
	    this.notice = aNotice;
	    this.bonus_point = aBonus_point;
	    this.create_date = aCreate_date;
	    this.revise_date = aRevise_date;
	   
	  }
	  //search
	  
	  //insert
	  public ActivityBean(String aUsername, String aS_email, String aTopic, String[] aStyle, String aAddress, int aCost, String aLink, String aIntroduce,
			  int aQuota, String aSchedule, String aNotice, byte[] aActivity_photo, float aBonus_point, java.sql.Timestamp aCreate_date, java.sql.Timestamp aRevise_date)
	  {
	    this.username = aUsername;
	    this.s_email = aS_email;
	    this.topic = aTopic;
	    this.style = aStyle;
	    this.address =aAddress;
	    this.cost = aCost;
	    this.link = aLink;
	    this.introduce = aIntroduce;
	    this.quota = aQuota;
	    this.schedule = aSchedule;
	    this.notice = aNotice;
	    this.activity_photo = aActivity_photo;
	    this.bonus_point = aBonus_point;
	    this.create_date = aCreate_date;
	    this.revise_date = aRevise_date;
	   
	  }
	  
	  //edit
	  public ActivityBean(String aUsername, String aS_email, String aTopic, String[] aStyle, String aAddress, int aCost, String aLink, String aIntroduce,
			  int aQuota, String aSchedule, String aNotice, byte[] aActivity_photo, float aBonus_point, java.sql.Timestamp aRevise_date)
	  {
	    this.username = aUsername;
	    this.s_email = aS_email;
	    this.topic = aTopic;
	    this.style = aStyle;
	    this.address =aAddress;
	    this.cost = aCost;
	    this.link = aLink;
	    this.introduce = aIntroduce;
	    this.quota = aQuota;
	    this.schedule = aSchedule;
	    this.notice = aNotice;
	    this.activity_photo = aActivity_photo;
	    this.bonus_point = aBonus_point;
	    this.revise_date = aRevise_date;
	   
	  }
	  
	  
	 
	  
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String[] getStyle() {
		return style;
	}
	public void setStyle(String[] style) {
		this.style = style;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public byte[] getActivity_photo() {
		return activity_photo;
	}
	public void setActivity_photo(byte[] activity_photo) {
		this.activity_photo = activity_photo;
	}
	
	public float getBonus_point() {
		return bonus_point;
	}

	public void setBonus_point(float bonus_point) {
		this.bonus_point = bonus_point;
	}

	public java.sql.Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(java.sql.Timestamp create_date) {
		this.create_date = create_date;
	}
	public java.sql.Timestamp getRevise_date() {
		return revise_date;
	}
	public void setRevise_date(java.sql.Timestamp revise_date) {
		this.revise_date = revise_date;
	}
	
}
