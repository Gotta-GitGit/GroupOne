package shopping;

import java.io.Serializable;
import java.util.Vector;

public class NowCartBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Vector<String> nowCart;

	public NowCartBean() {
		// TODO Auto-generated constructor stub
	}
	
	public NowCartBean(String username, Vector<String> nowCart) {
		super();
		this.username = username;
		this.nowCart = nowCart;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Vector<String> getNowCart() {
		return nowCart;
	}

	public void setNowCart(Vector<String> nowCart) {
		this.nowCart = nowCart;
	}


}
