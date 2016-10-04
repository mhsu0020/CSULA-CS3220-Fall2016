
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddQuiz")
public class AddQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Forwards request to form view */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//setting categories  as attribute to display in form
		Category[] categories  = Category.values();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/AddQuiz.jsp").forward(request, response);
	}
	
	/* handles the form submit action */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//The parameter names matches the name attribute in the HTML input elements
		String questionText = request.getParameter("questionText");
		int difficultyRating = Integer.parseInt(request.getParameter("rating"));
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		Category category = Category.valueOf(request.getParameter("category"));
		int correctAnswerIndex = Integer.parseInt(request.getParameter("answer"));
		
		//Retrieve list of Quizzes from application scope
	     List<Quiz> quizzes = (List<Quiz>)getServletContext().getAttribute("quizzes");
		
	     //make sure new id is unique
	     int newId = getNewId(quizzes);
	     
	     //Created quiz Object from submitted form data
		Quiz quizFromForm = new Quiz(newId, questionText, new String[]{option1, option2, option3, option4}, correctAnswerIndex, difficultyRating, category);
		
		//Adding it to the list of questions in application scope
		quizzes.add(quizFromForm);
		
		//Redirect to different url (from the client), notice how this is different from request forward (server side)
		response.sendRedirect("ViewQuiz?id="+newId);
	}
	
	//Make sure the new id is unique among existing quizzes
	public int getNewId(List<Quiz> existingQuizzes){
		
		int newId = existingQuizzes.size();
		HashSet<Integer> existingIds = new HashSet<>();
		for(Quiz quiz : existingQuizzes){
			existingIds.add(quiz.id);
		}
		while(existingIds.contains(newId)){
			newId++;
		}
		return newId;
	}

}
