
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

/*
 * loadOneStarup=1, this servlet is the 1st Servlet to be loaded. Normally Servlets are instantiated on the first request, loadOnStartup forces the Servlet to be created on startup
 * */
@WebServlet(urlPatterns = "/Quizzes", loadOnStartup = 1)
public class Quizzes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * Executed exactly one for this servlet
	 * 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ServletContext appContext = getServletContext();
		Quiz quiz1 = new Quiz("What is 1+1", new String[] { "1", "2", "3", "4" }, 1);
		Quiz quiz2 = new Quiz("who is the first US president",
				new String[] { "George Washington", "Michael Hsu", "Kobe Bryant", "Tylor Durden" }, 0);
		Quiz quiz3 = new Quiz("What is the Answer to the Ultimate Question of Life, the Universe, and Everything", new String[] { "1", "2", "3", "42" }, 3);
		Quiz quiz4 = new Quiz("What is the average case complexity for merge sort", new String[] { "O(log(n))", "O(n^2)", "O(n log(n))", "O(n)" }, 2);

		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizzes.add(quiz1);
		quizzes.add(quiz2);
		quizzes.add(quiz3);
		quizzes.add(quiz4);

		appContext.setAttribute("quizzes", quizzes);
	}
	
	//Helper method to create Quizzes table html string
	public String buildQuizString(List<Quiz> quizzes){
		//Use StringBuilder to avoid creating a new String object on every concat
		StringBuilder quizString = new StringBuilder();
		quizString.append("<table><tr><th>Question Text</th><th>Options</th><th>Answer</th></tr>");
		for(Quiz quiz : quizzes){
			quizString.append("<tr>");
			quizString.append("<td>"+quiz.questionText+"</td>");
			quizString.append("<td><ul>");
			for(String option : quiz.options){
				quizString.append("<li>"+option+"</li>");
			}
			quizString.append("</ul></td>");
			quizString.append("<td>"+quiz.options[quiz.correctAnswerIndex]+"</td>");
			quizString.append("</tr>");
		}
		quizString.append("</table>");
		return quizString.toString();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Quiz> quizzes = (List<Quiz>)getServletContext().getAttribute("quizzes");
		response.setContentType("text/html");
		response.getWriter().println("<!DOCTYPE html><html lang=\"en\"> <head><meta charset=\"utf-8\"><title>Quiz Example</title></head><body><h1>Loading Quizzes from Application Scope</h1><h2>Quizzes</h2>"+buildQuizString(quizzes)+"</body></html>");

	}

}
