public class Role {

	private String roleTitle;
	private int roleRank;
	private String roleLine;

	public Role(String roleTitle, int roleRank, String roleLine) {
		this.roleTitle = roleTitle;
		this.roleRank = roleRank;
		this.roleLine = roleLine;
	}

	public String getRoleTitle() {
		return this.roleTitle;
	}

	public int getRoleRank() {
		return this.roleRank;
	}

	public String getRoleLine() {
		return this.roleLine;
	}

	public void display() {
		System.out.println("Role:		" + this.roleRank + " | " + this.roleTitle + " | " + this.roleLine);
	}

}
