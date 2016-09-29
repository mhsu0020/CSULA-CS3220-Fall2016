
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * loadOneStarup=1, this servlet is the 1st Servlet to be loaded. Normally Servlets are instantiated on the first request, loadOnStartup forces the Servlet to be created on startup
 * */
@WebServlet(urlPatterns = "/ViewQuiz")
public class ViewQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * if the URL is of format
		 * http://yourhost:8080/yourapp/yourservlet?id=1&name=20&gender=female,
		 * you can retrieve the id, name, and gender parameters by using
		 * request.getAttribute
		 */

		String id = request.getParameter("id");
		int quizId = Integer.parseInt(id);
		List<Quiz> quizzes = (List<Quiz>) getServletContext().getAttribute("quizzes");

		boolean foundQuiz = false;

		for (Quiz quiz : quizzes) {
			if (quiz.id == quizId) {
				// Passing the Quiz object to the jsp View by using
				// request.setAttribute
				request.setAttribute("quiz", quiz);
				// Forwarding the request to the view jsp. Quiz.jsp is under
				// WebContent/WEB-INF. In general, put your jsp views in there
				request.getRequestDispatcher("/WEB-INF/Quiz.jsp").forward(request, response);
				foundQuiz = true;
				break;
			}
		}

		if (!foundQuiz) {
			// Redirect to error page with error message
			response.sendRedirect("Error?message=" + URLEncoder.encode("Quiz does not exist", "UTF-8"));
		}

	}

}
