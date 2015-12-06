package Events;

import java.io.IOException;
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

@WebServlet("/RemovePendingEvent")
public class RemovePendingEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemovePendingEvent() {
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
		
		System.out.println(yc);
		System.out.println(student.getPendingMeetings().get(0).getEvent());
		System.out.println(yc.equals(student.getPendingMeetings().get(0).getEvent()));
		
		//Remove the pending meeting that has the SAME class object.
		student.removeFromPendingMeeting(yc);
		Persist.deletePerson(student.getGatorLink()); //Update the person in the DB
		Persist.persistPerson(student);
		response.getWriter().write(student.getJson()); //Return a JSON with the updated person.
		
	}

}
