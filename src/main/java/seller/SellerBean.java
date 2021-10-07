package seller;

import java.io.Serializable;

public class SellerBean implements Serializable {
	private String id;
	private String name;
	private String price;
	private String people;
	
	private String date;

	public SellerBean(String id, String name, String price, String people, String date) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.people = people;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}


	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	

}
