package com.backend.authenticator;

import com.backend.pojos.Person;
import com.backend.pojos.Schedule;
import com.backend.pojos.YourClass;

import bsh.This;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parse {
	
	public static List<String> parseSchedule(String schedule){
	
		List<String> parsedInfo = new ArrayList<String>();
		String addParsedInfo;
		
		Document doc = Jsoup.parse(schedule, "");
		Iterator<Element> itr;
		
		//Parse the table with personal info
		Element personalTable = doc.getElementById("phead");
		itr = personalTable.select("td").iterator();
		
		while(itr.hasNext()){
			addParsedInfo = itr.next().text();
			parsedInfo.add(addParsedInfo);
		}
		
		//Parse the table with the schedule elements
		Element schedTable = doc.getElementById("reg_sched");
		itr = schedTable.select("td").iterator();
		
		while(itr.hasNext()){
			addParsedInfo = itr.next().text();
			parsedInfo.add(addParsedInfo);
		}
		return parsedInfo;
	}
	
	//Method to read a file and convert it to a string
	//Used for testing
	static String readFile(String fileName) {
		
		BufferedReader br;
		
		try{
			
			br = new BufferedReader(new FileReader(fileName));
	    
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        br.close();
	        
	        return sb.toString();
	        
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	private static Person getBasicInfo(List<String> parsedInfo, LoginCredentials lc) {
		Person will = new Person();
		will.setName(parsedInfo.get(1));
		will.setBirthday(parsedInfo.get(3));
		will.setCollege(parsedInfo.get(5));
		will.setMajor(parsedInfo.get(7));
		
		will.setGatorLink(lc.getUsername());
		
		String hashedPassword = hashPassword(lc.getPassword());
		will.setPasswordHash(hashedPassword);
		
		return will;
	}
	
	private static String hashPassword(String unencrypted) {
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(unencrypted); 
	}
	
	public static Person extractPersonInfo(List<String> parsedInfo, LoginCredentials lc) {
		Person will = getBasicInfo(parsedInfo, lc);
		List<YourClass> classList = new ArrayList<YourClass>();
		YourClass currentClass = new YourClass();
		boolean areYouInAnOnlineClassBecausePawelWantedABetterName = false;
		
		int counter = 1;
		int index = 8;
		
		String prevSection = "";
		String prevType = "";
		String prevCourse = "";
		String prevCredits = "";
		
		System.out.println("__________________________");
		for(int i = 0; i < parsedInfo.size(); i++) {
			System.out.println(parsedInfo.get(i));
		}
		System.out.println("__________________________");
		
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
				System.out.println("for pawel: " + parsedInfo.get(index));
				if(parsedInfo.get(index).equals("Time to be arranged")) {
//					currentClass.setDay(parsedInfo.get(index));
					System.out.println("for pawel (inside if): " + parsedInfo.get(index));
					areYouInAnOnlineClassBecausePawelWantedABetterName = true;
					currentClass.setPeriod("");
					counter++;
				}
				else {
					areYouInAnOnlineClassBecausePawelWantedABetterName = false;
				}
				currentClass.setDay(parsedInfo.get(index));
			}
			else if(counter %8 == 6) {
				if(areYouInAnOnlineClassBecausePawelWantedABetterName) {
					currentClass.setPeriod("");
				}
				else {
					currentClass.setPeriod(parsedInfo.get(index));
				}
			}
			else if(counter %8 == 7) {
				if(areYouInAnOnlineClassBecausePawelWantedABetterName) {
					currentClass.setBuilding("");
				}
				else {
					currentClass.setBuilding(parsedInfo.get(index));
				}
			}
			else if(counter %8 == 0) {
				if(areYouInAnOnlineClassBecausePawelWantedABetterName) {
					currentClass.setRoom("");
				}
				else {
					currentClass.setRoom(parsedInfo.get(index));
				}
				
	
				classList.add(currentClass);
				currentClass = new YourClass();
				
				counter = 0;
			}
			index++;
			counter++;
		}
		Schedule sched = new Schedule();
		sched.setClassList(classList);
		will.setSchedule(sched);
		return will;
	}
	
	public static Person makeAPerson(LoginCredentials lc, String rawScheduleInput) {
		List<String> parsedRawText = parseSchedule(rawScheduleInput);
		Person will = extractPersonInfo(parsedRawText, lc);
		adjustSchedule(will);
		
		return will;
	}
	
	private static void adjustSchedule(Person person){
		
		//Allocate space for all needed lists.
		List<YourClass> cl = person.getSchedule().getClassList();
		List<YourClass> expCl = new ArrayList<YourClass>();
		List<YourClass> newCl = new ArrayList<YourClass>(); 
		
		//Expand the original list by day.
		int len = cl.size();
		for(int i = 0; i < len; i++){
			expCl.addAll( expandClassByDay(cl.get(i)) );
		}
		
		//Expand the list again by period.
		len = expCl.size();
		for(int i = 0; i < len; i++){
			newCl.addAll( expandClassByPeriod(expCl.get(i)) );
		}
		
		//Set the new list as the class list.
		person.getSchedule().setClassList(newCl);
		
	}
	
	//Expands a class into an array of classes with the different periods.
	private static List<YourClass> expandClassByPeriod(YourClass c){
		
		List<YourClass> expanded = new ArrayList<YourClass>();
		
		String period = c.getPeriod();
		String[] periods = expandPeriod(period);
		int len = periods.length;
		
		for(int i=0; i<len; i++){
			
			YourClass temp = c.clone();
			temp.setPeriod(periods[i]);
			expanded.add(temp);
			
		}
		
		return expanded;
		
	}
	
	private static List<YourClass> expandClassByDay(YourClass c){
			
			List<YourClass> expanded = new ArrayList<YourClass>();
			
			String day = c.getDay();
			String[] days = expandDay(day);
			int len = days.length;
			
			for(int i=0; i<len; i++){
				
				YourClass temp = c.clone();
				temp.setDay(days[i]);
				expanded.add(temp);
				
			}
			
			return expanded;
			
		}
	
	//Expands a periods string such as "1-3" to ["1","2","3"]
	private static String[] expandPeriod(String period){
		
		int startPeriod = 0;
		int endPeriod = 0;
		
		while(period.contains("E1")){
			period.replaceFirst("E1", "12");
		}
		while(period.contains("E2")){
			period.replaceFirst("E2", "13");
		}
		while(period.contains("E3")){
			period.replaceFirst("E3", "14");
		}
		
		if(period.contains("-")){
		
			String[] periodStrings = period.split("-");
			startPeriod = Integer.parseInt(periodStrings[0]);
			endPeriod = Integer.parseInt(periodStrings[1]);
		
		}
		else if(period.length() == 4) {
			startPeriod = Integer.parseInt(period.substring(0,2));
			endPeriod = Integer.parseInt(period.substring(2,4));
		}
		else if(period.equals("")){
			//Online class
			return new String[]{""};
		}
		else if(period.length() <= 2){
			startPeriod = Integer.parseInt(period);
			endPeriod = Integer.parseInt(period);
		}
		else{
			System.out.println("Bad Period String.");
		}
		
		int len = endPeriod-startPeriod+1;
		String[] periods = new String[len];
		
		for(int i=0; i<len; i++){
			periods[i] = Integer.toString(startPeriod+i);
		}
		
		for(int i=0; i<periods.length; i++){
			if(periods[i].equals("12")){
				periods[i] = "E1";
			}
			if(periods[i].equals("13")){
				periods[i] = "E2";
			}
			if(periods[i].equals("14")){
				periods[i] = "E3";
			}
		}
	
		return periods;
		
	}
	
	private static String[] expandDay(String day){
		if(day.equals("Time to be arranged")){
			return new String[]{"Time to be arranged"};
		}
		else{
			return day.split(" ");
		}
	}
	
}
