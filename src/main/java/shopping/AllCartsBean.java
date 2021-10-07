package shopping;

import java.io.Serializable;
import java.util.Vector;

public class AllCartsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> vCart;

	public AllCartsBean(Vector<String> vCart) {
		super();
		this.vCart = vCart;
	}

	public Vector<String> getvCart() {
		return vCart;
	}

	public void setvCart(Vector<String> vCart) {
		this.vCart = vCart;
	}

	public AllCartsBean() {
		// TODO Auto-generated constructor stub
		
		
	}

}
