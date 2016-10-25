
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Forwards request to form view */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Country> countries = DatabaseAccessor.getCountries();
			request.setAttribute("countries", countries);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/AddEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		int countryId = Integer.valueOf(request.getParameter("country"));
		Connection c = null;
		try {
			//we are just using a dummy employee object to hold data. 
			//Because the id will be auto-generated and the country via a foreign key, we are using placeholder values.
			DatabaseAccessor.insertEmployees(new Employee(-1, firstName, lastName, address, null), countryId);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		// Redirect to different url (from the client), notice how this is
		// different from request forward (server side)
		response.sendRedirect("ListEmployees");
	}

}
