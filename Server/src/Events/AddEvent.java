package Events;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;

@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
	    HttpSession session = request.getSession(true);
	    String studentGatorlink = (String) session.getAttribute("studentGatorlink");
		Person student = (Person) Persist.getPersonFromGatorLink(studentGatorlink);
	    
		//The JSON from the request has 3 fields. day, period, and title. All strings.
		String day = (String) request.getParameter("day");
		String period = (String) request.getParameter("period");
		String title = (String) request.getParameter("title");
		String building = (String) request.getParameter("building");
		String room = (String) request.getParameter("room");
		
		student.getSchedule().addEvent(day, period, title, building, room); //Add the event.
		
		Persist.deletePerson(student.getGatorLink()); //Update the person in the DB
		Persist.persistPerson(student);
		
		response.getWriter().write(student.getJson()); //Return a JSON with the updated person.
		
	}

}
