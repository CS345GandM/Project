//Melissa Gonzalez and Hannah Montague

import java.util.*;

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
   private Set name;

   private Player(int rank, String id, boolean role, int credits, int dollars, int rehersalCredits, int roleValue, int roleBudget, String location){
      this.rank = 1;
      this.id = null; //input from user
      this.role = false;
      this.credits = 0;
      this.dollars = 0;
      this.rehersalCredits = 0;
      this.roleValue = 0;
      this.roleBudget = 0;
      this.location = name;
   }

   public Player(){

   }

   private void makePlayer(){
     //prompt for id
     //set id
   }

   public void act(){
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
       /////////////////////////////////////decrement shot counters
       /// WRAP
     }else{
       //LOSE
       if(role == 1){//of the card
         dollars++;
       }
     }
   }

   public void rehearse(){
     rehersalCredits = rehersalCredits + 1;
   }

   public void move(){
   }

   public String getLocation(){
      return location;
   }

   public void setRank(int x){
   }

   public int getRank(){
      return rank;
   }

   public void setRole(int x){
   }

   public boolean getRole(){
      return role;
   }

   public void upgrade(){
   }
}
