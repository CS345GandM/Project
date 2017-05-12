//Melissa Gonzalez and Hannah Montague
/*Name
ShotCounts NumRoles roleValues . . */

import java.util.*;
import java.lang.*;

public class Set{
   private String name;
   private int shotCounts;
   private ArrayList<Role> extraRoles = new ArrayList<Role>();
   private Cards cardName;

   public Set(String name, int takes, ArrayList<Role> extraRoles){
      this.name = name;
      this.shotCounts = takes;
      this.extraRoles = extraRoles;
      this.cardName = null;
   }

   public void setShotCounts(int i){
     this.shotCounts = i;
   }

   public void setCard(Cards n){
     this.cardName = n;
   }

   //getter for card name
   public String getCardName(){
     return cardName.getName();
   }

   //getter for number of roles
   public int getNumRoles(){
     return extraRoles.size();
   }

   public void removeCounters(){
      shotCounts = shotCounts - 1;
   }

   public int getCounts(){
     return shotCounts;
   }

   public String getName(){
      return name;
   }

   public void removeCard(){
     cardName = null;
   }

   public int getBudget(){
     int budget = cardName.getBudget();
     return budget;
   }
}
