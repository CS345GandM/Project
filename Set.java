//Melissa Gonzalez and Hannah Montague
//Set Class - Every Room has a set

import java.util.*;
import java.lang.*;

public class Set{
   private String name;        //set name
   private int shotCounts;    //number of set shot counters
   private Cards cardName;   //A set contains a card
   private ArrayList<Role> extraRoles = new ArrayList<Role>();   //ArrayList contains extraroles that are in a set

   //Constuctor for a set
   public Set(String name, int takes, ArrayList<Role> extraRoles){
      this.name = name;
      this.shotCounts = takes;
      this.extraRoles = extraRoles;
      this.cardName = null;
   }

   //Setter: setShotCounts
   public void setShotCounts(int i){
     this.shotCounts = i;
   }

   //Getter: getCounts
   public int getCounts(){
     return shotCounts;
   }

   //Setter: setCard
   public void setCard(Cards n){
     this.cardName = n;
   }

   //Getter: getCardName
   public String getCardName(){
     return cardName.getName();
   }

   //Getter: getNumRoles
   public int getNumRoles(){
     return extraRoles.size();
   }

   //Getter: getName of a set
   public String getName(){
      return name;
   }

   //Method: removeCounters()
   //Purpose: keep track of the takes left for particular set
   public void removeCounters(){
      shotCounts = shotCounts - 1;
   }

   //Method: removeCard()
   //Purpose: Once a scene on the set is done, remove the card
   //Return: cardName set to null
   public void removeCard(){
     cardName = null;
   }
   //Method: getBudget()
   //Purpose: retrieve the budget that corresponds to the card on the set
   //Return: int
   public int getBudget(){
     int budget = cardName.getBudget();
     return budget;
   }
}
