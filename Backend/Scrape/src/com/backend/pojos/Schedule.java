package com.backend.pojos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.backend.pojos.YourClass;

public class Schedule {

	private List<YourClass> classList = new ArrayList<YourClass>();
	private int id;
	
	public Schedule() {
		
	}
	
	public List<YourClass> getClassList() {
		return classList;
	}
	
	public void setClassList(List<YourClass> classList) {
		this.classList = classList;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		StringBuilder results = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
		
		for(int i = 0; i < classList.size(); i++) {
			results.append(NEW_LINE + "CLASS #" + (i+1));
			results.append(NEW_LINE + "Section: " + classList.get(i).getSection());
			results.append(NEW_LINE + "Type: " + classList.get(i).getType());
			results.append(NEW_LINE + "Course: " + classList.get(i).getCourse());
			results.append(NEW_LINE + "Credits: " + classList.get(i).getCredits());
			results.append(NEW_LINE + "Day: " + classList.get(i).getDay());
			results.append(NEW_LINE + "\nPeriod: " + classList.get(i).getPeriod());
			results.append(NEW_LINE + "Building: " + classList.get(i).getBuilding());
			results.append(NEW_LINE + "Room: " + classList.get(i).getRoom());
			results.append(NEW_LINE + "*****************************");
		}
		return results.toString();
	}
	
	//This method gets the class at a given day or time. It does this by
	//iterating through all classes checking the day and period.
	public String getClassAt(String day, String period){
		Iterator<YourClass> itr = this.classList.iterator();
			
		while(itr.hasNext()){
			//Get next class.
			YourClass c = (YourClass) itr.next();
			//Is it the right day?
			if(c.getDay().contains(day)){
				//Is it the right time?
				if(c.getExpandedPeriod().contains(period)){
					return c.getCourse();
				}
			}
		}
		//If no classes match, return empty string.
		return "";
	}
	
	public void addEvent(String day, String period, String title){
		
		YourClass event = new YourClass();
		event.setCourse(title);
		event.setPeriod(period);
		event.setDay(day);
		
		classList.add(event);
		
	}
	
	public void removeEvent(String day, String period){
		
		List<YourClass> newList = new ArrayList<YourClass>();
		
		int len = classList.size();
		
		for(int i=0; i<len; i++){
			YourClass c = classList.get(i);
			if(  (!c.getDay().equals(day)) || (!c.getPeriod().equals(period)) ){
				newList.add(c);
			}
		}
		this.classList = newList;
		
	}
	
}
