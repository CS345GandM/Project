//Hannah Montague and Melissa Gonzalez
//Role Class - A player can take on a role which contains its own attributes
import java.util.*;
import java.lang.*;

public class Role {

	private String roleTitle;  //Name of the role
	private int roleRank;      //Value of the role's rank
	private String roleLine;   //The role's line
	private int roleX;
	private int roleY;
	private int roleW;
	private int roleH;


	//Constructor of Role
	public Role(String roleTitle, int roleRank, String roleLine, int x, int y, int w, int h) {
		this.roleTitle = roleTitle;
		this.roleRank = roleRank;
		this.roleLine = roleLine;
		this.roleX = x;
		this.roleY = y;
		this.roleW = w;
		this.roleH = h;
	}

	public int getRoleX(){
		return roleX;
	}
	public int getRoleY(){
		return roleY;
	}
	public int getRoleW(){
		return roleW;
	}
	public int getRoleH(){
		return roleH;
	}

	//Getter: getRoleTitle
	//Purpose: get the name of the player's role
	public String getRoleTitle() {
		return roleTitle;
	}

	//Getter: getRoleRank
	//Purpose: get the value of the role the player has
	public int getRoleRank() {
		return roleRank;
	}

	//Getter: getRoleLine
	//Purpose: get the line of the player's current role(for particular scene)
	public String getRoleLine() {
		return roleLine;
	}

	//Method: display()
	//Purpose: Display the player's role and its attributes
	public void display() {
		System.out.println("Role:		" + this.roleRank + " | " + this.roleTitle + " | " + this.roleLine);
	}

}
