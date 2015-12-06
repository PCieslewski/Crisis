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
import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.pojos.PendingMeeting;
import com.backend.pojos.Person;
import com.backend.pojos.Schedule;
import com.backend.pojos.YourClass;

public class Persist {
	public static void persistPerson(Person bob) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if(!doesPersonExist(bob.getGatorLink())) {
			//List<PendingMeeting> pendingMeetings = new ArrayList<PendingMeeting>();
			//PendingMeeting a = new PendingMeeting();
			//a.setWhoInvitedYou("NG");
			//pendingMeetings.add(a);
			//bob.setPendingMeetings(pendingMeetings);
			session.beginTransaction();
			session.save(bob);
			session.getTransaction().commit();
		}
		else {
			System.out.println("Already in DB!"); 
			//throw error
		}
		session.close();
	}
	
	public static boolean doesPersonExist(String gatorLink) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		
		List list = null;
		try {
		    t = session.beginTransaction();
	
		    Query query = session.createQuery("from com.backend.pojos.Person pp where pp.gatorLink = '" + gatorLink + "'");
		    list = query.list();	    
		    t.commit();
		}
		catch(Exception e) {
			if(t != null) {
				t.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
		if(list == null) {
			return false;
		}
		
		if(list != null && list.size() == 0) {
		   return false;
	   }
	   
	   return true;
	}
	
	public static int getIdFromGatorLink(String gatorLink) throws UserNotFoundException {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		List list = null;
		
		try {
		    t = session.beginTransaction();
	
		    Query query = session.createQuery("SELECT pp.id from com.backend.pojos.Person pp WHERE pp.gatorLink = '" + gatorLink + "'");
		    list = query.list();
		    t.commit();
		}
		catch(Exception e) {
			if(t != null) {
				t.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
		if(list == null) {
			System.out.println("not in DB!");
	    	throw new UserNotFoundException();
		}
		
	    if(list.size() == 0) {
	    	System.out.println("not in DB!");
	    	throw new UserNotFoundException();
	    }
	    
	    int id = (Integer) list.get(0);

	    return id;
	}
	
	private static Schedule getScheduleFromId(int id) {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		List list = null;
		
		try {
		    t = session.beginTransaction();
	
		    Query query = session.createQuery("from com.backend.pojos.YourClass yc where yc.id = '" + id + "'");
		    list = query.list();
		    t.commit();
		}
		catch(Exception e) {
			if(t != null) {
				t.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		List list = null;
		
		try {
		    t = session.beginTransaction();
	
		    Query query = session.createQuery("from com.backend.pojos.Person pp where pp.id = '" + id + "'");
		    list = query.list();
		    t.commit();
		}
		catch(Exception e) {
			if(t != null) {
				t.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
	    Person will = new Person();
	    
	    for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	    	will = (Person) iterator.next();
	    }
   
	    Schedule schedule = getScheduleFromId(id);
	    will.setSchedule(schedule);  

	    return will;
	}
	
	public static Person getPersonFromGatorLink(String gatorLink) {
		Person will = new Person();
		try {
			int id = getIdFromGatorLink(gatorLink);
			will = getAllPersonInfoFromId(id);
		} 
		catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		return will;
	}
	
	public static void deletePerson(String gatorLink) {
		int id = 0;
		try {
			id = getIdFromGatorLink(gatorLink);
		} 
		catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		
		try {
		    t = session.beginTransaction();
		    
		    Person will = (Person) session.get(Person.class, id);
		    session.delete(will);
		    
		    t.commit();
		}
		catch(Exception e) {
			if(t != null) {
				t.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
	}
}
