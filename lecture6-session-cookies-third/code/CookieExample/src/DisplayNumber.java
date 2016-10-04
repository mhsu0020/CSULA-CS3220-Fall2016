
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayNumber")
public class DisplayNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayNumber() {
		super();
	}

	private Cookie getNumberCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals("number"))
					return cookie;

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Check if we have a cookie with the user's number, display if exist
		Cookie numberCookie = getNumberCookie(request);
		if(numberCookie!=null){
			request.setAttribute("number", numberCookie.getValue());
		}
		request.getRequestDispatcher("/WEB-INF/DisplayNumber.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        // the user's number should either be in a cookie or a request parameter from the form
        Cookie numberCookie = getNumberCookie( request );
    	String number = request.getParameter( "number" );
        
        if( numberCookie == null ) {
            // create a cookie to store the user's name
            Cookie cookie = new Cookie( "number", number);
            response.addCookie( cookie );
        } else{
        	//Update existing cookie
        	numberCookie.setValue(number);
            response.addCookie( numberCookie);
        }
        response.sendRedirect("DisplayNumber");
        
        

	}

}
