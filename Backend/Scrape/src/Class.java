
public class Class {

	private String section;
	private String type;
	private String course;
	private String credits;
	private ClassTime classTime;
	private ClassLocation classLocation;

	public Class() {

	}

	public Class(String section, String type, String course, String credits, ClassTime classTime,
			ClassLocation classLocation) {
		this.section = section;
		this.type = type;
		this.course = course;
		this.credits = credits;
		this.classTime = classTime;
		this.classLocation = classLocation;
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
	
	public ClassTime getClassTime() {
		return classTime;
	}
	
	public void setClassTime(ClassTime classTime) {
		this.classTime = classTime;
	}
	
	public ClassLocation getClassLocation() {
		return classLocation;
	}
	
	public void setClassLocation(ClassLocation classLocation) {
		this.classLocation = classLocation;
	}

}
