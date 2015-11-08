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

@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddFriend() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Person student = (Person) session.getAttribute("student");
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		String fglink = (String) request.getParameter("gatorlink");
		System.out.println(fglink);
		
		if(fglink != null){
			
			if(fglink.equals(student.getGatorLink())){
				System.out.println("User cannot add themselves to friends list.");
				response.getWriter().write("{\"error\": 1}"); //Return a JSON with error.
			}
			else if(Persist.doesPersonExist(fglink)){
				Person friend = Persist.getPersonFromGatorLink(fglink);
				friend.addToPending(student.getGatorLink());
				
				//This is pretty terrible. Maybe we could use an update?
				Persist.deletePerson(friend.getGatorLink());
				Persist.persistPerson(friend);
				
				response.getWriter().write("{\"error\": 0}"); //Return a JSON with success.
			}
			else{
				response.getWriter().write("{\"error\": 1}"); //Return a JSON with error.
			}
			
		}
		else{
			response.getWriter().write("{\"error\": 1}"); //Return a JSON with error.
		}
		
	}

}
