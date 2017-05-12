//Melissa Gonzalez and Hannah Montague
/*Name Budget NumRoles roleValue . . */
import java.util.*;
import java.lang.*;

public class Cards{

   private String name;
   private String description;
   private int sceneNum;
   private int budget;
   private ArrayList<Role> onCardRoles;

   public Cards(String n, int b, int sN, ArrayList<Role> r, String d){
      this.name = n;
      this.description = d;
      this.budget = b;
      this.sceneNum = sN;
      this.onCardRoles = r;
   }

   private void makeCard(){
      //read in text file
   }
   public int getNumRoles(){
     int len = onCardRoles.size();
     return len;
   }

   public String getName(){
      return name;
   }

   public String getQuote(){
     return description;
   }

   public int getBudget(){
      return budget;
   }

   public boolean isARole(String roleName){
     for(Role r : onCardRoles){
       String currRole = r.getRoleTitle();
       if(roleName.compareToIgnoreCase(currRole) == 0){
         return true;
       }
     }
     return false;
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
