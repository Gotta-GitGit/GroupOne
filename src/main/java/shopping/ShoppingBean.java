package shopping;

import java.io.Serializable;
import java.util.Vector;

public class ShoppingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String username;
	int itemNo;
	Vector<String> shoppingList;
	int amount;
	int point;
	
	public ShoppingBean() {
		// TODO Auto-generated constructor stub
	}
	public ShoppingBean(String username, int itemNo, Vector<String> shoppingList) {
		// TODO Auto-generated constructor stub
		this.username =username;
		this.itemNo = itemNo;
		this.shoppingList = shoppingList;
	}
	public ShoppingBean(String username, int itemNo, Vector<String> shoppingList, int amount, int point) {
		// TODO Auto-generated constructor stub
		this.username =username;
		this.itemNo = itemNo;
		this.shoppingList = shoppingList;
		this.amount = amount;
		this.point = point;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public Vector<String> getShoppingList() {
		return shoppingList;
	}
	public void setShoppingList(Vector<String> shoppingList) {
		this.shoppingList = shoppingList;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}
