package Restaurant;

import java.io.Serializable;

public class RestaurantBean implements Serializable {
	// private static final long serialVersionUID = 1L;

	private String userName;
	private String mobile;
	private String rstname;
	private String license;
	private String rstaddress;
	private String rsttel;
	private String type;
	private String businesshour;



	public RestaurantBean() {
		super();
	}

	public RestaurantBean(String userName, String mobile, String rstname, String license, String rstaddress,
			String rsttel,String type, String businesshour) {
		super();
		this.userName = userName;
		this.mobile = mobile;
		this.rstname = rstname;
		this.license = license;
		this.rstaddress = rstaddress;
		this.rsttel = rsttel;
		this.type = type;
		this.businesshour = businesshour;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRstname() {
		return rstname;
	}

	public void setRstname(String rstname) {
		this.rstname = rstname;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getRstaddress() {
		return rstaddress;
	}

	public void setRstaddress(String rstaddress) {
		this.rstaddress = rstaddress;
	}

	public String getRsttel() {
		return rsttel;
	}

	public void setRsttel(String rsttel) {
		this.rsttel = rsttel;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusinesshour() {
		return businesshour;
	}

	public void setBusinesshour(String businesshour) {
		this.businesshour = businesshour;
	}


}
