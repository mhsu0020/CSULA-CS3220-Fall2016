package edu.calstatela;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBean {

	public static final String LIST_COUNTRIES_QUERY = "select * from countries";

	// Selecting both employees and their country at the same time. That way we
	// only need to do one query.
	public static final String LIST_EMPLOYEES_COUNTRIES_QUERY = "select e.id as 'employee_id', e.first_name, e.last_name, e.address, c.id as 'country_id', c.name as 'country_name' from employees e, countries c where e.country_id = c.id";

	public static final String GET_EMPLOYEE_COUNTRY_QUERY = "select e.id as 'employee_id', e.first_name, e.last_name, e.address, c.id as 'country_id', c.name as 'country_name' from employees e, countries c where e.id = ? and e.country_id = c.id";

	public static final String ADD_EMPLOYEES = "insert into employees (first_name, last_name, address, country_id) values (?, ?, ?, ?)";

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertEmployee;

	/*
	 * See
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html
	 * /jdbc.html
	 * 
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertEmployee = new SimpleJdbcInsert(dataSource).withTableName("employees")
				.usingGeneratedKeyColumns("id");
		;
	}

	// get all countries
	public List<Country> getCountries() {
		List<Country> countries = this.jdbcTemplate.query(LIST_COUNTRIES_QUERY, new RowMapper<Country>() {
			public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				return new Country(id, name);
			}
		});
		return countries;
	}

	// method to process each employee resultset row. Separate class since will
	// be used more than once
	private static final class EmployeeMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("employee_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String address = rs.getString("address");
			int countryId = rs.getInt("country_id");
			String countryName = rs.getString("country_name");
			return new Employee(id, firstName, lastName, address, new Country(countryId, countryName));
		}
	}

	// get single employee
	public Employee getEmployee(int id) {
		Employee employee = this.jdbcTemplate.queryForObject(GET_EMPLOYEE_COUNTRY_QUERY, new Object[] { id },
				new EmployeeMapper());
		return employee;

	}

	// get all employees
	public List<Employee> getEmployees() {
		List<Employee> employees = this.jdbcTemplate.query(LIST_EMPLOYEES_COUNTRIES_QUERY, new EmployeeMapper());
		return employees;
	}

	//Insert an employee using SimpleJdbcInsert, see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-simple-jdbc-insert-2
	public int insertEmployees(Employee employeeToAdd) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("first_name", employeeToAdd.getFirstName());
		parameters.put("last_name", employeeToAdd.getLastName());
		parameters.put("address", employeeToAdd.getAddress());
		parameters.put("country_id", employeeToAdd.getCountry().id);
		
		Number newId = insertEmployee.executeAndReturnKey(parameters);
		return newId.intValue();
	}

}