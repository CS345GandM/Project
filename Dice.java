//Melissa Gonzalez and Hannah Montague
//Dice Class - Simulates a die by generating a random number 1-6
import java.util.*;
import java.lang.*;
import java.util.Random;

public class Dice{

   private int rollOutcome; //number that was rolled

   //Constructor for Dice
   public Dice(){
      this.rollOutcome = 0; 
   }

   //Method:  getValue()
   //Purpose: generate a random number(1-6)
   //Output:  int
   public int getValue(){
     Random random = new Random();
     rollOutcome = random.nextInt(6) + 1;
     return rollOutcome;
   }
}
