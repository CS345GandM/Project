//Melissa Gonzalez and Hannah Montague


import java.util.*;
import java.lang.*;

public class Rooms{
   private String one;
   private String two;
   private String three;
   private String four;
   private String name;

<<<<<<< HEAD
   public  Rooms(String one, String two, String three, String four, String name){
=======

   public Rooms(String one, String two, String three, String four, String name){
>>>>>>> 8d9a8d81c361c5403ca41260b4d05a7c1c567f69
      this.one = one;
      this.two = two;
      this.three = three;
      this.four = four;
      this.name = name;
   }

  /* public void successfulAct(){
     name.removeCounters();
   }
   public int remainingShots(){
     int x = name.getCounts();
     return x;
   }*/
   public String peekOne(){
     return one;
   }
   public String peekTwo(){
     return two;
   }
   public String peekThree(){
     return three;
   }
   public String peekFour(){
     return four;
   }
   public String peekName(){
     return name;
   }
}
