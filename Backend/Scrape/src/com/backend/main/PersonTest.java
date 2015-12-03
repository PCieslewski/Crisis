package com.backend.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.authenticator.LoginCredentials;
import com.backend.authenticator.Parse;
import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;
import com.backend.pojos.YourClass;

public class PersonTest {

	public static void main(String[] args) {
		
//		String[] test = expandDay("M   W F  ");
//		System.out.println(Arrays.toString(test));
		
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
		
		will.getSchedule().addEvent("M", "9", "Testing123");
		
		System.out.println(will);
		
		will.getSchedule().removeEvent("M", "9");
		
		System.out.println(will);
//		
//		
//		
//		//System.out.println();
//		
//		System.out.println(will.getSchedule().getId());
//		
//		Iterator <YourClass> itr = will.getSchedule().getClassList().iterator();
//		
//		while(itr.hasNext()){
//			
//			YourClass c = itr.next();
//			System.out.println(c.getId());
//			
//		}
		
//		Person will2 = Persist.getPersonFromGatorLink(lc.getUsername());
//		
//		will2.addToPending("testing");
//		
//		System.out.println(will2.getPendingFriends());
		
		
		
		
		

	}

}
