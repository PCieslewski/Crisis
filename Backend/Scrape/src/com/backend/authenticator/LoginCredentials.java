package com.backend.authenticator;

import java.util.Scanner;

public class LoginCredentials {
	
	private String username;
	private String password;
	
	public LoginCredentials() {
		
	}
	
	public LoginCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static LoginCredentials console(){
		Scanner input = new Scanner(System.in);
		LoginCredentials login = new LoginCredentials();

		System.out.print("Enter your username: ");
		login.setUserName(input.nextLine());

		System.out.print("Enter your password: ");
		login.setPassword(input.nextLine());
		
		input.close();
		
		return login;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
