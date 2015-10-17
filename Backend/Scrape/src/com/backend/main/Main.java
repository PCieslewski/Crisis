package com.backend.main;

import com.backend.authenticator.*;

import com.backend.databaseInteractions.*;

import com.backend.pojos.Person;

public class Main {

	public static void main(String args[]) {
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
		
		System.out.println(Persist.getPersonFromGatorLink("steelerfan2010"));
		
		Persist.deletePerson("steelerfan2010");
	}
	
}