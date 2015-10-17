package com.backend.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	//This method gets the class at a given day or time. It does this by
	//iterating through all classes checking the day and period.
	public String getClassAt(String day, String period){
		Iterator<YourClass> itr = this.classList.iterator();
		
		while(itr.hasNext()){
			//Get next class.
			YourClass c = (YourClass) itr.next();
			//Is it the right day?
			if(c.getDay().equals(day)){
				//Is it the right time?
				if(c.getExpandedPeriod().contains(period)){
					return c.getCourse();
				}
			}
		}
		//If no classes match, return empty string.
		return "";
	}
	
	public String toString(){
		Iterator<YourClass> itr = this.classList.iterator();
		StringBuilder sb = new StringBuilder();
		sb.append("Classes:");
		while(itr.hasNext()){
			YourClass c = itr.next();
			sb.append("\n");
			sb.append(c.toString());
		}
		return sb.toString();
	}
	
}
