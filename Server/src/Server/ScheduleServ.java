package Server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.pojos.Person;

@WebServlet("/ScheduleServ")
public class ScheduleServ extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ScheduleServ() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		Person student = (Person) session.getAttribute("student");
		if(student == null){
				response.sendRedirect(request.getContextPath() + "/Login");
				return;
		}
		
		request.getRequestDispatcher("Schedule.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
