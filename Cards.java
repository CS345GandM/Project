//Melissa Gonzalez and Hannah Montague
/*Name Budget NumRoles roleValue . . */
import java.util.*;
import java.lang.*;

public class Cards{

   private String name;
   private String description;
   private int sceneNum;
   private int budget;
   private Role[] roles = new Role[4];

   public Cards(String n, String d, int b, int sN, Role[] r){
      this.name = n;
      this.description = d;
      this.budget = b;
      this.sceneNum = sN;
      this.roles = r;
   }

   private void makeCard(){
      //read in text file
   }

/*
 	public Role getRoleName(String role) {
 		Role name = null;

 		for(Role current : role) {
 			if(current.getRoleTitle().equals(role))
 				name = current;
 		}

 		return name;
 	}
  */
   public String getName(){
      return name;
   }

   public String getQuote(){
     return description;
   }

   public int getBudget(){
      return budget;
   }

   public void turnCard(){
      return;
   }



}
