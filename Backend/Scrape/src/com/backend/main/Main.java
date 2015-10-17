package com.backend.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.backend.hibernate.Person;
import com.backend.hibernate.Schedule;
import com.backend.hibernate.YourClass;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.backend.Util.HibernateUtil;
import com.backend.authenticator.*;

public class Main {

	public static void main(String args[]) {
		LoginCredentials lc = LoginCredentials.console();
		String rawText = Authenticator.getSchedule(lc);
		
		Person will = Parse.makeAPerson(lc, rawText);
		
		persistPerson(will);
	}
	
	private static void persistPerson(Person bob) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bob);
		session.getTransaction().commit();
	}
	
	private static Person findPerson(String gatorLink) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();

	    Query query = session.createQuery("from com.backend.hibernate.Person pp where pp.gatorLink = '" + gatorLink + "'");
	    java.util.List list = query.list();
	    System.out.println(list);
	    t.commit();
	    session.close();
  
	    Person bruce = new Person();
	        
	    for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	     	bruce = (Person) iterator.next();
//	       	System.out.println("bday: " + bruce.getBirthday());
//	       	System.out.println(bruce.getSchedule().getClassList().get(0).getCourse());
	    }
	        
//	    System.out.println("courseL " + bruce.getSchedule().getClassList().get(0).getCourse());
	        
	    return bruce;
	}

}