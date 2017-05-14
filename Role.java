//Hannah Montague and Melissa Gonzalez
//Role Class - A player can take on a role which contains its own attributes
import java.util.*;
import java.lang.*;

public class Role {
	
	private String roleTitle;  //Name of the role
	private int roleRank;      //Value of the role's rank
	private String roleLine;   //The role's line
	
	//Constructor of Role
	public Role(String roleTitle, int roleRank, String roleLine) {
		this.roleTitle = roleTitle;
		this.roleRank = roleRank;
		this.roleLine = roleLine;
	}
	
	//Getter: getRoleTitle
	//Purpose: get the name of the player's role
	public String getRoleTitle() {
		return this.roleTitle;
	}
	
	//Getter: getRoleRank
	//Purpose: get the value of the role the player has
	public int getRoleRank() {
		return this.roleRank;
	}
	
	//Getter: getRoleLine
	//Purpose: get the line of the player's current role(for particular scene)
	public String getRoleLine() {
		return this.roleLine;
	}
	
	//Method: display()
	//Purpose: Display the player's role and its attributes
	public void display() {
		System.out.println("Role:		" + this.roleRank + " | " + this.roleTitle + " | " + this.roleLine);
	}

}
