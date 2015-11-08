package Server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.databaseInteractions.Persist;
import com.backend.pojos.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/GetFriends")
public class GetFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetFriends() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		Person student = (Person) session.getAttribute("student");
		
		List<String> friendsList = student.getFriends();
		int len = friendsList.size();
		
		if(len > 0){
			Person[] friends = new Person[len];
			
			for(int i=0; i<len; i++){
				friends[i] = Persist.getPersonFromGatorLink(friendsList.get(i));
			}
			
			Gson gson = new GsonBuilder().create();
			response.getWriter().write(gson.toJson(friends).toString());
		}
		else{
			response.getWriter().write("{\"empty\": 1}");
		}
		
	}

}
