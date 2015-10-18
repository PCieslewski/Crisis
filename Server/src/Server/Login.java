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
		
		Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
		if(isLoggedIn != null){
			if(isLoggedIn == true){
				request.getRequestDispatcher("ScheduleServ").forward(request, response);
			}
		}
		else{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		String username = request.getParameter("username").toString().toLowerCase();
		String password = request.getParameter("password").toString();
		
		LoginCredentials lc = new LoginCredentials(username,password);
		
		PrintWriter pr = response.getWriter();
		
		try {
			
			String rawScheduleString = Authenticator.getScheduleOnline(lc);
			Person student = Parse.makeAPerson(lc, rawScheduleString);
			Persist.persistPerson(student);
			
			session.setAttribute("student", student);
			session.setAttribute("isLoggedIn", true);
			
			request.getRequestDispatcher("ScheduleServ").forward(request, response);
			
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
