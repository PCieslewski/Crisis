
public class ClassTimeAndLocation {
	private String day;
	private String period;
	private String building;
	private String room;
	
	public ClassTimeAndLocation() {
		
	}
	
	public ClassTimeAndLocation(String day, String period, String building, String room) {
		this.day = day;
		this.period = period;
		this.building = building;
		this.room = room;
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
}
