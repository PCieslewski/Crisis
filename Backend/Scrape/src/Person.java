
public class Person {
	private String name;
	private String birthday;
	private String major;
	private String college;
	
	public Person() {
		
	}
	
	public Person(String name, String birthday, String major, String college) {
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
	
}
