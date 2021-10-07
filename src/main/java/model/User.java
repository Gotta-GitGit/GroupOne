
package model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String username;
	protected String password;
	protected String fullname;
	protected String dob;
	protected String gender;
	protected String email;
	protected String phoneNumber;
	protected String homeNumber; // nullable
	protected float bonusPoint; // nullable

	public User() {
		super();
	}
	
	// insert user用 除了id以外對應全部欄位
	public User(String username, String password, String fullName, String dob, String gender, String email,
			String phoneNumber, String homeNumber, float bonusPoint) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.homeNumber = homeNumber;
		this.bonusPoint = bonusPoint;
	}
	
	
	// update user用 對應全部欄位
	public User(int id, String username, String password, String fullname, String dob, String gender, String email,
			String phoneNumber, String homeNumber, float bonusPoint) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.homeNumber = homeNumber;
		this.bonusPoint = bonusPoint;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullName) {
		this.fullname = fullName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public float getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(float bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

}
