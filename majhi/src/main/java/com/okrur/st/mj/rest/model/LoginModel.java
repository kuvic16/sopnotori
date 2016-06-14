package com.okrur.st.mj.rest.model;

/**
 * @File UserModel.java: user model for rest communication 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 24, 2015
 */
public class LoginModel{
	private String username;
	private String password;
	
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
}
