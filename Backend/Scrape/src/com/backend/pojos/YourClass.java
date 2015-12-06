package com.backend.pojos;

import java.io.Serializable;

public class YourClass implements Serializable {

	private String section;
	private String type;
	private String course;
	private String credits;
	private String day;
	private String period;
	private String building;
	private String room;
	
	private int id;

	public YourClass() {

	}

	public YourClass(String section, String type, String course, String credits) {
		this.section = section;
		this.type = type;
		this.course = course;
		this.credits = credits;
	}
	
	public YourClass clone(){
		
		YourClass c = new YourClass();
		
		c.setSection(this.section);
		c.setType(this.type);
		c.setCourse(this.course);
		c.setCredits(this.credits);
		c.setDay(this.day);
		c.setPeriod(this.period);
		c.setBuilding(this.building);
		c.setRoom(this.room);
		c.setId(this.id);
		
		return c;
		
	}
	
	//Overload the equals operator to only check 5 fields.
	public boolean equals(YourClass yc){
		
		if(!yc.getCourse().equals(this.course)){
			return false;
		}
		if(!yc.getDay().equals(this.day)){
			return false;
		}
		if(!yc.getPeriod().equals(this.period)){
			return false;
		}
		if(!yc.getBuilding().equals(this.building)){
			return false;
		}
		if(!yc.getRoom().equals(this.room)){
			return false;
		}
		
		return true;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//This method expands the notation such as "2-3" into "2 3 4"
	public String getExpandedPeriod(){
		
		if(this.period.contains("-")){
			
			String[] periodStrings = this.period.split("-");
			int startPeriod = Integer.parseInt(periodStrings[0]);
			int endPeriod = Integer.parseInt(periodStrings[1]);
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = startPeriod; i< endPeriod; i++){
				sb.append(i + " ");
			}
			sb.append(endPeriod);
			
			return sb.toString();
			
		}

		return this.period;
		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.section + " ");
		sb.append(this.type + " ");
		sb.append(this.course + " ");
		sb.append(this.day + " ");
		sb.append(this.period + " ");
		sb.append(this.building + " ");
		sb.append(this.room + " ");
		return sb.toString();
	}
	
}
