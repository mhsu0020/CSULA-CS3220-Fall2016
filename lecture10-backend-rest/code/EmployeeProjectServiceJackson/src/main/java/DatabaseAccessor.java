import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DatabaseAccessor {

	public static final String LIST_COUNTRIES_QUERY = "select * from countries";
	

	//Selecting both employees and their country at the same time. That way we only need to do one query.
	public static final String LIST_EMPLOYEES_COUNTRIES_QUERY = "select e.id as 'employee_id', e.first_name, e.last_name, e.address, c.id as 'country_id', c.name as 'country_name' from employees e, countries c where e.country_id = c.id";

	//Retrieve project and leader info
	public static final String LIST_PROJECTS_QUERY =  "select p.id as 'project_id', p.name as 'project_name', p.leader_id as 'leader_id', e.first_name as 'first_name', e.last_name as 'last_name', e.address as 'address', c.id as 'country_id', c.name as 'country_name' from projects p, employees e, countries c where p.leader_id = e.id and e.country_id = c.id";

	//Retrieve member info and ratings for specific project
	public static final String LIST_PROJECTS_MEMBERS_QUERY = "select e.id as 'employee_id', e.first_name, e.last_name, e.address, c.id as 'country_id', c.name as 'country_name', pm.member_team_rating from employees e, countries c, project_members pm where e.country_id = c.id and pm.member_id = e.id and pm.project_id = ?";

	public static final String UPDATE_PROJECTS_MEMBERS_RATINGs = "update project_members set member_team_rating = ? where project_id = ? and member_id = ?";
	
	public static final String ADD_EMPLOYEES = "insert into employees (first_name, last_name, address, country_id) values (?, ?, ?, ?)";
	
	//Batch processing: call addBatch for each query creation, then call executeBatch to run all update queries
	public static void updateProjectMembersRatings(int projectId, Map<Integer, Integer> employeeIdNewRatings) throws SQLException {
		
		Connection c = null;
		try {

			c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);

			PreparedStatement stmt = c.prepareStatement(UPDATE_PROJECTS_MEMBERS_RATINGs);
			
			for(Entry<Integer, Integer> employeeIdNewRating : employeeIdNewRatings.entrySet()){
				//new value, project_id, member_id, in order
				stmt.setInt(1, employeeIdNewRating.getValue());
				stmt.setInt(2, projectId);
				stmt.setInt(3, employeeIdNewRating.getKey());
				//adds to query batch, not executed yet
				stmt.addBatch();
			}

			//run all updates
	        stmt.executeBatch();
	        
		} catch (SQLException e) {
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		
	}
	
	public static long insertEmployees(Employee employeeToAdd) throws SQLException{
		
	    Connection c = null;
        try {
    		c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);
    		//request generated employee key
    		PreparedStatement pstmt = c.prepareStatement(ADD_EMPLOYEES, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString( 1, employeeToAdd.firstName);
            pstmt.setString( 2, employeeToAdd.lastName);
            pstmt.setString( 3, employeeToAdd.address);
            pstmt.setInt(4, employeeToAdd.country.id);
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating Employee failed, no ID obtained.");
                }
            }
        }
        catch( SQLException e ) {
        	throw e;
        }
        finally {
            try {
                if( c != null ) c.close();
            }
            catch( SQLException e ) {
            	throw e;
            }
        }
		
		
	}
	
	public static void insertEmployees(Employee employeeToAdd, int countryId) throws SQLException{
		
	    Connection c = null;
        try {
    		c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);           
    		PreparedStatement pstmt = c.prepareStatement(ADD_EMPLOYEES);
            pstmt.setString( 1, employeeToAdd.firstName);
            pstmt.setString( 2, employeeToAdd.lastName);
            pstmt.setString( 3, employeeToAdd.address);
            pstmt.setInt(4, countryId);
            pstmt.executeUpdate();
        }
        catch( SQLException e ) {
        	throw e;
        }
        finally {
            try {
                if( c != null ) c.close();
            }
            catch( SQLException e ) {
            	throw e;
            }
        }
		
		
	}
	
	/**
	 * get project members and their ratings for a specific project
	 * 
	 * */
	public static Map<Employee, Integer> getProjectMembersRatings(int projectId) throws SQLException {
		
		Map<Employee, Integer> projectMemberRatings = new HashMap<>();
		Connection c = null;
		try {

			c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);

			PreparedStatement stmt = c.prepareStatement(LIST_PROJECTS_MEMBERS_QUERY);
			stmt.setInt(1, projectId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String address = rs.getString("address");
				int countryId = rs.getInt("country_id");
				String countryName = rs.getString("country_name");
				int rating = rs.getInt("member_team_rating");
				Country country = new Country(countryId, countryName);

				Employee employee = new Employee(employeeId, firstName, lastName, address, country);
				projectMemberRatings.put(employee, rating);
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return projectMemberRatings;
		
	}

	
	public static List<Project> getProjects() throws SQLException {
		List<Project> projects = new ArrayList<>();
		Connection c = null;
		try {

			c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);

			PreparedStatement stmt = c.prepareStatement(LIST_PROJECTS_QUERY);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				int leaderId = rs.getInt("leader_id");
				String leaderFirstName = rs.getString("first_name");
				String leaderLastName = rs.getString("last_name");
				String address = rs.getString("address");
				int countryId = rs.getInt("country_id");
				String countryName = rs.getString("country_name");
				
				Country country = new Country(countryId, countryName);
				Employee leader = new Employee(leaderId, leaderFirstName, leaderLastName, address, country);
				Project project = new Project(projectId, projectName, leader, getProjectMembersRatings(projectId));
				projects.add(project);
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		
		return projects;
	}
	
	public static List<Country> getCountries() throws SQLException {
		Connection c = null;
		List<Country> countries = new ArrayList<>();
		try {

			c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);

			PreparedStatement stmt = c.prepareStatement(LIST_COUNTRIES_QUERY);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				countries.add(new Country(id, name));
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return countries;
		
	}
	
	public static List<Employee> getEmployees() throws SQLException {
		Connection c = null;
		List<Employee> employees = new ArrayList<>();
		try {

			c = ConnectionUtils.getMySQLConnection(DatabaseConfig.MYSQL_USERNAME, DatabaseConfig.MYSQL_PASSWORD,
					DatabaseConfig.MYSQL_HOST, DatabaseConfig.MYSQL_PORT, DatabaseConfig.MYSQL_DATABASE_TO_USE);

			PreparedStatement stmt = c.prepareStatement(LIST_EMPLOYEES_COUNTRIES_QUERY);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String address = rs.getString("address");

				int countryId = rs.getInt("country_id");
				String countryName = rs.getString("country_name");
				employees.add(new Employee(id, firstName, lastName, address, new Country(countryId, countryName)));
			}

		} catch (SQLException e) {
			// Escalate to Server error
			throw e;
		}
		// Always close connections, no matter what happened
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return employees;

	}

}
