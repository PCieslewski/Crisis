package Server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backend.pojos.Person;

@WebServlet("/someservlet")
public class someservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public someservlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("val");

		System.out.println("Testing123");
		
	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);       // Write response body.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("val");
		
		HttpSession session = request.getSession(true);
		
		Integer num = (Integer) session.getAttribute("num");
		if(num != null){
			session.setAttribute("num", num + 1);
			num++;
		}
		else{
			session.setAttribute("num", 0);
			num = 0;
		}

		System.out.println("Testing123");
		
	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(num.toString());       // Write response body.
	}

}
