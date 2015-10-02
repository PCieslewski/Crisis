package com.backend.hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parse {
	
	//Simple Method to parse a schedule and return a Person Object
	//TODO: Currently only prints without returning object
	static void parseSchedule(String schedule){
		
		Document doc = Jsoup.parse(schedule, "");
		Iterator<Element> itr;
		
		//Parse the table with personal info
		Element personalTable = doc.getElementById("phead");
		itr = personalTable.select("td").iterator();
		
		while(itr.hasNext()){
			System.out.println(itr.next().text());
		}
		
		//Parse the table with the schedule elements
		Element schedTable = doc.getElementById("reg_sched");
		itr = schedTable.select("td").iterator();
		
		while(itr.hasNext()){
			System.out.println(itr.next().text());
		}
		
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
