package com.backend.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.backend.Util.HibernateUtil;

public class Main {

	private static final String WEBPAGE_URL = "https://www.isis.ufl.edu/cgi-bin/nirvana?MDASTRAN=RSI-FSCHED";

	public static void main(String args[]) {
		
		//want to return person object
		Parse.parseSchedule(getWebpageSourceCode(enterCredentials()));
		
//		Schedule empty = new Schedule();
//		Person will = new Person("LIVESEY WILLIAM T", "06/27/1993",
//				"COMPUTER ENGINEERING", "ENGINEERING", 21);
//		
//		persistPerson(will);
	}

	private static void persistPerson(Person bob) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bob);
		session.getTransaction().commit();
	}
	
	private static LoginCredentials enterCredentials() {
		Scanner input = new Scanner(System.in);
		LoginCredentials login = new LoginCredentials();

		System.out.print("Enter your username: ");
		login.setUserName(input.nextLine());

		System.out.print("Enter your password: ");
		login.setPassword(input.nextLine());
		
		return login;
	}

	private static String getWebpageSourceCode(LoginCredentials login) {
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

		while (!driver.getTitle().equals("ISIS"));

		String webpageUrl = driver.getPageSource();
		driver.quit();

		return webpageUrl;
	}

}