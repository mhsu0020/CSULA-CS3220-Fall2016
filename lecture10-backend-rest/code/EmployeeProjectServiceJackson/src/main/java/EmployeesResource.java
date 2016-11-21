
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = "/employees")
public class EmployeesResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;
		List<Employee> employees = null;

		try {

			employees = DatabaseAccessor.getEmployees();

		} catch (SQLException e) {
			// Escalate to Server error
			throw new ServletException(e);
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		//jackson object mapper, no need for manual JSON string manipulation
		ObjectMapper mapper = new ObjectMapper();
	
		//write result to response output stream
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(employees));
		out.flush();
		
	}	

}
