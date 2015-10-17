package com.backend.main;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.authenticator.LoginCredentials;
import com.backend.authenticator.Parse;
import com.backend.hibernate.Person;

public class PersonTest {

	public static void main(String[] args) {
		
		LoginCredentials lc = LoginCredentials.console();
		String rawText = "";
		
		try {
			rawText = Authenticator.getScheduleOnline(lc);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		} catch (GatorlinkTimeoutException e) {
			e.printStackTrace();
		}
		
		Person will = Parse.makeAPerson(lc, rawText);
		
		System.out.println(will + "\n\n");
		
		System.out.println(will.getSchedule().getClassAt("T", "2"));
		System.out.println(will.getSchedule().getClassAt("T", "5"));
		System.out.println(will.getSchedule().getClassAt("R", "3"));
		
		
		

	}

}
