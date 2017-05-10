//Melissa Gonzalez and Hannah Montague
/*Name Budget NumRoles roleValue . . */
import java.util.*;
import java.lang.*;

public class Cards{

   private String name;
   private String description;
   private int sceneNum;
   private int budget;
   private ArrayList<Role> onCardRoles = new Role();

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

   public int[] getRoleRanks(){
     int[] ranks = new int[onCardRoles.size()];
     int track = 0;
     for(Role r : onCardRoles){
       ranks[track] = r.getRoleRank();
       track++;
     }
     Arrays.sort(ranks);
     return ranks;
   }



}
