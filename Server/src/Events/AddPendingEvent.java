package Events;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.databaseInteractions.Persist;
import com.backend.pojos.PendingMeeting;
import com.backend.pojos.Person;
import com.backend.pojos.YourClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddPendingEvent")
public class AddPendingEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPendingEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    Gson gson = new GsonBuilder().create();
		
	    //Get the session of the student
	    HttpSession session = request.getSession(true);
	    String studentGatorlink = (String) session.getAttribute("studentGatorlink");
		Person student = (Person) Persist.getPersonFromGatorLink(studentGatorlink);
	    
		//Read all the fields of the request
		String gltemp = request.getParameter("gatorlinks");
		
		System.out.println("GOT HERE!");
		System.out.println(gltemp);
		
		String[] gatorlinks = gson.fromJson(gltemp, String[].class);
		
		//System.out.println(Arrays.asList(gatorlinks));
		
		String day = (String) request.getParameter("day");
		String period = (String) request.getParameter("period");
		String title = (String) request.getParameter("title");
		String building = (String) request.getParameter("building");
		String room = (String) request.getParameter("room");
		
		//Create a your class object.
		YourClass yc = new YourClass();
		yc.setDay(day);
		yc.setPeriod(period);
		yc.setCourse(title);
		yc.setBuilding(building);
		yc.setRoom(room);
		
		//Create a pending meeting object.
		PendingMeeting pm = new PendingMeeting();
		pm.setEvent(yc);
		pm.setWhoInvitedYou(studentGatorlink);
		pm.setMeetingId(0);
		
		System.out.println("HEEEEEEEEERRRRRRRRRREEEEEEEEEEEEe");
		System.out.println(pm);
		
		//Add the pending meeting to all the people in the list of gatorlinks.
		for(int i=0; i<gatorlinks.length; i++){
			String gl = gatorlinks[i];
			Person stuTemp = (Person) Persist.getPersonFromGatorLink(gl);
			stuTemp.addToPendingMeeting(pm);
			Persist.deletePerson(gl);
			Persist.persistPerson(stuTemp);
		}
		
		//Add the event to the schedule of the person scheduling.
		
		//TEMP DISABLE!!!!!!
		
//		student.getSchedule().addEvent(day, period, title, building, room); //Add the event.
//		Persist.deletePerson(student.getGatorLink()); //Update the person in the DB
//		Persist.persistPerson(student);
//		response.getWriter().write(student.getJson()); //Return a JSON with the updated person.
		
	}

}
