package com.estore.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;

	private String address;
	private String city;
	private String province;
	private String postal;

	// getters & setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getProvince() { return province; }
	public void setProvince(String province) { this.province = province; }

	public String getPostal() { return postal; }
	public void setPostal(String postal) { this.postal = postal; }

}
