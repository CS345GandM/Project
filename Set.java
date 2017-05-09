//Melissa Gonzalez and Hannah Montague
/*Name
ShotCounts NumRoles roleValues . . */

import java.util.*;
import java.lang.*;

public class Set{
   private String name;
   private int takes;
   private Role[] extraRoles = new Role[4];
   private Card cardName;

   public Set(String name, int takes, Card[] extraRoles){
      this.name = name;
      this.shotCounts = takes;
      this.extraRoles = extraRoles;
      this.cardName = null;
   }

   public void setShotCounts(int i){
     this.shotCounts = i;
   }


   public void setCard(Card n){
     this.cardName = n.getName();
   }

     //read in text file through gameboard


   public void removeCounters(){
      shotCounts = shotCounts - 1;
   }

   public int getCounts(){
     return shotCounts;
   }

   public String getName(){
      return name;
   }

   public void assignRole(){
   }

   public void associateCard(){
   }
}
