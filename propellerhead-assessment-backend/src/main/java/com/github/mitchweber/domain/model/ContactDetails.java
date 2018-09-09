package com.github.mitchweber.domain.model;

public class ContactDetails {
	
	private Long id;

	private String mobileNumber;
	
	private String telefoneNumber;
	
	private String email;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelefoneNumber() {
		return telefoneNumber;
	}

	public void setTelefoneNumber(String telefoneNumber) {
		this.telefoneNumber = telefoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
