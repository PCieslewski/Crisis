package com.backend.databaseInteractions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.backend.Util.HibernateUtil;
import com.backend.pojos.Person;
import com.backend.pojos.Schedule;
import com.backend.pojos.YourClass;

public class Persist {
	public static void persistPerson(Person bob) {
		if(!doesPersonExist(bob.getGatorLink())) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(bob);
			session.getTransaction().commit();
		}
		else {
			System.out.println("Already in DB!"); 
			//throw error
		}
	}
	
	private static boolean doesPersonExist(String gatorLink) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();

	    Query query = session.createQuery("from com.backend.pojos.Person pp where pp.gatorLink = '" + gatorLink + "'");
	    List list = query.list();	    
	    t.commit();
	    session.close();
  
	   if(list.size() == 0) {
		   return false;
	   }
	   
	   return true;
	}
	
	private static int getIdFromGatorLink(String gatorLink) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();

	    Query query = session.createQuery("SELECT pp.id from com.backend.pojos.Person pp WHERE pp.gatorLink = '" + gatorLink + "'");
	    List list = query.list();
	    t.commit();
	    session.close();
	    
	    int id = (Integer) list.get(0);

	    return id;
	}
	
	private static Schedule getScheduleFromId(int id) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();

	    Query query = session.createQuery("from com.backend.pojos.YourClass yc where yc.id = '" + id + "'");
	    List list = query.list();
	    t.commit();
	    session.close();
	    
	    Schedule schedule = new Schedule();
	    List<YourClass> classList = new ArrayList<YourClass>();
	    YourClass myClass = new YourClass();
	    
	    for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	     	myClass = (YourClass) iterator.next();
	     	classList.add(myClass);
	    }
	    schedule.setClassList(classList);

	    return schedule;
	}
	
	private static Person getAllPersonInfoFromId(int id) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();

	    Query query = session.createQuery("from com.backend.pojos.Person pp where pp.id = '" + id + "'");
	    List list = query.list();
	    t.commit();
	    session.close();
	   
	    Person will = new Person();
	    
	    for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	    	will = (Person) iterator.next();
	    }
   
	    Schedule schedule = getScheduleFromId(id);
	    will.setSchedule(schedule);  

	    return will;
	}
	
	public static Person getPersonFromGatorLink(String gatorLink) {
		int id = getIdFromGatorLink(gatorLink);
		Person will = getAllPersonInfoFromId(id);
		
		return will;
	}
	
	public static void deletePerson(String gatorLink) {
		int id = getIdFromGatorLink(gatorLink);
		//go to respective tables to remove objects
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

	    Session session = factory.openSession();

	    Transaction t = session.beginTransaction();
	    
	    Person will = (Person) session.get(Person.class, id);
	    session.delete(will);
	    
	    t.commit();
	    session.close();
		
	}
}
