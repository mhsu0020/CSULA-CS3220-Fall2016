import java.util.Map;

public class Project {

	String name;
	Employee leader;
	Map<Employee, Integer> projectMembersRatings;

	public Project() {
	}

	public Project(String name, Employee leader, Map<Employee, Integer> projectMembersRatings) {
		this.name = name;
		this.leader = leader;
		this.projectMembersRatings = projectMembersRatings;
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
