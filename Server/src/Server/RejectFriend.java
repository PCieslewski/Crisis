package Server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;

@WebServlet("/RejectFriend")
public class RejectFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RejectFriend() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Friends.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
	    HttpSession session = request.getSession(true);
		Person student = (Person) session.getAttribute("student");
	    
		String friendGatorlink = (String) request.getParameter("gatorlink");
		
		if(student.getPendingFriends().contains(friendGatorlink)){ //Check if the friend is actually pending
			student.removeFromPending(friendGatorlink); //Remove the friend from the pending list
			session.setAttribute("student", student); //Update the acceptor in the current SESSION!
			Persist.deletePerson(student.getGatorLink()); //Update the acceptor in the DB
			Persist.persistPerson(student);
			
			response.getWriter().write("{\"error\": 0}"); //Return a JSON with success!
		}
		else{
			response.getWriter().write("{\"error\": 1}"); //Return a JSON with wtf?
		}
	}

}
