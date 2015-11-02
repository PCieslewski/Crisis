package com.backend.main;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.authenticator.LoginCredentials;
import com.backend.authenticator.Parse;
import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest2 {

	public static void main(String[] args) {
		
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
		String willText = will.toString();
		
		String willInJSON = will.getJson();
		
		System.out.println(willInJSON);
		
		Person willSmith = Person.personFromJson(willInJSON);
		String willSmithText = willSmith.toString();
		
		if(willText.equals(willSmithText)){
			System.out.println("JSONing and deJSONing will produces the same person object.");
		}
		else{
			System.out.println("JSONing and deJSONing will produces DIFFERENT BAD RESULTS.");
		}
		
		
		

	}

}
