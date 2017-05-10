//Melissa Gonzalez and Hannah Montague

import java.util.*;
import java.io.*;
import java.lang.*;

public class Deadwood{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numScenes = 22;
  private static int sceneTrack = 0;
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
        associateCards();
        sceneTrack = numScenes;
        while(sceneTrack > 1){
          for(Player p : allPlayers){
            if(sceneTrack > 1){
              p.turn();
            }
            //remove last card
            String room = p.getPlayerLocation();
            Set currSet;
            for(Set s : allSets){
              String name = s.getName();
              if(name.compareToIgnoreCase(room) == 0){
                currSet = s;
              }
            }
            String cardName = currSet.getCardName();
            Cards currCard;
            for(Cards c : allCards){
              String name = c.getName();
              if(name.compareToIgnoreCase(cardName) == 0){
                currCard = c;
              }
            }
            boolean done = allCards.remove(currCard);

            currSet.removeCard();

          }
        }
        daysComplete++;
      }
      //end game. print scores
      for(Player p : allPlayers){
        p.finalScore();
      }
    }
    exit();
  }

  //checks for right number of args and right arg input
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
           if(result == 1){//success
             sceneTrack--;
             completed.add(rehearse);
             completed.add(act);
             completed.add(work);
             completed.add(move);
             completed.add(upgrade);
           }

           Set currSet;
           for(Set s : allSets){
             String name = s.getName();
             if(name.compareToIgnoreCase(room) == 0){
               currSet = s;
             }
           }

           int remaining = currSet.getShotCounts()
           if(remaining == 0){//WRAP SCENE
             String place = x.getPlayerLocation();
             int cardBudget = x.getCardBudget();
             wraps(place, cardBudget, currSet);
           }

         }else if(command.compareToIgnoreCase(work) == 0){//WORK
           //check that role isn't taken
           int len = userCommands.size();
           String[] subString = new String[len];
           len--;
           substring = Arrays.copyOfRange(userCommands, 1, len);
           String desiredRole = String.join(" ", subString);

           boolean goodToGo = true;
           for(Player p : allPlayers){
             if(p.getRoleStatus){//has a role
               String playerRole = p.getRoleName;
               if(playerRole.compareToIgnoreCase(desiredRole) == 0){ // role taken
                 goodToGo = false;
               }
             }
           }

           Role currRole
           for(Role r : allRoles){
             String name = r.getRoleTitle();
             if(name.compareToIgnoreCase(name) == 0){
               currRole = r;
             }
           }

           if(goodToGo){//role isn't taken
             //get desired role
             result = x.Work(currRole);
             if(result == 1){
               completed.add(rehearse);
               completed.add(act);
               completed.add(work);
               completed.add(move);
               completed.add(upgrade);
             }
           }else{
              result = 0;
           }


         }else if(command.compareToIgnoreCase(move) == 0){//MOVE
           //find destination
           int len = userCommands.size();
           String[] subString = new String[len];
           len--;
           substring = Arrays.copyOfRange(userCommands, 1, len);
           String desiredDest = String.join(" ", subString); //desired location
           String currRoom = x.getRoom();// current location

           if(currRoom.peekOne().compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredDest);
             if(result == 1){//success
               completed.add(command);
             }
           }

           if(currRoom.peekTwo().compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredDest);
             if(result == 1){//success
               completed.add(command);
             }
           }

           if(currRoom.peekThree().compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredDest);
             if(result == 1){//success
               completed.add(command);
             }
           }

           if(currRoom.peekFour().compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredDest);
             if(result == 1){//success
               completed.add(command);
             }
           }

           //not a valid move
           result = 0;

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

  public void wraps(String room, int budget, Set currSet){
    int[] diceRolls = new int[budget];
    Dice newDice = new Dice();
    for(int x = 0; x < budget; x++){
      diceRolls[x] = newDice.getValue();
    }
    //decreasing.
    Arrays.sort(diceRolls, Collections.reverseOrder());

    //based on current set and current card
    Cards currCard;
    for(Cards c : allCards){
      String name = c.getName();
      String setsCard = currSet.getName;
      if(name.compareToIgnoreCase(setsCard) == 0){
        currCard = c;
      }
    }

    //get number of on card roles
    int numRoles = c.getNumRoles();
    boolean onCard = false;

    int spot = 0;//dice rolls
    int track = numRoles - 1;//roles starting at highest ranked
    int[] winnings = new int[numRoles];
    //calculating on card winnings
    while(spot < budget){
      //highest to lowest
      if(int track > 0){
        cardWins[track] = cardWins[track] + diceRolls[spot];
        track--;
        spot++;
      }else{
        track = numRoles - 1;
      }
    }

    int[] rankOrder = currCard.getRoleRanks();

    for(Player p : allPlayers){ //on card roles
      String cardRole = p.getonOrOffCard();
      if(cardRole.compareToIgnoreCase("On") == 0){
        onCard = true;
        int roleRank = p.getRoleRank();
        int spot = 0;
        while(roleRank != rankOrder[spot]){
          spot++;
        }
        p.wraps(winnings[spot]);
      }
    }

    if(onCard){//there is an on the card player
      for(Player p : allPlayers){ //on card roles
        String cardRole = p.getonOrOffCard();
        if(cardRole.compareToIgnoreCase("off") == 0){
          int roleRank = p.getRoleRank();
          p.wraps(roleRank);
        }
      }
    }

    //removing card
    currSet.removeCard();
    boolean done = allCards.remove(currCard);
  }

  public int readFiles(){
    ////////////////////////////////////////////////////////////////////////////////////////////////
  }

  public int finalScore(Player player) {
    int score = calcScore(player);
    System.out.println("--------------------------------------");
    System.out.println("Name:             " + player.getPlayerColor());
    System.out.println("Rank:             " + player.getPlayerRank());
    System.out.println("Credit:           " + player.getPlayerCredits());
    System.out.println("Money:            " + player.getPlayerDollars());
    System.out.println("Total Score:      " + score);
    System.out.println("--------------------------------------");
    return score;
  }

  public int calcScore(Player player) {
    return player.getPlayerDollars() + player.getPlayerCredits() + (player.getPlayerRank() * 5);
  }

  public void associateCards(){
    Collections.shuffle(allCards); //randomizes cards
    int i = 0;
    for(Set s : allSets){
      s.setCard(allCards.get(i));
      i++
    }
  }

}
