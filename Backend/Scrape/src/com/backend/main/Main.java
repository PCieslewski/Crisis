package com.backend.main;

import com.backend.hibernate.Person;

import org.hibernate.Session;

import com.backend.Util.HibernateUtil;
import com.backend.authenticator.*;

public class Main {

	public static void main(String args[]) {
		
		try {
			Parse.parseSchedule(Authenticator.getScheduleOnline(LoginCredentials.console()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);

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

}