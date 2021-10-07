package Menu;

import java.io.Serializable;

public class MenuBean implements Serializable {
	
	private String productName;
    //private Byte  ;
	private String remark;
	private String price;
	
	
	public MenuBean() {
		super();
	}

	public MenuBean(String productName, String remark, String price) {
		super();
	
		this.productName = productName;
		this.remark = remark;
		this.price = price;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}


}
