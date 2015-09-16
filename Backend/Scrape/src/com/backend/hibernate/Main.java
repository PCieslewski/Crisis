package com.backend.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.backend.Util.HibernateUtil;

public class Main {

	private static final String WEBPAGE_URL = "https://www.isis.ufl.edu/cgi-bin/nirvana?MDASTRAN=RSI-FSCHED";

	public static void main(String args[]) {

		Schedule empty = new Schedule();
		Person will = new Person("LIVESEY WILLIAM T", "06/27/1993",
				"COMPUTER ENGINEERING", "ENGINEERING", 21);
		
		persistPerson(will);
	}

	private static void persistPerson(Person bob) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bob);
		session.getTransaction().commit();
	}
	
	private static void scapeIsis() {
		Scanner input = new Scanner(System.in);
		LoginCredentials login = new LoginCredentials();

		System.out.print("Enter your username: ");
		login.setUserName(input.nextLine());

		System.out.print("Enter your password: ");
		login.setPassword(input.nextLine());

		String parse = getWebpageSourceCode(login);

		int tableStart = getIndexOfTargetString(parse, "<table", 1);
		int tableEnd = getIndexOfTargetString(parse, "</table", 2);

		String smallPartOfFile = parse.substring(tableStart - 1, tableEnd - 1);
		System.out.println(smallPartOfFile);
	}

	private static Person ngsMethod(String smallPartOfFile) {
		Person ng38 = new Person();

		// parse input parameter into below stuff
		// you should probably use getIndexOfTargetString

		ng38.setName("");
		ng38.setBirthday("");
		ng38.setMajor("");
		ng38.setCollege("");

		return ng38;
	}

	private static int getIndexOfTargetString(String wholeFile, String goal,
			int numberOfOccurance) {
		int fromIndex = 0;

		while (numberOfOccurance > 0) {
			fromIndex = (wholeFile.indexOf(goal, fromIndex)) + 1;

			if (fromIndex == 0) {
				return -1;
			}
			numberOfOccurance--;
		}
		return fromIndex;
	}

	private static String getWebpageSourceCode(LoginCredentials login) {
		WebDriver driver = new FirefoxDriver();

		driver.get(WEBPAGE_URL);

		while (!driver.getTitle().equals(
				"UF Authentication - GatorLink Login » University of Florida"))
			;

		WebElement username = driver.findElement(By.name("j_username"));
		username.sendKeys(login.getUsername());
		// username.sendKeys(user);

		WebElement password = driver.findElement(By.name("j_password"));
		password.sendKeys(login.getPassword());
		// password.sendKeys(passwd);

		WebElement submit_button = driver.findElement(By.name("login"));
		submit_button.submit();

		while (!driver.getTitle().equals("ISIS"))
			;

		String webpageUrl = driver.getPageSource();
		driver.quit();

		return webpageUrl;
	}

}