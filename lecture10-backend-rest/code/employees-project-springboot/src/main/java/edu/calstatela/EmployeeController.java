package edu.calstatela;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//mapping to tell spring boot that this class is a REST Controller
@RestController
public class EmployeeController {

	// property is injected, we do not create this object ourselves
	@Autowired
	DatabaseBean databaseBean;

	//return all employees
	@RequestMapping("/employees")
	public List<Employee> getEmployees() {
		return databaseBean.getEmployees();
	}
	
	//return single employee using path variable. For example /employee/2
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") int id) {
		return databaseBean.getEmployee(id);
	}
	
	//return single employee using RequestParam variable. For example /employee?id=2
	@RequestMapping(value="/employee/", method=RequestMethod.GET)
	public Employee getEmployeeByParam(@RequestParam("id") int id) {
		return databaseBean.getEmployee(id);
	}
	
	//@RequestBody parses request body to Java object automatically
	@RequestMapping(value="/employee/", method = RequestMethod.POST)
	public Map<String, String> AddEmployee(@RequestBody Employee employeeToAdd) {
		
		int id = databaseBean.insertEmployees(employeeToAdd);
		
		//simple JSON object with single property
		return Collections.singletonMap("id", id+"");
	}
	
	
}
