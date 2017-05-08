//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.util.Random;

public class Dice{

   private int rollOutcome;

   public Dice(){
      this.rollOutcome = 0;
   }

   public int getValue(){
     Random random = new Random();
     rollOutcome = random.nextInt(6) + 1;
     return rollOutcome;
   }
}
