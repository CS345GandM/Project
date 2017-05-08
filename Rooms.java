//Melissa Gonzalez and Hannah Montague
/*Name
number of connections
connection names
.
.
*/

import java.util.*;
import java.lang.*;

///NOT SETS!!!!!!!!
public class Rooms{
   private Set one;
   private Set two;
   private Set three;
   private Set four;
   private Set name;

   public Rooms(){
   }

   private Rooms(String up, String down, String left, String right, String name){
      this.one = null;
      this.two = null;
      this.three = null;
      this.four = null;
      this.name = name;
   }

   public void successfulAct(){
     name.removeCounters();
   }
   public int remainingShots(){
     int x = name.getCounts();
     return x;
   }
   public Set peekOne(){
     return one.getName();
   }
   public Set peekTwo(){
     return two.getName();
   }
   public Set peekThree(){
     return three.getName();
   }
   public Set peekFour(){
     return four.getName();
   }
}
