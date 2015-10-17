package com.backend.main;

import java.util.ArrayList;
import java.util.List;

import com.backend.hibernate.Person;
import com.backend.hibernate.Schedule;
import com.backend.hibernate.Temp;
import com.backend.hibernate.YourClass;

import org.hibernate.Session;

import com.backend.Util.HibernateUtil;
import com.backend.authenticator.*;

public class Main {

	public static void main(String args[]) {
		//need to store this shit
		//Parse.parseSchedule(Authenticator.getSchedule());
		
//		Person will = Parse.parseSchedule(Authenticator.getSchedule());
		List<String> test = Parse.parseSchedule(Authenticator.getSchedule());
		
//		List<YourClass> classes = new ArrayList<YourClass>();
//		YourClass c1 = new YourClass("sec1","type1", "cour1", "3");
//		YourClass c2 = new YourClass("sec2","type2", "cour2", "4");
//		YourClass c3 = new YourClass("sec2=3","type3", "cour3", "3");
//		String section, String type, String course, String credits
//		classes.add(c1);
//		classes.add(c2);
		
//		System.out.println(test.toString());
//		for(int i = 0; i < test.size(); i++) {
//			System.out.println(test.get(i));
//		}
		
		Person will = extractPersonInfo(test);
		//RELEVANT: !!
//		for(int i = 0; i < will.getClassList().size(); i++) {
//			System.out.println(will.getClassList().get(i).getPeriod());
//		}
		
//		classes.add(c3);
//		Person will = doShit(test);
//		will.setClassList(classes);
//		will.setSchedule(schedule)
		
//		for(int i = 0; i < will.getSchedule().getClassList().size(); i++) {
//			System.out.println(will.getSchedule().getClassList().get(i).getSection());
//		}
		
//		persistPerson(doShit(test));

//		System.out.println(will.getBirthday());
//		System.out.println(will.getCollege());
//		System.out.println(will.getMajor());
//		System.out.println(will.getName());

//		Schedule empty = new Schedule();
//		Person will = new Person("LIVESEY WILLIAM T", "06/27/1993",
//				"COMPUTER ENGINEERING", "ENGINEERING", 21);
//		
	
		persistPerson(will);
		
		
	}
	
	private static Person getBasicInfo(List<String> parsedInfo) {
		Person will = new Person();
		will.setName(parsedInfo.get(1));
		will.setBirthday(parsedInfo.get(3));
		will.setCollege(parsedInfo.get(5));
		will.setMajor(parsedInfo.get(7));
		
		return will;
	}
	
	private static Person extractPersonInfo(List<String> parsedInfo) {
		Person will = getBasicInfo(parsedInfo);
		List<YourClass> classList = new ArrayList<YourClass>();
		YourClass currentClass = new YourClass();
		
		int counter = 1;
		int index = 8;
		
		String prevSection = "";
		String prevType = "";
		String prevCourse = "";
		String prevCredits = "";
		
		while(!parsedInfo.get(index).equals("Total Credits:")) {
			if(counter %8 == 1) {
				if(!parsedInfo.get(index).equals("")) {
					prevSection = parsedInfo.get(index);
				}
				currentClass.setSection(prevSection);
			}
			else if(counter %8 == 2) {
				if(!parsedInfo.get(index).equals("")) {
					prevType = parsedInfo.get(index);
				}
				currentClass.setType(prevType);
			}
			else if(counter %8 == 3) {
				if(!parsedInfo.get(index).equals("")) {
					prevCourse = parsedInfo.get(index);
				}
				currentClass.setCourse(prevCourse);
			}
			else if(counter %8 == 4) {
				if(!parsedInfo.get(index).equals("")) {
					prevCredits = parsedInfo.get(index);
				}
				currentClass.setCredits(prevCredits);
			}
			else if(counter %8 == 5) {
				currentClass.setDay(parsedInfo.get(index));
			}
			else if(counter %8 == 6) {
				currentClass.setPeriod(parsedInfo.get(index));
			}
			else if(counter %8 == 7) {
				currentClass.setBuilding(parsedInfo.get(index));
			}
			else if(counter %8 == 0) {
				currentClass.setRoom(parsedInfo.get(index));
	
				classList.add(currentClass);
				currentClass = new YourClass();
				
				counter = 0;
			}
			index++;
			counter++;
		}
		Schedule sched = new Schedule();
		sched.setClassList(classList);
//		sched.setId(6969696);
		sched.setZzz("yooyoyoyoyho53qyojhreio");
		Temp tt = new Temp();
		tt.setYo("Hey WI!L!!!");
		List<Temp> a = new ArrayList<Temp>();
		a.add(tt);
		sched.setTemp(a);
		will.setSchedule(sched);
		return will;
	}
	
