package com.backend.hibernate;

import java.util.List;

public class YourClass {

	private String section;
	private String type;
	private String course;
	private String credits;
	private String day;
	private String period;
	private String building;
	private String room;
	
	private int id;
//	private List<ClassTimeAndLocation> classTimeAndLocation;

	public YourClass() {

	}

	public YourClass(String section, String type, String course, String credits) {
		this.section = section;
		this.type = type;
		this.course = course;
		this.credits = credits;
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

//	public List<ClassTimeAndLocation> getClassTimeAndLocation() {
//		return classTimeAndLocation;
//	}
//
//	public void setClassTimeAndLocation(List<ClassTimeAndLocation> classTimeAndLocation) {
//		this.classTimeAndLocation = classTimeAndLocation;
//	}
	
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
	
}
