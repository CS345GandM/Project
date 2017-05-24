//Melissa Gonzalez and Hannah Montague
/*Name Budget NumRoles roleValue . . */
import java.util.*;
import java.lang.*;

public class Cards{

   private String name;         //Name of Card
   private String description;  //Card Description
   private int    sceneNum;     //Card contains a scene number
   private int    budget;       //Card has its own budget
   private ArrayList<Role> onCardRoles = new ArrayList<Role>();
   private String pngName;

   //Card constructor
   public Cards(String n, int b, int sN, ArrayList<Role> r, String d, String png){
      this.name = n;
      this.description = d;
      this.budget = b;
      this.sceneNum = sN;
      this.onCardRoles = r;
      this.pngName = png;
   }

   private void makeCard(){
      //read in text file
   }

   //Getter:
   //Purpose:
   public String getImageName(){
     return pngName;
   }

   //Getter:  getNumRoles
   //Purpose: Get the number of roles on a card
   public int getNumRoles(){
     int len = onCardRoles.size();
     return len;
   }

   //Getter:  getName
   //Purpose: get the name of a card
   public String getName(){
      return name;
   }
   //Getter:  getQuote
   //Purpose: get the description of a card
   public String getQuote(){
     return description;
   }

   //Getter:  getBudget
   //Purpose: get the card's budget
   public int getBudget(){
      return budget;
   }

   //Getter:  getSceneNumber
   //Purpose: get the card's budget
   public int getSceneNumber(){
      return sceneNum;
   }

   //Method:  isARole()
   //Purpose: check if the player's role is contained in the onCardRoles array
   //Input:   String - name of a role
   //Output:  boolean
   public boolean isARole(String roleName){
     for(Role r : onCardRoles){
       String currRole = r.getRoleTitle();
       if(roleName.compareToIgnoreCase(currRole) == 0){
         return true;
       }
     }
     return false;
   }

   //Method:  getRoleRanks()
   //Purpose: get the values of all the roles
   //Input:   none
   //Output:  an array of integers sorted
   public int getRoleRanks(int spot){
     Role r = onCardRoles.get(spot);
     int rank = r.getRoleRank();
     return rank;
   }
}
