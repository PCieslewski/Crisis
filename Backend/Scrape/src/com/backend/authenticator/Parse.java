package com.backend.authenticator;

import com.backend.pojos.Person;
import com.backend.pojos.Schedule;
import com.backend.pojos.YourClass;

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
		will.setSchedule(sched);
		return will;
	}
	
	public static Person makeAPerson(LoginCredentials lc, String rawScheduleInput) {
		List<String> parsedRawText = parseSchedule(rawScheduleInput);
		Person will = extractPersonInfo(parsedRawText, lc);
		
		return will;
	}
	
}
