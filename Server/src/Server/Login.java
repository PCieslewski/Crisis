package Server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.GatorlinkTimeoutException;
import com.backend.authenticator.InvalidCredentialsException;
import com.backend.authenticator.LoginCredentials;
import com.backend.authenticator.Parse;
import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		Person student = (Person) session.getAttribute("student");
		if(student != null){
			response.sendRedirect(request.getContextPath() + "/ScheduleServ");
			//request.getRequestDispatcher("/ScheduleServ").forward(request, response);
		}
		else{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		Person test = (Person) session.getAttribute("student");
		if(test != null){
			response.sendRedirect(request.getContextPath() + "/ScheduleServ");
			//request.getRequestDispatcher("/ScheduleServ").forward(request, response);
		}
		
		String username = request.getParameter("username").toString().toLowerCase();
		String password = request.getParameter("password").toString();
		
		LoginCredentials lc = new LoginCredentials(username,password);
		
		PrintWriter pr = response.getWriter();
		
		Person temp = Persist.getPersonFromGatorLink(username);
		//If the person already exists, get them from the DB.
		if(temp.getName() != null){
			if(temp.getPasswordHash().equals(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password))){
				session.setAttribute("student", temp);
				session.setAttribute("studentGatorlink", username);
				System.out.println(temp.getPendingFriends());
				response.sendRedirect(request.getContextPath() + "/ScheduleServ");
			}
			else{
				System.out.println("A user has input bad user credentials.");
				request.setAttribute("invalidCredentials", true);
				doGet(request, response);
			}
		}
		//Else fetch them from the gatorlink website.
		else{
			try {
				
				String rawScheduleString = Authenticator.getScheduleOnline(lc);
				Person student = Parse.makeAPerson(lc, rawScheduleString);
				Persist.persistPerson(student);
				
				session.setAttribute("student", student);
				session.setAttribute("studentGatorlink", username);
				response.sendRedirect(request.getContextPath() + "/ScheduleServ");
				//request.getRequestDispatcher("/ScheduleServ").forward(request, response);
				
			} catch (InvalidCredentialsException e) {
				System.out.println("A user has input bad user credentials.");
				request.setAttribute("invalidCredentials", true);
				doGet(request, response);
			} catch (GatorlinkTimeoutException e) {
				pr.println("GatorLink login has timed out for a user.");
				request.setAttribute("timeout", true);
				doGet(request, response);
			}
		}
		
	}

}
