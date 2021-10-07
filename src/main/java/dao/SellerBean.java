package dao;

import java.sql.Date;

public class SellerBean {
	private String username;
	private String password;
	private String fullname;
	private String dob; //nullable
	private String gender; //nullable
	private String email;
	private String phoneNumber;
	private String telephoneNumber;
	private String extensionNumber; //nullable
	private String companyName;
	private String companyAddress;
	private byte[] businessCert;
	private boolean verifyStatus; //nullable
	
	public SellerBean() {
		super();
	}

	public SellerBean(String username, String password, String fullName, String dob, String gender, String email,
			String phoneNumber, String telephoneNumber, String extensionNumber, String companyName,
			String companyAddress, byte[] businessCert, boolean verifyStatus) {
		this.username = username;
		this.password = password;
		this.fullname = fullName;
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

	public boolean isVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	
	
	
	
}
