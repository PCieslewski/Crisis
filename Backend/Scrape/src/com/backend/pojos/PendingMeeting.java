package com.backend.pojos;

public class PendingMeeting {
	
	private int meetingId;
	private YourClass event;
	private String whoInvitedYou;
	
	public int getMeetingId() {
		return meetingId;
	}
	
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	
	public YourClass getEvent() {
		return event;
	}
	
	public void setEvent(YourClass event) {
		this.event = event;
	}
	
	public String getWhoInvitedYou() {
		return whoInvitedYou;
	}
	
	public void setWhoInvitedYou(String whoInvitedYou) {
		this.whoInvitedYou = whoInvitedYou;
	}
	
	public String toString() {
		StringBuilder results = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
	
		results.append("Meeting Id:" + meetingId);
		results.append(NEW_LINE + "Invited by: " + whoInvitedYou);
		results.append(NEW_LINE + "Your Event: " + event);
		
		return results.toString();
	}
	
}
