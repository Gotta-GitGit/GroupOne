package dao;

import java.io.Serializable;
import java.sql.Date;

public class UserBean implements Serializable{
	private String username;
	private String password;
	private String fullname;
	private String dob;
	private String gender;
	private String email;
	private String phoneNumber;
	private String homeNumber; //nullable
	private float bonusPoint; //nullable
	
	public UserBean() {
		super();
	}

	public UserBean(String username, String password, String fullName, String dob, String gender, String email,
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

	