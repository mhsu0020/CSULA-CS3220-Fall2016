
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
		
		//return json
		response.setContentType("application/json");
		
		//write employees JSON object string to output
		PrintWriter out = response.getWriter();
		out.print(getEmployeesJSONArrayWrapperObject(employees));
		out.flush();
		
	}
	
	public String getEmployeesJSONArrayWrapperObject(List<Employee> employees){
		
		StringBuilder employeesJSONArrayWrapperBuilder = new StringBuilder();
		employeesJSONArrayWrapperBuilder.append("{");
		employeesJSONArrayWrapperBuilder.append("\"employees\": " + employeesToJSONArray(employees));
		employeesJSONArrayWrapperBuilder.append("}");
		return employeesJSONArrayWrapperBuilder.toString();
	}
	
	public String employeesToJSONArray(List<Employee> employees){
    	
		StringBuilder employeesJSONArrayBuilder = new StringBuilder();
		
		employeesJSONArrayBuilder.append("[");
		
		for(Employee employee : employees){
			employeesJSONArrayBuilder.append(employeeToJSONString(employee));

			//array elements separated by commas
			employeesJSONArrayBuilder.append(",");
		}
		
		//remove extra comma at the end
		employeesJSONArrayBuilder.deleteCharAt(employeesJSONArrayBuilder.length()-1);
		
		employeesJSONArrayBuilder.append("]");
		return employeesJSONArrayBuilder.toString();
	}
	
	
	/**
	 * JSON property keys must be in double quotes, each key/value entry is separated by a comma except the last entry.
	 * 
	 */
    public StringBuilder employeeToJSONString(Employee employee){
    	
    	StringBuilder employeeJSONBuilder = new StringBuilder();
    	
    	employeeJSONBuilder.append("{");
    	
    	employeeJSONBuilder.append("\"id\": "+employee.id+",");
    	employeeJSONBuilder.append("\"firstName\":\""+employee.firstName+"\",");
    	employeeJSONBuilder.append("\"lastName\":\""+employee.lastName+"\",");
    	employeeJSONBuilder.append("\"address\":\""+employee.address+"\",");
    	
    	//Country is an object
    	employeeJSONBuilder.append("\"country\": "+countryToJSONString(employee.country)+"");
    	
    	employeeJSONBuilder.append("}");
    	return employeeJSONBuilder;
    }
    
    public String countryToJSONString(Country country){
    	
      	
    	StringBuilder countryJSONBuilder = new StringBuilder();
    	
    	countryJSONBuilder.append("{");
    	
    	countryJSONBuilder.append("\"id\": "+country.id+",");
    	countryJSONBuilder.append("\"name\":\""+country.name+"\"");
    	
    	countryJSONBuilder.append("}");
    	
    	return countryJSONBuilder.toString();
    }

}
