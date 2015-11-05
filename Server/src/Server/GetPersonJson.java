package Server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backend.databaseInteractions.Persist;
import com.backend.databaseInteractions.UserNotFoundException;
import com.backend.pojos.Person;

@WebServlet("/GetPersonJson")
public class GetPersonJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetPersonJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		String gatorlink = (String) request.getParameter("gatorlink");
		
		//Why doesnt this throw a UserNotFound. WILL! Or at least return null!
		Person temp = Persist.getPersonFromGatorLink(gatorlink);
		if(temp.getName() != null){
			response.getWriter().write(temp.getJson());
		}
		else{
			response.getWriter().write("{\"failure\": 1}");
		}
	}

}
