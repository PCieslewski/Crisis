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

@WebServlet("/FriendServ")
public class FriendServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FriendServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("Friends.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Person student = (Person) session.getAttribute("student");
		
		String fglink = (String) request.getParameter("friendGatorlink");
		
		if(fglink != null){
			
			if(fglink.equals(student.getGatorLink())){
				request.setAttribute("friendExists", false);
				System.out.println("User cannot add themselves to friends list.");
			}
			else if(Persist.doesPersonExist(fglink)){
				Person friend = Persist.getPersonFromGatorLink(fglink);
				
				System.out.println("HERE0");
				
				System.out.println(friend.getGatorLink());
				
				friend.addToPending(student.getGatorLink());
				
				System.out.println("HERE1");
				
				//This is pretty terrible. Maybe we could use an update?
				Persist.deletePerson(friend.getGatorLink());
				Persist.persistPerson(friend);
				
				System.out.println("HERE2");
				
				System.out.println(friend.getPendingFriends());
				
				request.setAttribute("friendExists", true);
			}
			else{
				request.setAttribute("friendExists", false);
			}
			
			request.getRequestDispatcher("Friends.jsp").forward(request, response);
			
		}
		else{
			
			doGet(request, response);
			
		}
	}

}
