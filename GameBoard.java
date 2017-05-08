//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.io.*;
import java.lang.*;

public class Board{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numPlayers = 0;
  private static int totalShots = 22;
  private static int remainingShots = 22;
  private static String[] players = new String[8];
  //private Scanner setScanner = readFile("Set.txt");
  //private ArrayList<Set> sets = new ArrayList<>();

  public static void main(String[] args)throws FileNotFoundException{
    //check args
    if(handleArgs(args)){
      makePlayerArray();
      numplayers = Integer.parseInt(args[0]);
      //makeSets(); //feed in file/////////////////////////////////////////////////////////////
      //makeRooms();//read in file///////////////////////////////////////////////////////////////
      //makeCards();//read in file//////////////////////////////////////////////////////////
      for(int x = 0; x < numPlayers; x++){
        String name = players[x];
        name = new Player(name);
      }
      setDays(numPlayers);
      int day = 1;
      while(day <= numDays){
        for(int x = 0; x < numPlayers; x++){
          x.newDay();
        }
        //associateCardToSet();//////////////////////////////////////////////////////////////////////////////////
        remainingShots = totalShots;
        while(remainingShots > 1){
          for(int x = 0; x < numPlayers; x++){
            if(remainingShots <= 1){
              //Day over!
              break; //only breaks out of for loop
            }
            turn(x);
          }
        }
        day++;
      }
      //Game over
    }else{
      System.exit(1);
    }
  }

  private static boolean handleArgs(String[] args){
    if(args.length != 1){
      System.out.println("Incorrect number of args.")
      return false;
    }
    return true;
  }

  private static void makePlayerArray(){
    players[0] = "Blue";
    players[1] = "Cyan";
    players[2] = "Green";
    players[3] = "Orange";
    players[4] = "Pink";
    players[5] = "Red";
    players[6] = "Violet";
    players[7] = "Yellow";
  }


  public void turn(Player x){
    Scanner input = new Scanner();
    String userInput = input.nextLine();
    String[] userCommands = userInput.split(" ");
    //// split up string////////////////////////////////////////////////////////////////////////////////////////////////////
    String who = "Who";
    String Where = "Where";
    String rehease = "Rehease";
    String end = "End";
    String act = "Act";
    String work = "Work";
    String move = "Move";

    int result;
    boolean anotherAction = true;
    String lastMove = null;

    //INITIAL INPUT ANALYSIS
    if(userInput.compareToIgnoreCase(who) == 0){//WHO
      System.out.println("Player.Name(Player.Money(),Player.Credits() working" + working location);
    }else if (userInput.compareToIgnoreCase(where) == 0){//WHERE
      System.out.println("active scenes and current players room");
    }else if(userInput.compareToIgnoreCase(rehearse) == 0){//REHEARSE
      result = x.rehearse();
      if(result == 0){//can't already has 6

      }
      if(result == 1){//success
        anotherAction = false;
      }
    }else if(userInput.compareToIgnoreCase(act) == 0){//ACT
      result = x.act();
      if(result == 0){//doesn't have a role

      }
      if(result == 1){//success
        anotherAction = false;
      }
      if(result == 2){//fail
        anotherAction = false;
      }if(result == 3){ //Success and Wrap
        anotherAction = false;
        Room place = x.getLocation();
        int cardBudget = x.getBudget();
        wraps(place, cardBudget);
      }
    }else if(userInput.compareToIgnoreCase(work) == 0){//WORK
      //get desired role
      result = x.setRole(part);
      if(result == 0){//failed

      }
      if(result == 1){//success

      }
    }else if(userInput.compareToIgnoreCase(move) == 0){//MOVE
      //get move destination
      result = x.move(dest);
      if(result == 0){//fail

      }
      if(result == 1){//success
        lastMove = "Move";
      }
    }else if(userInput.compareToIgnoreCase(upgrade) == 0){//UPGRADE
      //$
      //get upgrade desire
      result = x.upgradeDollars(up);
      //cr
      //get upgrade desire
      result = x.upgradeCR(up);
      if(result == 0){//fail

      }
      if(result == 1){//success
        lastMove = "Upgrade";
      }
    }else if(userInput.compareToIgnoreCase(end) == 0){
      //player turn over
    }

    while(userInput.compareToIgnoreCase(end) != 0){
      //scan in more
      //move if != last move
      //upgrade if != last move
      //work if != last move
      //who
      //where
      //otherwise can't
    }

  }

  public void wraps(Room finishedRoom, int budget){
    int[] diceRolls = new int[budget];
    for(int x = 0; x < budget; x++){
      diceRolls[x] = Dice.getValue();
    }
    diceRolls.sort(diceRolls);

    //get number of on card roles
    int numRoles = XXXX;/////////////////////////////////////////////////////
    int[] cardWins = new int[numRoles];//lowest to highest
    for(int x = 0; x < numRoles x++){// initialize all spots to 0
      cardWins[x] = 0;
    }

    int spot = 0;//dice rolls
    int track = 0;//roles

    while(spot < budget){
      if(int track < numRoles){
        cardWins[track] = cardWins[track] + diceRolls[spot];
        track++;
        spot++;
      }else{
        track = 0;
      }
    }
    boolean onCard = false;
    for(int x = 0; x < numPlayers; x++){//look for on the card player
      if(finishedRoom.compareToIgnoreCase(x.getLocation)){
        if(x.role == 2){//on the card
          onCard = true;
          //determine "role" on card
          //increment == cardWins[role]
          x.wrapped(increment);
        }
      }
    }

    if(onCard == true){//look for off the card player if on card
      for(int x = 0; x < numPlayers; x++){
        if(finishedRoom.compareToIgnoreCase(x.getLocation)){
          if(x.role == 1){//off the card
            //increment == role value
            x.wrapped(increment);
          }
        }
      }
    }

  }

/*
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
  */

//according to game rules
  private void setDays(int numPlayers) {
      if (numPlayers == 2 || numPlayers == 3) {
          numDays = 3;
      } else {
          numDays = 4;
      }
  }

//displays final informatiom for a player
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

  //calculates score for a player
  public int calcScore(Player player) {
    return player.getMoney() + player.getCredit() + (player.getRank() * 5);
  }

}
