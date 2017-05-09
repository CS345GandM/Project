//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.io.*;

public class Board{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numCards = 0;
  private static int numScenes = 0;


  // Constructor takes one input : the number of players
  GameBoard(int numPlayers) {
      this.numPlayers = numPlayers;
      this.numScenes = 10;
  }

public static void main(String[] args) {

  Scanner input = new Scanner();
  String userInput = input.nextLine();
  String who = "who";
  String Where = "Where";
  String rehease = "rehease";
  String end = "end";
  String act = "act";
  String work = "work";

  while (userInput.compareToIgnoreCase(end) != 0)

    if(userInput.compareToIgnoreCase(who) == 0){
      System.out.println("Player.Name(Player.Money(),Player.Credits() working" + working location);

    }else if (userInput.compareToIgnoreCase(where) == 0){
      System.out.println("active scenes and current players room");

    }else if(userInput.compareToIgnoreCase(rehease) == 0){

    }else if(userInput.compareToIgnoreCase(act) == 0){

    }else if(userInput.compareToIgnoreCase() == 0){
  }


  private Scanner readFile(String fileName) {
      Scanner scan = null;
      try {
          scan = new Scanner(new File(fileName));
      } catch (FileNotFoundException e) {
          System.out.println("Error: unable to open file: " + fileName);
          System.exit(1);
      }
      return scan;
  }

//according to game rules
  private void setDays() {
      if (numPlayers == 2 || numPlayers == 3) {
          numDays = 3;
      } else {
          numDays = 4;
      }
  }

  public void checkScenes(){
    int sceneCount = 0;
    for(Rooms current: this.rooms){
      if(!current.isWrapped()){
        sceneCount++;
      }
      this.numScenes = sceneCount;
    }
  }


  public getRoomName(String name){
    Room roomName = null;

    for(Room current : room) {
      if(room.getName().equals(name)){
         roomName = current;
      }
    }
 return roomName;
 } 

  public int finalScore(Player player) {
    int score = calcScore(player);
    System.out.println("--------------------------------------");
    System.out.println("Name:             " + player.getName());
    System.out.println("Rank:             " + player.getRank());
    System.out.println("Credit:           " + player.getCredit());
    System.out.println("Money:            " + player.getMoney());
    System.out.println("Total Score:      " + score);
    System.out.println("--------------------------------------");
    return score;
  }

  public int calcScore(Player player) {
    return player.getMoney() + player.getCredit() + (player.getRank() * 5);
  }
}
