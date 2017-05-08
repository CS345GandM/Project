//Melissa Gonzalez and Hannah Montague
/*Name
ShotCounts NumRoles roleValues . . */

import java.util.*;

public class Set{
   private String name;
   private int shotCounts;
   private int[] extraRoles;
   private Card cardName;

   public Set(String name, int shotCounts, int[] extraRoles, String cardName){
      this.name = null;
      this.shotCounts = 0;
      this.extraRoles = extraRoles;
      this.cardName = null;
   }

   public void setName(String s){
     this.name = name;
   }

   public void setShotCounts(int i){
     this.shotCounts = i;
   }

   public void setExtraRoles(int 1, int 2, int 3, int 4){
     ////////////////////////////////////////////////////////////////////////////////////////
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
