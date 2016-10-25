
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ChangeRating")
public class ChangeRating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Forwards request to form view */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int projectId = Integer.valueOf(request.getParameter("projectId"));
		try {
			Map<Employee, Integer> projectMemberRatings = DatabaseAccessor.getProjectMembersRatings(projectId);
			request.setAttribute("projectMemberRatings", projectMemberRatings);
			request.setAttribute("projectId", projectId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/ChangeRating.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int projectId = Integer.valueOf(request.getParameter("projectId"));
		String ratingFormInputPrefix = "rating-";
		
		Map<Integer, Integer> employeeIdNewRatings  = new HashMap<>(); 
		
		Enumeration<String> formParameterNames = request.getParameterNames();
		while(formParameterNames.hasMoreElements()) {
		         String memberRatingparamName = (String)formParameterNames.nextElement();
		         if(memberRatingparamName.startsWith(ratingFormInputPrefix)){
		        	 //Is rating form input element
			         int memberId = Integer.valueOf(memberRatingparamName.substring(ratingFormInputPrefix.length()));
			         int newRating = Integer.valueOf(request.getParameter(memberRatingparamName));
			         employeeIdNewRatings.put(memberId, newRating);	 
		         }
		}
			
        try {
        	DatabaseAccessor.updateProjectMembersRatings(projectId, employeeIdNewRatings);
        }
        catch( SQLException e ) {
            throw new ServletException( e );
        }
  
		
		response.sendRedirect("ListProjects");
	}

}
