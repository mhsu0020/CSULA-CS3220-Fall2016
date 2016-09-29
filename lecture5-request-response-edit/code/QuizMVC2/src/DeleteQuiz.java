
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteQuiz")
public class DeleteQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Forwards request to form view */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int quizId = Integer.parseInt(id);
		List<Quiz> quizzes = (List<Quiz>) getServletContext().getAttribute("quizzes");
		for (Quiz quiz : quizzes) {
			if (quiz.id == quizId) {
				//Delete quiz from list
				quizzes.remove(quiz);
				response.sendRedirect("Quizzes");
				break;
			}
		}
		
	}

}
