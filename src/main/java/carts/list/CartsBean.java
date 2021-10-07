package carts.list;

import java.io.Serializable;
import java.util.Date;

public class CartsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int itemNo;
	private String username;
	private int amonunt;
	private Date payday;
	private float points;
	private String wrong;
	private String type;
	
	public CartsBean() {
		// TODO Auto-generated constructor stub
	}
	public CartsBean(String username, String type) {
		this.username = username;
		this.type = type;
	}
	public CartsBean(int itemNo, String username, int amonunt, Date payday, float points, String wrong, String type) {
		this.itemNo = itemNo;
		this.username = username;
		this.amonunt = amonunt;
		this.payday = payday;
		this.points = points;
		this.wrong = wrong;
		this.type = type;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAmonunt() {
		return amonunt;
	}
	public void setAmonunt(int amonunt) {
		this.amonunt = amonunt;
	}
	public Date getPayday() {
		return payday;
	}
	public void setPayday(Date payday) {
		this.payday = payday;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	public String getWrong() {
		return wrong;
	}
	public void setWrong(String wrong) {
		this.wrong = wrong;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
