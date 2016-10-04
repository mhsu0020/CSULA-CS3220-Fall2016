
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/EditQuiz")
public class EditQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isUserAdmin(HttpServletRequest request){
		return (Boolean) request.getSession().getAttribute("isAdmin");
	}
	/* Forwards request to form view */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    // check if a user has logged in or not
        if( request.getSession().getAttribute( "userid" ) == null ) {
            response.sendRedirect( "Login" );
            return;
        }
        
        //If not admin, redirect to error page
        if(!isUserAdmin(request)){
        	response.sendRedirect("Error?message=Unauthorized");
        	return;
        }
        
		String id = request.getParameter("id");

		//Editing the quiz requires prefilling the form with existing data. Hence we need the quiz object they want to edit, and pass it to the view.
		int quizId = Integer.parseInt(id);
		List<Quiz> quizzes = (List<Quiz>) getServletContext().getAttribute("quizzes");
		
		//setting categories  as attribute to display in form
		Category[] categories  = Category.values();
		request.setAttribute("categories", categories);
		
		boolean foundQuiz=false;
		for (Quiz quiz : quizzes) {
			if (quiz.id == quizId) {
				// Passing the Quiz object to the jsp View by using
				// request.setAttribute
				request.setAttribute("quiz", quiz);
				// Forwarding the request to the view jsp. Quiz.jsp is under
				// WebContent/WEB-INF. In general, put your jsp views in there
				request.getRequestDispatcher("/WEB-INF/EditQuiz.jsp").forward(request, response);
				foundQuiz = true;
				break;
			}
		}

		if (!foundQuiz) {
			// Redirect to error page with error message
			response.sendRedirect("Error?message=" + URLEncoder.encode("Quiz does not exist", "UTF-8"));
		}

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
		int correctAnswerIndex = Integer.parseInt(request.getParameter("answer"));
		Category category = Category.valueOf(request.getParameter("category"));

		//Retrieve list of Quizzes from application scope
	     List<Quiz> quizzes = (List<Quiz>)getServletContext().getAttribute("quizzes");
		
	     //id was passed through hidden form input field
	     int newId = Integer.parseInt(request.getParameter("id"));
	     
	     //Created quiz Object from submitted form data
		Quiz quizFromForm = new Quiz(newId, questionText, new String[]{option1, option2, option3, option4}, correctAnswerIndex, difficultyRating, category);
		
		boolean foundQuiz = false;
		
		for(Quiz quizToEdit : quizzes){
			if(quizToEdit.id == quizFromForm.id){
				
				//Editing existing fields
				quizToEdit.questionText = quizFromForm.questionText;
				quizToEdit.options = quizFromForm.options;
				quizToEdit.correctAnswerIndex = quizFromForm.correctAnswerIndex;
				quizToEdit.difficultyRating = quizFromForm.difficultyRating;
				quizToEdit.category = quizFromForm.category;
				response.sendRedirect("ViewQuiz?id="+newId);
				foundQuiz = true;
				break;
			}
		}
		
		if(!foundQuiz){
			//Redirect to error page with error message
			response.sendRedirect("Error?message="+URLEncoder.encode("Quiz does not exist", "UTF-8"));
		}

	}

}
