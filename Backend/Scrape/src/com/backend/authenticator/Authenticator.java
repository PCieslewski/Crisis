package com.backend.authenticator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//The authenticator class will contain all functions related to selenium and authenticating users.

public class Authenticator {
	
	private static final String WEBPAGE_URL = "https://www.isis.ufl.edu/cgi-bin/nirvana?MDASTRAN=RSI-FSCHED";
	
	public static String getScheduleOnline(LoginCredentials login) throws InvalidCredentialsException, GatorlinkTimeoutException{
		
		String ufTitle = "UF Authentication - GatorLink Login » University of Florida"; 
		WebDriver driver = new FirefoxDriver();

		driver.get(WEBPAGE_URL);

		while (!driver.getTitle().equals(ufTitle));

		WebElement username = driver.findElement(By.name("j_username"));
		username.sendKeys(login.getUsername());

		WebElement password = driver.findElement(By.name("j_password"));
		password.sendKeys(login.getPassword());

		WebElement submit_button = driver.findElement(By.name("login"));
		submit_button.submit();

		long currTime = System.currentTimeMillis();
		while (!driver.getTitle().equals("ISIS")){
			String source = driver.getPageSource();
			if(source.contains("Your username or password is incorrect. Please try again.")){
				driver.quit();
				throw new InvalidCredentialsException();
			}
			if(System.currentTimeMillis() - currTime > 15000){
				driver.quit();
				throw new GatorlinkTimeoutException();
			}
		}

		String webpageUrl = driver.getPageSource();
		driver.quit();

		return webpageUrl;
	}
	
}
