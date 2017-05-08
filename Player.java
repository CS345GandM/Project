//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.lang.*;

public class Player{

      //attributes
   private int rank;
   private String id;
   private int role;// 0 = none, 1 = off card, 2 = on card
   private int credits;
   private int dollars;
   private int rehersalCredits;
   private int roleValue;
   private int roleBudget;
   private Room location;

   private Player( String id){
      this.rank = 1;
      this.id = null; //input from user
      this.role = false;
      this.credits = 0;
      this.dollars = 0;
      this.rehersalCredits = 0;
      this.roleValue = 0;
      this.roleBudget = 0;
      this.location = null;
   }

   //reset player infor for a new day
   public void newDay(){
     role = 0;
     rehersalCredits = 0;
     roleValue = 0;
     roleBudget = 0;
     location = "Trailer";
   }

   //rest player info for wrapped set
   public void wrapped(int money){
     role = 0;
     dollars = dollars + money;
     rehersalCredits = 0;
     roleValue = 0;
     roleBudget = 0;
   }

   public int act(){
     if(role != 0){ // can act
       int roll = Dice.getValue();
       roll = roll + rehersalCredits;

       if(roll > roleValue){
         //WIN
         if(role == 1){
           credits++;
           dollars++;
         }else{//role == 2
           credits = credits + 2;
         }
         location.successfulAct();
         if(location.remainingShots() == 0){
           return 3; //WRAP
         }
         return 1;
       }else{
         if(role == 1){//of the card
           dollars++;
         }
         return 2;
       }
     }
     return 0; //doesn't have a role to act on
   }

   public int rehearse(){
     if(rehersalCredits < 6){
       rehersalCredits = rehersalCredits + 1;
       return 1;
     }
     return 0; //already has 6 credits
   }

   public int move(Room newLocation){
     if(location.compareToIgnoreCase(location.peekOne()) == 0){
       location = newLocation;
       return 1; //success
     }else if(location.compareToIgnoreCase(location.peekTwo()) == 0){
       location = newLocation;
       return 1; //success
     }else if(location.compareToIgnoreCase(location.peekThree()) == 0){
       location = newLocation;
       return 1; //success
     }else if(location.compareToIgnoreCase(location.peekOne()) == 0){
       location = newLocation;
       return 1; //success
     }
     return 0; //not a valid move
   }

   public Room getLocation(){
      return location;
   }

   public int getBudget(){
     return roleBudget;
   }

   public int getRank(){
      return rank;
   }

   public void setRole(String part){
     //can't if there is no shot counters
     //can't if already have role
     //can't if role value is greater than rank
   }

   public int getRole(){
     //0 == no role
     //1 == off card
     //2 == on card
      return role;
   }

   public void upgradeCR(int increaseTo){///////////////////////////////////////////find cr for desired rank
     if(location.compareToIgnoreCase("Casting Office") == 0){
       if(rank < 6){
         if(credits >= cr){
           rank = increaseTo;
           credits = credits - cr;
           return 1;
         }
         return 0; //not enough credits
       }
       return 0; //already highest Rank
     }
     return 0; //not in Casting Office
   }

   public void upgradeDollars(int increaseTo){//////////////////////////////////////find dollars for desired rank
     if(location.compareToIgnoreCase("Casting Office") == 0){
       if(rank < 6){
         if(dollars >= cost){
           rank = increaseTo;
           dollars = dollars - cost;
           return 1;
         }
         return 0; //not enough money
       }
       return 0; //already highest Rank
     }
     return 0; //not in Casting Office
   }
}
