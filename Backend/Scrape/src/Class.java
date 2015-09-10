import java.util.List;

public class Class {

	private String section;
	private String type;
	private String course;
	private String credits;
	private List<ClassTimeAndLocation> classTimeAndLocation;

	public Class() {

	}

	public Class(String section, String type, String course, String credits,
			List<ClassTimeAndLocation> classTimeAndLocation) {
		this.section = section;
		this.type = type;
		this.course = course;
		this.credits = credits;
		this.classTimeAndLocation = classTimeAndLocation;
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

	public List<ClassTimeAndLocation> getClassTimeAndLocation() {
		return classTimeAndLocation;
	}

	public void setClassTimeAndLocation(List<ClassTimeAndLocation> classTimeAndLocation) {
		this.classTimeAndLocation = classTimeAndLocation;
	}
	
}
