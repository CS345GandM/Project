//Melissa Gonzalez and Hannah Montague
//CSCI 345 - Moushumi Sharmin
//Due: May 15, 2017

import java.util.*;
import java.io.*;
import java.lang.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Deadwood{
  private static int numPlayers = 0;
  private static int numDays = 0;
  private static int numScenes = 22;
  private static int sceneTrack = 0;
  private static ArrayList<Player> allPlayers = new ArrayList<Player>();

  private static ArrayList<Cards> allCards = new ArrayList<Cards>();
  private static ArrayList<Set> allSets = new ArrayList<Set>();
  private static ArrayList<Rooms> allRooms = new ArrayList<Rooms>();
  private static ArrayList<Role> allRoles = new ArrayList<Role>();

  public static void main(String[] args) {

    if(checkArgs(args)){ //check that there is a valid number of players
      numPlayers = Integer.parseInt(args[0]);
      System.out.println(numPlayers);
      setDays();
      int daysComplete = 0;
      makePlayers();
      cards();
      board();
      Rooms newDayTrailer = null;
      for(Rooms r : allRooms){
        String currRoom = r.getRoomName();
        if(currRoom.compareToIgnoreCase("Trailer") == 0){
          newDayTrailer = r;
        }
      }
      Scanner input = new Scanner(System.in);
      while(daysComplete < numDays){
        for(Player p : allPlayers){
          p.newDay(newDayTrailer);
        }
        associateCards();
        sceneTrack = numScenes;
        Player currPlayer = null;
        while(sceneTrack > 1){
          for(Player p : allPlayers){
            currPlayer = p;
            if(sceneTrack > 1){
              turn(currPlayer, input);
            }
          }
        }

        //remove last card
        String room = currPlayer.getPlayerLocation();
        Set currSet = null;
        for(Set s : allSets){
          String name = s.getName();
          if(name.compareToIgnoreCase(room) == 0){
            currSet = s;
          }
        }
        String cardName = currSet.getCardName();
        Cards currCard = null;
        for(Cards c : allCards){
          String name = c.getName();
          if(name.compareToIgnoreCase(cardName) == 0){
            currCard = c;
          }
        }
        boolean done = allCards.remove(currCard);

        currSet.removeCard();

        daysComplete++;
      }
      input.close();
      //end game. print scores
      for(Player p : allPlayers){
        Player currPlayer;
        currPlayer = p;
        finalScore(currPlayer);
      }
    }
    System.exit(0);
  }

  //checks for right number of args and right arg input
  private static boolean checkArgs(String[] args){
    if(args.length == 1){
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
  private static void setDays() {
      if (numPlayers == 2 || numPlayers == 3) {
          numDays = 3;
      } else {
          numDays = 4;
      }
  }

  //creates players with color names
  private static void makePlayers(){
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
      allPlayers.add(new Player(name));
    }
  }

  public static void turn(Player x, Scanner input){
    boolean turn = true;

    String who = "Who";
    String where = "Where";
    String rehearse = "Rehearse";
    String end = "End";
    String act = "Act";
    String work = "Work";
    String move = "Move";
    String upgrade = "Upgrade";

    ArrayList<String> completed = new ArrayList<String>();

    while(turn){
      String userInput = input.nextLine();
      String[] userCommands = userInput.split(" ");//////////////////////////////////////////////////////
      String command = userCommands[0];
      int result = 0;

      if(command.compareToIgnoreCase(who) == 0){//WHO

        String color = x.getPlayerColor();
        int dollars = x.getPlayerDollars();
        int credits = x.getPlayerCredits();
        if(x.getRoleStatus()){
          String role = x.getRoleName();
          System.out.println(color +" ($" + dollars + ", " + credits + "cr) working" + role);
        }else{
          System.out.println(color +" ($" + dollars + ", " + credits + "cr)");
        }

      }
      if(command.compareToIgnoreCase(where) == 0){//WHERE

        String room = x.getPlayerLocation();
        Set currSet = null;
        for(Set s : allSets){
          String name = s.getName();
          if(name.compareToIgnoreCase(room) == 0){
            currSet = s;
          }
        }
        if(currSet == null){
          System.out.println(room);
        }else{
          String cardName = currSet.getCardName();

          if(cardName.compareToIgnoreCase(null) == 0){
            System.out.println(room + "wrapped");
          }else{
            System.out.println("in " + room + " shooting " + cardName);
          }
        }

      }

      if(command.compareToIgnoreCase(end) == 0){

        turn = false;

      }else if(completed.contains(command) == false){//command not done yet

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
           String room = x.getPlayerLocation();
           Set currSet = null;
           for(Set s : allSets){
             String name = s.getName();
             if(name.compareToIgnoreCase(room) == 0){
               currSet = s;
             }
           }
           if(currSet != null){
             int remaining = currSet.getCounts();
             if(remaining == 0){//WRAP SCENE
               String place = x.getPlayerLocation();
               int cardBudget = x.getCardBudget();
               wraps(place, cardBudget, currSet);
             }
           }          

         }else if(command.compareToIgnoreCase(work) == 0){//WORK
           //check that role isn't taken
           int len = userCommands.length;
           String[] subString = new String[len];
           len--;
           String[] substring = Arrays.copyOfRange(userCommands, 1, len);
           String desiredRole = String.join(" ", subString);

           boolean goodToGo = true;
           for(Player p : allPlayers){
             Player currPlayer = p;
             if(currPlayer.getRoleStatus() == true){//has a role
               String playerRole = currPlayer.getRoleName();
               if(playerRole.compareToIgnoreCase(desiredRole) == 0){ // role taken
                 goodToGo = false;
               }
             }
           }

           Role currRole = null;
           for(Role r : allRoles){
             currRole = r;
             String name = currRole.getRoleTitle();
             if(name.compareToIgnoreCase(name) == 0){
               currRole = r;
             }
           }

           //finding budget
           String room = x.getPlayerLocation();
           Set currSet = null;
           for(Set s : allSets){
             String name = s.getName();
             if(name.compareToIgnoreCase(room) == 0){
               currSet = s;
             }
           }
           int newBudget = currSet.getBudget();

           if(goodToGo){//role isn't taken
             //get desired role
             result = x.work(currRole, newBudget);
             if(result == 1){
               boolean isOn = false;
               String roleName = currRole.getRoleTitle();
               for(Cards c : allCards){
                 if(c.isARole(roleName) == true){
                   isOn = true;
                 }
               }

               x.onOrOff(isOn);
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
           int len = userCommands.length;
           //String[] subString = new String[len];
           len--;

           System.out.println("Length of user commands: " + len);
           String[] substring = Arrays.copyOfRange(userCommands, 1, len);
           String desiredDest = String.join(" ", substring); //desired location
           System.out.println("Input: " + userInput);/////////////////////////////////////////////////////////print statement not getting user commands
           Rooms desiredRoom = null;
           for(Rooms r : allRooms){
             Rooms currRoom = r;
             String curr = currRoom.getRoomName();
             if(curr.compareToIgnoreCase(desiredDest) == 0){
               desiredRoom = currRoom;
             }
           }
           Rooms currRoom = x.getRoom();// current location

           String comping = currRoom.peekOne();
           if(comping.compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredRoom);
             if(result == 1){//success
               completed.add(command);
             }
           }

           comping = currRoom.peekOne();
           if(comping.compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredRoom);
             if(result == 1){//success
               completed.add(command);
             }
           }

           comping = currRoom.peekOne();
           if(comping.compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredRoom);
             if(result == 1){//success
               completed.add(command);
             }
           }

           comping = currRoom.peekOne();
           if(comping.compareToIgnoreCase(desiredDest) == 0){
             result = x.move(desiredRoom);
             if(result == 1){//success
               completed.add(command);
             }
           }

           //not a valid move
           result = 0;

         }else if(command.compareToIgnoreCase(upgrade) == 0){//UPGRADE
           if(userCommands.length == 3){
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

    }
  }

  public static void wraps(String room, int budget, Set currSet){
    int[] diceRolls = new int[budget];
    Dice newDice = new Dice();
    for(int x = 0; x < budget; x++){
      diceRolls[x] = newDice.getValue();
    }
    //decreasing.
    Arrays.sort(diceRolls);

    //based on current set and current card
    Cards currCard = null;
    for(Cards c : allCards){
      String name = c.getName();
      String setsCard = currSet.getName();
      if(name.compareToIgnoreCase(setsCard) == 0){
        currCard = c;
      }
    }

    //get number of on card roles
    int numRoles = currCard.getNumRoles();
    boolean onCard = false;

    int spot = budget;//dice rolls
    int track = numRoles - 1;//roles starting at highest ranked
    int[] winnings = new int[numRoles];
    //calculating on card winnings
    while(spot < budget){
      //highest to lowest
      if(track > 0){
        winnings[track] = winnings[track] + diceRolls[spot];
        track--;
        spot--;
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
        spot = 0;
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

  public static void cards() {

    try {
      FileInputStream cardFile = new FileInputStream("cards.xml");
      try {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(cardFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("card");

        //parse the cards
        for (int i = 0; i < nList.getLength(); i++) {
          //card attributes
          String cardName;
          int cardBudget;
          String cardLine; //scene's description
          int cardNumber;

          ArrayList<Role> roleList = new ArrayList<Role>();
          int roleRank = 0;
          String roleLine = null;
          String roleTitle = null;


          Node nNode = nList.item(i);

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element set = (Element) nNode;
            Element temp = (Element) set.getElementsByTagName("scene").item(0);

            NodeList roles = set.getElementsByTagName("part"); //'part' in the xml file is defined as a role in our design

            cardName = set.getAttribute("name");
            cardBudget = Integer.parseInt(set.getAttribute("budget"));
            cardNumber = Integer.parseInt(temp.getAttribute("number"));
            cardLine = set.getElementsByTagName("scene").item(0).getTextContent();

            //roles on a card
            for(int j = 0; j < roles.getLength(); j++) {
              Element n = (Element) roles.item(j);

              roleTitle = n.getAttribute("name");
              roleRank = Integer.parseInt(n.getAttribute("level"));
              roleLine = n.getElementsByTagName("line").item(0).getTextContent();

              allRoles.add(new Role(roleTitle, roleRank, roleLine));
              roleList.add(new Role(roleTitle, roleRank, roleLine));
            }
          allCards.add(new Cards(cardName, cardBudget, cardNumber, roleList, cardLine)); //add these new card objects into the card arraylist(from beginning)
          }
        }
      } finally {
        cardFile.close(); //close the cards file
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  //second method is trying to seperate the componenets of board
  public static void board() {
    //AT THE END WE WANT THE BOARD TO CONTAIN ALL ITS CONTENTS
    try{
      FileInputStream boardFile = new FileInputStream("board.xml");
      try {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(boardFile);
        doc.getDocumentElement().normalize();

        NodeList trailerList = doc.getElementsByTagName("trailer");
        NodeList officeList = doc.getElementsByTagName("office");
        NodeList setList = doc.getElementsByTagName("set");

        //first get the trailer info
        Node tNode = trailerList.item(0);
        String[] trailerNeighbors = new String[3];
        String trailerName = "Trailer";

        if (tNode.getNodeType() == Node.ELEMENT_NODE) {
          Element trailer = (Element) tNode;
          NodeList neighbors = trailer.getElementsByTagName("neighbor");

          for (int j = 0; j < neighbors.getLength(); j++) {
            Element n = (Element) neighbors.item(j);
            trailerNeighbors[j] = n.getAttribute("name");
          }
          String roomOne = trailerNeighbors[0];
          String roomTwo = trailerNeighbors[1];
          String roomThree = trailerNeighbors[2];
          String roomFour = null;
          if(neighbors.getLength() == 4){
            roomFour = trailerNeighbors[3];
          }

        allRooms.add(new Rooms(roomOne, roomTwo, roomThree, roomFour, trailerName));

        }

        //Casting Office info
        Node oNode = officeList.item(0);
        String[] officeNeighbors = new String[3];
        String castingOfficeName = "Casting Office";

        if (oNode.getNodeType() == Node.ELEMENT_NODE) {
          Element office = (Element) oNode;
          NodeList neighbors = office.getElementsByTagName("neighbor");

          //this ArrayList holds the office strings

          for (int j = 0; j < neighbors.getLength(); j++) {
            Element adjRooms = (Element) neighbors.item(j);

            officeNeighbors[j] = adjRooms.getAttribute("name");
          }

          String roomOne = officeNeighbors[0];
          String roomTwo = officeNeighbors[1];
          String roomThree = officeNeighbors[2];
          String roomFour = null;
          if(neighbors.getLength() == 4){
            roomFour = officeNeighbors[3];
          }
        allRooms.add(new Rooms(roomOne, roomTwo, roomThree, roomFour, castingOfficeName));
        }

        //set info
        for (int i = 0; i < setList.getLength(); i++) {
          String[] adjList = new String[4];
          int numTakes = 0;
          String roleName = null;
          int roleRank = 0;
          String roleLine = null;
          String name = null;

          ArrayList<Role> roleArray = new ArrayList<Role>();

          Node nNode = setList.item(i);
          NodeList rolesItems = null;

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element set = (Element) nNode;
            name = set.getAttribute("name");

            NodeList neighbors = set.getElementsByTagName("neighbor");
            NodeList takes = set.getElementsByTagName("take");
            rolesItems = set.getElementsByTagName("part");

            //adjacent or neighbors
            for (int j = 0; j < neighbors.getLength(); j++) {
              Element n = (Element) neighbors.item(j);

              adjList[j] = n.getAttribute("name");
            }
            String roomOne = adjList[0];
            String roomTwo = adjList[1];
            String roomThree = adjList[2];
            String roomFour = null;
            if(neighbors.getLength() == 4){
              roomFour = adjList[3];
            }

            //Make room
          allRooms.add(new Rooms(roomOne, roomTwo, roomThree, roomFour, name));
          //next is the takes info
          numTakes = takes.getLength();
          }



            //the roles
            for(int j = 0; j < rolesItems.getLength(); j++) {
              Element n = (Element) rolesItems.item(j);

              roleName = n.getAttribute("name");
              roleRank = Integer.parseInt(n.getAttribute("level"));
              roleLine = n.getElementsByTagName("line").item(0).getTextContent();

              allRoles.add(new Role(roleName, roleRank, roleLine));
              roleArray.add(new Role(roleName, roleRank, roleLine));
            }

            allSets.add(new Set(name, numTakes, roleArray));

          }

      } finally {
        boardFile.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void finalScore(Player player) {
    int score = calcScore(player);
    System.out.println("--------------------------------------");
    System.out.println("Name:             " + player.getPlayerColor());
    System.out.println("Rank:             " + player.getPlayerRank());
    System.out.println("Credit:           " + player.getPlayerCredits());
    System.out.println("Money:            " + player.getPlayerDollars());
    System.out.println("Total Score:      " + score);
    System.out.println("--------------------------------------");
  }

  public static int calcScore(Player player) {
    return player.getPlayerDollars() + player.getPlayerCredits() + (player.getPlayerRank() * 5);
  }

  public static void associateCards(){
    Collections.shuffle(allCards); //randomizes cards
    int i = 0;
    for(Set s : allSets){
      s.setCard(allCards.get(i));
      i++;
    }
  }

}
