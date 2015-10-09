package Server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backend.authenticator.Authenticator;
import com.backend.authenticator.LoginCredentials;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fname = request.getParameter("fname").toString();
		String lname = request.getParameter("lname").toString();
		
		LoginCredentials lc = new LoginCredentials(fname,lname);
		
		PrintWriter pr = response.getWriter();
		
		pr.println(Authenticator.getSchedule(lc));
		//pr.println("First Name : " + fname);
		//pr.println("Last Name : " + lname);
		//pr.println("Test Complete.\n");
		//pr.println("SERVER WARNING : Will sucks at fantasy football.");
		
		//doGet(request, response);
	}

}
