//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.io.*;
import java.lang.*;

public class Deadwood{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numScenes = 22;
  private static ArrayList<Player> allPlayers = new ArrayList();

  private static ArrayList<Cards> allCards = new ArrayList();
  private static ArrayList<Set> allSets = new ArrayList();
  private static ArrayList<Rooms> allRooms = new ArrayList();

  public static void main(String[] args) {

    if(checkArgs(args)){ //loosk that there is a valid number of players
      numPlayers = args[0];
      setDays();
      int daysComplete = 0;
      makePlayers();
      readFiles();////////////////////////////////////////////////////////////////
      while(daysComplete < numDays){
        for(Player p : allPlayers){
          p.newDay();/////////////////////////////////////////////////////////// New Day function in Player. reset role stuff
        }
        //REST BOARD --> NEW CARDS TO setCard
        int sceneTrack = numScenes;
        while(sceneTrack > 1){
          for(Player p : allPlayers){
            if(sceneTrack > 1){
              p.turn();
            }
          }
        }
        daysComplete++;
      }
      //end game
    }
    exit();
  }

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

  //creates players with color names
  private void makePlayers(){
    String[] colors = new String[8];
    colors[0] = "blue";
    colors[1] = "cyan";
    colors[2] = "green";
    colors[3] = "orange";
    colors[4] = "pink";
    colors[5] = "red";
    colors[6] = "violet";
    colors[7] = "yellow";

    for(int x = 0; x < numPlayers; x++){
      String name = colors[x];
      //Player adding = new Player(name);//////////////////////////////////////////////////
      allPlayers.add(new Player(name));
    }
  }

  public void turn(Player x){
    boolean turn = true;

    String who = "Who";
    String where = "Where";
    String rehearse = "Rehearse";
    String end = "End";
    String act = "Act";
    String work = "Work";
    String move = "Move";
    String upgrade = "Upgrade";

    ArrayList<String> completed = new ArrayList();

    while(turn){
      Scanner input = new Scanner(System.in);
      String userInput = input.nextLine();
      String[] userCommands = userInput.split(" ");
      String command = userCommands[0];
      arrayList<String> completed = new arrayList<String>;

      if(command.compareToIgnoreCase(who) == 0){//WHO

        String color = x.getPlayerColor();
        int dollars = x.getPlayerDollars();
        int credits = x.getPlayerCredits();
        if(x.getRoleStatus){
          String role = x.getRoleName();
          System.out.println("%s ($%d, %dcr) working %s", color, dollars, credits, role)
        }else{
          System.out.println("%s ($%d, %dcr)", color, dollars, credits)
        }

      }else if(command.compareToIgnoreCase(where) == 0){//WHERE

        String roomName = x.getPlayerLocation();
        if(/*Card is deleted*/){
          System.out.println("%s wrapped", roomName);
        }else{
          //String sceneName = ////////////////////////////SCENE NAME/CARD NAME
          System.out.println("in %s shooting %s", roomName, sceneName);
        }

      }else if(command.compareToIgnoreCase(end) == 0){

        turn = false;

      }else if(!complete.contains(command)){//command not done yet

        if(command.compareToIgnoreCase(rehearse) == 0){//REHEARSE

           result = x.rehearse();
           if(result == 1){//success
             completed.add(rehearse);
             completed.add(act);
             completed.add(work);
             completed.add(move);
             completed.add(upgrade);
           }

         }else if(command.compareToIgnoreCase(act) == 0){//ACT

           result = x.act();
           if(result == 1 || result == 2){//success
             completed.add(rehearse);
             completed.add(act);
             completed.add(work);
             completed.add(move);
             completed.add(upgrade);
           }

           if(result == 3){ //Success and Wrap///////////////////////////////////////////FIX
             completed.add(command);
             Room place = x.getLocation();
             int cardBudget = x.getBudget();
             wraps(place, cardBudget);
           }

         }else if(command.compareToIgnoreCase(work) == 0){//WORK

           //get desired role
           result = x.setRole(part);
           if(result == 1){
             completed.add(rehearse);
             completed.add(act);
             completed.add(work);
             completed.add(move);
             completed.add(upgrade);
           }

         }else if(command.compareToIgnoreCase(move) == 0){//MOVE

           //get move destination
           result = x.move(dest);
           if(result == 1){//success
             completed.add(command);
           }

         }else if(command.compareToIgnoreCase(upgrade) == 0){//UPGRADE
           if(userCommands.length() == 3){
             String type = userCommands[1];
             int up = Integer.parseInt(userCommands[2]);
             result = x.upgrade(type, up);
             if(result == 1){//success
               completed.add(command);
             }
          }

        }

      }else{
        System.out.println("SOMETHING IS JUST NOT HAPPENING.");
      }
      //turn over
      input.close()
    }
  }

  /*public void wraps(Room finishedRoom, int budget){
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

  }*/

  publid int readFiles(){
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
