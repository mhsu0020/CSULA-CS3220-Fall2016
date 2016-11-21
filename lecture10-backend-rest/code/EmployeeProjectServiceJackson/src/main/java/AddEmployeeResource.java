
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
public class AddEmployeeResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
