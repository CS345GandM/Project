//Melissa Gonzalez and Hannah Montague
//Set Class - Every Room has a set

import java.util.*;
import java.lang.*;

public class Set{
   private String name;        //set name
   private int shotCounts;    //number of set shot counters
   private Cards cardName;   //A set contains a card
   private boolean hasACard; //A set has an associated card
   private ArrayList<Role> extraRoles = new ArrayList<Role>();   //ArrayList contains extraroles that are in a set
   private int cardX;
   private int cardY;
   private int cardW;
   private int cardH;

   //Constuctor for a set
   public Set(String name, int takes, ArrayList<Role> extraRoles, int x, int y, int w, int h){
      this.name = name;
      this.shotCounts = takes;
      this.extraRoles = extraRoles;
      this.hasACard = false;
      this.cardName = null;
      this.cardX = x;
      this.cardY = y;
      this.cardW = w;
      this.cardH = h;
   }

   public int getCardX(){
     return cardX;
   }
   public int getCardY(){
     return cardY;
   }
   public int getCardH(){
     return cardH;
   }
   public int getCardW(){
     return cardW;
   }


   //Setter: setShotCounts
   public void setShotCounts(int i){
     shotCounts = i;
   }

   //Getter:  getCounts
   //Purpose: get the number of shot counters of a set
   public int getCounts(){
     return shotCounts;
   }

   //Setter: setCard
   public void setCard(Cards n){
     cardName = n;
     hasACard = true;
   }

   //Getter:  getCardName
   //Purpose: get the name of the card for a set
   public String getCardName(){
     //String needName = cardName.getName();
     return cardName.getName();
   }

   //Getter:  getNumRoles
   //Purpose: get number of extra roles of a set
   public int getNumRoles(){
     int numRoles = extraRoles.size();
     return numRoles;
   }

   //Getter:  getName of a set
   //Purpose: get the name of a set
   public String getName(){
      return name;
   }

   //Getter: setHasACard
   //Purpose: retrieve the boolean that corresponds to if a set as an associated card
   //Return: boolean
   public boolean setHasACard(){
     return hasACard;
   }

   //Method:  removeCounters()
   //Purpose: remove a shot counter upon successful act
   public void removeCounters(){
      shotCounts = shotCounts - 1;
   }

   //Method:  removeCard()
   //Purpose: Once a scene on the set is done, remove the card
   //Return:  cardName set to null
   public void removeCard(){
     cardName = null;
     hasACard = false;
   }
   //Method:  getBudget()
   //Purpose: retrieve the budget that corresponds to the card on the set
   //Return:  int
   public int getBudget(){
     int budget = cardName.getBudget();
     return budget;
   }
}
