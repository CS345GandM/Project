//Melissa Gonzalez and Hannah Montague
//Room Class - The gameboard contains different Rooms
import java.util.*;
import java.lang.*;

public class Rooms{
  //there are a max of 4 rooms per board section
   private String one;   //name of room one
   private String two;   //name of room two
   private String three; //name of room three
   private String four;  //name of room four
   private String name;  //name of room

   //Constuctor for Room
   public Rooms(String one, String two, String three, String four, String name){
      this.one = one;
      this.two = two;
      this.three = three;
      this.four = four;
      this.name = name;
   }

   //Want to take a look at rooms to decide whether they are adj
   //Can only move to adj room

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
   public String getRoomName(){
     return name;
   }
}
