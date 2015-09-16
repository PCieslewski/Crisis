package com.backend.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

   private static SessionFactory sessionFactory;

   static {
	   try {
		   sessionFactory = new Configuration().configure().buildSessionFactory();
	   }
	   catch (Throwable ex) {
		   System.err.println("Session factory wasn't created " + ex);
		   //throw error 
	   }
   }
   
   public static SessionFactory getSessionFactory() {
        return sessionFactory;
   }
   
}