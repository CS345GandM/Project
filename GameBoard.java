//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.io.*;
import java.lang.*;

public class GameBoard{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numCards = 0;
  private static int numScenes = 0;



public static void main(String[] args) {

  Scanner input = new Scanner(System.in);
  String userInput = input.nextLine();
  String who = "who";
  String Where = "Where";
  String rehease = "rehease";
  String end = "end";
  String act = "act";
  String work = "work";

  if (userInput.equals("work")){
    System.out.println("Slected Work");
  }
/*
  while (userInput.compareToIgnoreCase(end) != 0)
=======
  private static int numScenes = 22;


  public static void main(String[] args) {

    if(checkArgs(args)){
      numPlayers = args[0];
      readIn.callRead();
      setDays();
      int daysComplete = 0;
      //make players
      while(daysComplete < numDays){
        //REST PLAYERS
        //REST BOARD --> NEW CARDS TO setCard
        int sceneTrack = numScenes;
        while(sceneTrack > 1){
          //go through players
            //each player takes their turn
        }
        daysComplete++;
      }
      //END GAME
      Scanner input = new Scanner(System.in);
      String userInput = input.nextLine();
      String who = "who";
      String Where = "Where";
      String rehease = "rehease";
      String end = "end";
      String act = "act";
      String work = "work";

    /*
      while (userInput.compareToIgnoreCase(end) != 0)

        if(userInput.compareToIgnoreCase(who) == 0){
          System.out.println("Player.Name(Player.Money(),Player.Credits() working" + working location);
>>>>>>> 507b72805922c80eba54369702990d26fac3b95d

        }else if (userInput.compareToIgnoreCase(where) == 0){
          System.out.println("active scenes and current players room");

        }else if(userInput.compareToIgnoreCase(rehease) == 0){

        }else if(userInput.compareToIgnoreCase(act) == 0){

        }else if(userInput.compareToIgnoreCase() == 0){
      }*/

    }
  }


=======
  private boolean checkArgs(String[] args){
    if(args.length() == 1){
        String players = args[0];
        int num = Integer.parseInt(players);
        if(num > 1 && num < 9){
          return true;
        }
    }
    System.out.println("Invalid or incorrect number of arguments");
    return false;
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

  public String getRoomName(String name){
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
