package dao;

import java.io.Serializable;

public class AdminBean implements Serializable {
	private int id;
	private String username;
	private String fullname;
	private String email;
	private String password;

	public AdminBean() {
		super();
	}

	public AdminBean(int id, String username, String fullname, String email, String password) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
