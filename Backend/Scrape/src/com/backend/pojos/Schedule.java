package com.backend.pojos;

import java.util.ArrayList;
import java.util.List;

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
	
}
