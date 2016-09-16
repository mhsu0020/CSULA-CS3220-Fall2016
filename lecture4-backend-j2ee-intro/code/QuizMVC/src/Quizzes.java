
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
		Quiz quiz5 = new Quiz("new quiz", new String[] { "O(log(n))", "O(n^2)", "O(n log(n))", "O(n)" }, 2);

		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizzes.add(quiz1);
		quizzes.add(quiz2);
		quizzes.add(quiz3);
		quizzes.add(quiz4);
		quizzes.add(quiz5);

		appContext.setAttribute("quizzes", quizzes);
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Getting the list of quizzes
		List<Quiz> quizzes = (List<Quiz>)getServletContext().getAttribute("quizzes");
		Date date = new Date();
		
		request.setAttribute("date", date);
		request.setAttribute("quizzes", quizzes);
		request.setAttribute("pageHeader", "Quiz List");
		request.getRequestDispatcher( "/WEB-INF/Quizzes.jsp" )
				 .forward( request, response );
	}

}
