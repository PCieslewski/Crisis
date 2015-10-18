package com.backend.main;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.authenticator.LoginCredentials;
import com.backend.authenticator.Parse;
import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;

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
		
		will.addToPending("abc");
		will.addToPending("trest");
		will.addToPending("test");
		will.addToPending("test");
		
		System.out.println(will.getPendingFriends());
		
		Person will2 = Persist.getPersonFromGatorLink(lc.getUsername());
		
		will2.addToPending("testing");
		
		System.out.println(will2.getPendingFriends());
		
		
		
		
		

	}

}
