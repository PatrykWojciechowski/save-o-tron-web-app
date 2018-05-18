package com.wojciechowski.project.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class NormalUser {

	@NotNull(message="Username is required.")
	@Length(min=5, max =20, message="Your unsername should be between 5 and 20 characters.")
	@Pattern(regexp="^[a-z A-Z 0-9]{5,20}", 
		message="Name must contain only letters and numbers.")
	private String userName;
	
	@NotNull(message="Password is required.")
	@Length(min=5, max=15, message="Your password should be between 5 and 20 characters.")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,15}$", 
		message="Password must contain at least one digit, lower case letter and uppercase letter.")
	private String password;

	public NormalUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "NormalUser [userName=" + userName + ", password=" + password + "]";
	}
}
