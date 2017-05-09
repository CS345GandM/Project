//Melissa Gonzalez and Hannah Montague
/*Name Budget NumRoles roleValue . . */
import java.util.*;
public class Cards{

   private String name;
   private int budget;
   private String line;
   private ArrayList<Role> roles;

   private Cards(ArrayList<Role> roles, int budget, String name, String line){
      this.name = name;
      this.budget = budget;
      this.roles = roles;
      this.line = line;
   }
   private void makeCard(){
      //read in text file
   }


 	public Role getRoleName(String role) {
 		Role name = null;

 		for(Role current : role) {
 			if(current.getRoleTitle().equals(role))
 				name = current;
 		}

 		return name;
 	}
   public String getName(){
      return name;
   }

   public String getQuote(){
     return line;
   }

   public int getBudget(){
      return budget;
   }

   public void turnCard(){
      return;
   }



}
