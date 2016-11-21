import java.util.Map;

public class Project {
	int id;
	String name;
	Employee leader;
	Map<Employee, Integer> projectMembersRatings;

	public Project() {
	}

	public Project(int id, String name, Employee leader, Map<Employee, Integer> projectMembersRatings) {
		this.id = id;
		this.name = name;
		this.leader = leader;
		this.projectMembersRatings = projectMembersRatings;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}

	public Map<Employee, Integer> getProjectMembersRatings() {
		return projectMembersRatings;
	}

	public void setProjectMembersRatings(Map<Employee, Integer> projectMembersRatings) {
		this.projectMembersRatings = projectMembersRatings;
	}

}
