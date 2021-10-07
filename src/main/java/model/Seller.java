package model;

import java.io.Serializable;
import java.sql.Date;

public class Seller implements Serializable {
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
	protected String telephoneNumber;
	protected String extensionNumber; //nullable
	protected String companyName;
	protected String companyAddress;
	protected byte[] businessCert;
	protected boolean verifyStatus; //nullable

	public Seller() {
		super();
	}
	
	
	// update seller用 對應全部欄位
	public Seller(String username, String password, String fullname, String dob, String gender, String email,
			String phoneNumber, String telephoneNumber, String extensionNumber, String companyName,
			String companyAddress, byte[] businessCert, boolean verifyStatus) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.telephoneNumber = telephoneNumber;
		this.extensionNumber = extensionNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.businessCert = businessCert;
		this.verifyStatus = verifyStatus;
	}



	
	// insert seller用 除了id以外對應全部欄位
	public Seller(int id, String username, String password, String fullname, String dob, String gender, String email,
			String phoneNumber, String telephoneNumber, String extensionNumber, String companyName,
			String companyAddress, byte[] businessCert, boolean verifyStatus) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.telephoneNumber = telephoneNumber;
		this.extensionNumber = extensionNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.businessCert = businessCert;
		this.verifyStatus = verifyStatus;
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


	public void setFullname(String fullname) {
		this.fullname = fullname;
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


	public String getTelephoneNumber() {
		return telephoneNumber;
	}


	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}


	public String getExtensionNumber() {
		return extensionNumber;
	}


	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public byte[] getBusinessCert() {
		return businessCert;
	}


	public void setBusinessCert(byte[] businessCert) {
		this.businessCert = businessCert;
	}


	public boolean getVerifyStatus() {
		return verifyStatus;
	}


	public void setVerifyStatus(boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	
	
}
	