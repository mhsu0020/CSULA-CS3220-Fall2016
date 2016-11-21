
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = "/employee")
public class EmployeeResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//handles GET employee
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = null;

		try {

			employee = DatabaseAccessor.getEmployee(id);

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
		out.print(mapper.writeValueAsString(employee));
		out.flush();
		
	}	

	/**
	 * Handles HTTP POST /employee request. 
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read request body JSON string
		StringBuffer jsonBuffer = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jsonBuffer.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Convert request JSON String to Java Object.
		ObjectMapper mapper = new ObjectMapper();
		Employee employeeToAdd = mapper.readValue(jsonBuffer.toString(), Employee.class);

		Long employeeId = null;
		try {
			employeeId = DatabaseAccessor.insertEmployees(employeeToAdd);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
		//write id of new employee to response output stream
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"id\": "+employeeId+"}");
		out.flush();

	}

}
