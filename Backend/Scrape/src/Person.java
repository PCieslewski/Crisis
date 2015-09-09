
public class Person {
	private String name;
	private String birthday;
	private String major;
	private String college;
	private Schedule schedule;
	
	public Person() {
		
	}
	
	public Person(String name, String birthday, String major, String college, Schedule schedule) {
		this.name = name;
		this.birthday = birthday;
		this.major = major;
		this.college = college;
		this.schedule = schedule;
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
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
}
