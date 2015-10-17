package com.backend.pojos;

import java.util.List;

public class Person {
	
	private String name;
	
	private String birthday;
	
	private String major;
	
	private String college;

	private int id; 
	
	private Schedule schedule;

	private String gatorLink;
	
	private String passwordHash;
	
	private List<String> friends;
	
	private List<String> pendingFriends;
	
	public Person() {
		
	}
	
	public Person(String name, String birthday, String major, String college, List<YourClass> classList) {
		this.name = name;
		this.birthday = birthday;
		this.major = major;
		this.college = college;
	}

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
	
	public String getGatorLink() {
		return gatorLink;
	}
	
	public void setGatorLink(String gatorLink) {
		this.gatorLink = gatorLink;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public List<String> getFriends() {
		return friends;
	}
	
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	public List<String> getPendingFriends() {
		return pendingFriends;
	}
	
	public void setPendingFriends(List<String> pendingFriends) {
		this.pendingFriends = pendingFriends;
	}
	
	public String toString() {	
		StringBuilder results = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
		
		results.append("BASIC INFO FOR PERSON:");
		results.append(NEW_LINE + "Name: " + name);
		results.append(NEW_LINE + "Birthday: " + birthday);
		results.append(NEW_LINE + "Major: " + major);
		results.append(NEW_LINE + "College: " + college);
		results.append(NEW_LINE + "Gatorlink: " + gatorLink);
		results.append(NEW_LINE + "Hashed password: " + passwordHash);
		results.append(NEW_LINE + "*****************************");
		results.append(NEW_LINE + "CLASS SCHEDULE FOR PERSON:");
		for(int i = 0; i < schedule.getClassList().size(); i++) {
			results.append(NEW_LINE + "CLASS #" + (i+1));
			results.append(NEW_LINE + "Section: " + schedule.getClassList().get(i).getSection());
			results.append(NEW_LINE + "Type: " + schedule.getClassList().get(i).getType());
			results.append(NEW_LINE + "Course: " + schedule.getClassList().get(i).getCourse());
			results.append(NEW_LINE + "Credits: " + schedule.getClassList().get(i).getCredits());
			results.append(NEW_LINE + "Day: " + schedule.getClassList().get(i).getDay());
			results.append(NEW_LINE + "Period: " + schedule.getClassList().get(i).getPeriod());
			results.append(NEW_LINE + "Building: " + schedule.getClassList().get(i).getBuilding());
			results.append(NEW_LINE + "Room: " + schedule.getClassList().get(i).getRoom());
			results.append(NEW_LINE + "*****************************");
		}
		return results.toString();
	}
	
}