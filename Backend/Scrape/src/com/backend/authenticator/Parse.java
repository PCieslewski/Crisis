package com.backend.authenticator;

import com.backend.hibernate.Person;

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
	
	//Simple Method to parse a schedule and return a Person Object
	//TODO: Currently only prints without returning object
	public static List<String> parseSchedule(String schedule){
		
		List<String> parsedInfo = new ArrayList<String>();
//		String[] parsedInfo;
		int parsedInfoIndex = 0;
		
		Person will = new Person();
		
		Document doc = Jsoup.parse(schedule, "");
		Iterator<Element> itr;
		
		//Parse the table with personal info
		Element personalTable = doc.getElementById("phead");
		itr = personalTable.select("td").iterator();
		
		//j is used to keep track of line number
		//this can be hard coded since it's the same for everyone
//		int j = 0;
		while(itr.hasNext()){
			String actualInfo = itr.next().text();
			if(actualInfo.length() > 0) {
				parsedInfo.add(parsedInfoIndex, actualInfo);
				parsedInfoIndex++;
			}
//			if(j == 1) {
//				will.setName(actualInfo);
//			}
//			else if(j == 3) {
//				will.setBirthday(actualInfo);
//			}
//			else if(j == 5) {
//				will.setCollege(actualInfo);
//			}
//			else if(j == 7) {
//				will.setMajor(actualInfo);
//			}
//			j++;
		}
		
		//Parse the table with the schedule elements
		Element schedTable = doc.getElementById("reg_sched");
		itr = schedTable.select("td").iterator();
		int i = 0;
		String sectionNumber;
		
		while(itr.hasNext()){
			String actualInfo = itr.next().text();
			if(actualInfo.length() > 0) {
				parsedInfo.add(parsedInfoIndex, actualInfo);
				parsedInfoIndex++;
			}
//			if(isSectionNumber(actualInfo)) {
//				System.out.println("section # :" + actualInfo);
//				sectionNumber = actualInfo;
//			}
////			System.out.println("i: " + i +  "    " + itr.next().text());
//			i++;
		}
		return parsedInfo;
	}
	
	/**
	 * We know a section number has to be length 4 and all numbers
	 * 
	 * @param str takes in parsed string from web page
	 * @return true/false if it is a course code
	 */
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

}
