package com.backend.pojos;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	private List<PendingMeeting> pendingMeetings;
	
	private int meetId;
	
	public Person() {
		this.friends = new ArrayList<String>();
		this.pendingFriends = new ArrayList<String>();
		this.pendingMeetings = new ArrayList<PendingMeeting>();
	}
	
	public Person(String name, String birthday, String major, String college, List<YourClass> classList) {
		this.name = name;
		this.birthday = birthday;
		this.major = major;
		this.college = college;
		this.friends = new ArrayList<String>();
		this.pendingFriends = new ArrayList<String>();
		this.pendingMeetings = new ArrayList<PendingMeeting>();
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
	
	public List<PendingMeeting> getPendingMeetings() {
		return pendingMeetings;
	}
	
	public void setPendingMeetings(List<PendingMeeting> pendingMeetings) {
		this.pendingMeetings = pendingMeetings;
	}
	
	public int getMeetId() {
		return meetId;
	}
	
	public void setMeetId(int meetId) {
		this.meetId = meetId;
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
		results.append(schedule);
		
		return results.toString();
	}
	
	public void addToPending(String gatorlink){
		if(this.pendingFriends.contains(gatorlink)){
			return; //If the friend is already in the list, do nothing.
		}
		else{
			this.pendingFriends.add(gatorlink);
		}
	}
	
	public void removeFromPending(String gatorlink){
		while(pendingFriends.contains(gatorlink)){
			pendingFriends.remove(gatorlink);
		}
	}
	
	public void addToFriends(String gatorlink){
		if(this.friends.contains(gatorlink)){
			return; //If the friend is already in the list, do nothing.
		}
		else{
			this.friends.add(gatorlink);
		}
	}
	
	public void removeFromFriends(String gatorlink){
		while(friends.contains(gatorlink)){
			friends.remove(gatorlink);
		}
	}
	
	public void addToPendingMeeting(PendingMeeting pm){
		this.pendingMeetings.add(pm);
	}
	
	//This is a WEIRD function. Takes in your class instead of pending meeting. Talk to Pawel if need help. 
	public void removeFromPendingMeeting(YourClass yc){
		ArrayList<PendingMeeting> newPendingMeetings = new ArrayList<PendingMeeting>();
		
		int len = this.pendingMeetings.size();
		for(int i=0; i<len; i++){
			if(!pendingMeetings.get(i).getEvent().equals(yc)){
				newPendingMeetings.add(pendingMeetings.get(i));
			}
		}
		
		this.pendingMeetings = newPendingMeetings;
		
	}
	
	//Static method to return a person object from a JSON string. Do not use with "new."
	public static Person personFromJson(String personJson){
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(personJson, Person.class);
	}
	
	public String getJson(){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
	
}