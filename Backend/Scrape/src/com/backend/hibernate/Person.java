package com.backend.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Person {
	
	private String name;
	
	private String birthday;
	
	private String major;
	
	private String college;

	private int id; //added so we can use it as primary key 
	
//	private List<YourClass> classList = new ArrayList<YourClass>(); // move into sched
	
	private Schedule schedule;
	
	public Person() {
		
	}
	
	public Person(String name, String birthday, String major, String college, List<YourClass> classList) {
		this.name = name;
		this.birthday = birthday;
		this.major = major;
		this.college = college;
//		this.classList = classList;
//		this.schedule = schedule;
	}
	
//	public Person(String name, String birthday, String major, String college, int id) {
//		this.name = name;
//		this.birthday = birthday;
//		this.major = major;
//		this.college = college;
//		this.id = id;
////		this.schedule = schedule;
//	}
//	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getCollege() {
		return college;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
}