	private static boolean isSectionNumber(String str) {
		if(str.length() != 4) {
			return false;
		}
		for(int i = 0; i < str.length(); i++) {
			if(((int)str.charAt(i) > 57) || ((int)str.charAt(i) < 48)) {
				return false;
			}
		}
		return true;
	}
	
//	private static Person doShit(List<String> info) {
//		String name = info.get(1);
//		String birthday = info.get(3);
//		String college = info.get(5);
//		String major = info.get(7);
//		List<YourClass> classList = new ArrayList<YourClass>();
//		List<ClassTimeAndLocation> timeAndLocationList = new ArrayList<ClassTimeAndLocation>();
//		Person will = new Person(name, birthday, college, major, null);
//	
//		YourClass currentClass = new YourClass();
//		
//		
//		int i = 8;
//		while(i < info.size()) {
//			String actualValue = info.get(i);
//			
//			if(isSectionNumber(actualValue)) {
//				currentClass.setSection(actualValue);
//				i++;
//				
//				actualValue = info.get(i);
//				currentClass.setType(actualValue);
//				i++;
//				
//				actualValue = info.get(i);
//				currentClass.setCourse(actualValue);
//				i++;
//				
//				actualValue = info.get(i);
//				currentClass.setCredits(actualValue);
//				i++;
//				
//				actualValue = info.get(i);
//				
//				while(!isSectionNumber(actualValue)) {
//					
//					if(actualValue.equals("Total Credits:")) {
//						System.out.println("BREAL!");
//						break;
//					}
//					
//					ClassTimeAndLocation timeAndLocation = new ClassTimeAndLocation();
//					timeAndLocation.setDay(actualValue);
//					i++;
//					
////					System.out.println("HEREd: " + actualValue);
//					
//					actualValue = info.get(i);
//					timeAndLocation.setPeriod(actualValue);
//					i++;
//					
////					System.out.println("HEREc: " + actualValue);
//					
//					actualValue = info.get(i);
//					timeAndLocation.setBuilding(actualValue);
//					i++;
//					
////					System.out.println("HEREb: " + actualValue);
//					
//					actualValue = info.get(i);
//					timeAndLocation.setRoom(actualValue);
//					i++;
//					
////					System.out.println("HEREa: " + actualValue);
//					
//					timeAndLocationList.add(timeAndLocation);
//					actualValue = info.get(i);
//				}
//				
//				classList.add(currentClass);
//				currentClass.setClassTimeAndLocation(timeAndLocationList);
//				currentClass = new YourClass();
//			}
//			else {
////				System.out.println("(else) ACTUAL :" + actualValue);
//				if(!actualValue.equals("Total Credits:")) {
//					//ACTUAL VALUE IS TOTAL CREDITS HERE!!!
//				}
//				i++;
//			}
//		}
//		
//		
//		
////		for(int i = 8; i < info.size(); i++) {
////			String actualValue = info.get(i);
////			if(actualValue.equals("Total Credits:")) {
////				System.out.println("ending");
////				break;
////			}
////			if(isSectionNumber(actualValue)) {
////				if(currentClass != null) {
////					classList.add(currentClass);
////				}
//////				YourClass currentClass = new YourClass();
////				currentClass.setSection(actualValue);
////				System.out.println("adding: " + actualValue);
////				
////			}
////		}
//		
//		will.setClassList(classList);
//	
//		return will;
//	}
	
	private static void persistPerson(Person bob) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bob);
		session.getTransaction().commit();
	}

}