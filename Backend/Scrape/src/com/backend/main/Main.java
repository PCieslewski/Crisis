package com.backend.main;

import com.backend.authenticator.*;

import com.backend.databaseInteractions.*;

import com.backend.pojos.Person;

public class Main {

	public static void main(String args[]) {
		LoginCredentials lc = LoginCredentials.console();
		String rawText = "";
//		Persist.deletePerson("pawel");
		try {
			rawText = Authenticator.getScheduleOnline(lc);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		} catch (GatorlinkTimeoutException e) {
			e.printStackTrace();
		}
		
		Person will = Parse.makeAPerson(lc, rawText);
		
		System.out.println(will);
		
		Persist.persistPerson(will);
		
		
		
//		try {
//			System.out.println(Persist.getIdFromGatorLink("steel"));
//		} catch (UserNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		Person bob = Persist.getPersonFromGatorLink("pawel");
//		System.out.println(bob);
	}
	
}