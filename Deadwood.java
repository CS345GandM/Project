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
  private static int numDays    = 0;
  private static int numScenes = 22;
  private static int sceneTrack = 0;
  private static ArrayList<Player> allPlayers = new ArrayList<Player>();  //contains the list of player objects
  private static ArrayList<Cards> allCards = new ArrayList<Cards>();      //contrains the list of card objects
  private static ArrayList<Set> allSets = new ArrayList<Set>();          //contains the list of set objects
  private static ArrayList<Rooms> allRooms = new ArrayList<Rooms>();    //contains the list of room objects
  private static ArrayList<Role> allRoles = new ArrayList<Role>();      //contains the list of role objects

  public static void main(String[] args) {

    if(checkArgs(args)){                //check that there is a valid number of players
      numPlayers = Integer.parseInt(args[0]);
      System.out.println(" ");
      System.out.println("|----------------------------------------------------------------------|");
      System.out.println("                         Number of players: "+ numPlayers                );
      System.out.println("|                         Command Options                              |");
      System.out.println("|----------------------------------------------------------------------|");
      System.out.println("|Type: 'who'              for current player information               |");
      System.out.println("|      'where'            for the location of player                   |");
      System.out.println("|      'move room'        to move to indicated room                    |");
      System.out.println("|      'work part'        to take an indicated role                    |");
      System.out.println("|      'upgrade $ level'  to upgrade to indicated level using dollars  |");
      System.out.println("|      'upgrade cr level' to upgrade to indicated level using credits  |");
      System.out.println("|      'upgrade cr level' to upgrade to indicated level using credits  |");
      System.out.println("|      'rehearse'         current player rehearses                     |");
      System.out.println("|      'act'              current player acts on current role          |");
      System.out.println("|      'end'              terminate player's turn                      |");
      System.out.println("|----------------------------------------------------------------------|");
      System.out.println(" ");

      setDays();
      int daysComplete = 0;
      makePlayers();
      cards(); // call the methods that create the cards and board (reading in files)
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
        for(Player player : allPlayers){
          player.newDay(newDayTrailer);
        }
        associateCards();
        sceneTrack = numScenes;
        Player currPlayer = null;
        while(sceneTrack > 1){
          for(Player player : allPlayers){
            currPlayer = player;
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
      for(Player player : allPlayers){
        Player currPlayer;
        currPlayer = player;
        finalScore(currPlayer);
      }
    }
    System.exit(0);
  }

  //Method: checkArgs
  //Purpose:checks for right number of args and right arg input
  private static boolean checkArgs(String[] args){
    if(args.length == 1){
        String players = args[0];
        int num = Integer.parseInt(players);
        if(num > 1 && num < 9){
          return true;
        }
    }
    System.out.println("ERROR: Please enter number of players (1-8)");
    return false;
  }

  //Method: setDays
  //Purpose: determine the number of days based on number of players
  private static void setDays() {
      if (numPlayers == 2 || numPlayers == 3) {
          numDays = 3;
      } else {
          numDays = 4;
      }
  }

  //Method:  makePlayers
  //Purpose: create players with color IDs
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
  //Method: turn
  //Purpose: continue prompting for commands from user as long as player has a turn
  //Input: player object, and user's input
  public static void turn(Player x, Scanner input){

    //set player's turn to true
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
      String command = null;
      String userInput = input.nextLine();
      Scanner parser = new Scanner(userInput);
      if(parser.hasNext()){
        command = parser.next();
      }

      int result = 0;
  //***************************** WHO **********************************
      if(command.compareToIgnoreCase(who) == 0){

        String color = x.getPlayerColor();
        int dollars = x.getPlayerDollars();
        int credits = x.getPlayerCredits();
        if(x.getRoleStatus()){
          String role = x.getRoleName();
          System.out.println(color +" ($" + dollars + ", " + credits + "cr) working " + role);
        }else{
          System.out.println(color +" ($" + dollars + ", " + credits + "cr)");
        }

      }
 //***************************** WHERE **********************************
      if(command.compareToIgnoreCase(where) == 0){

        String room = x.getPlayerLocation();
        boolean isASet = false;
        String cardName = "noCard";

        for(Set s : allSets){
          String name = s.getName();
          if(name.compareToIgnoreCase(room) == 0){
            isASet = true;
            cardName = s.getCardName();
          }
        }
        if(!isASet){
          System.out.println(room);
        }else{
          int compared = cardName.compareToIgnoreCase("noCard");
          if(compared == 0){
            System.out.println(room + " wrapped");
          }else{
            System.out.println("In " + room + " shooting " + cardName);
          }
        }

      }

      if(command.compareToIgnoreCase(end) == 0){
        System.out.println("**** " + x.getPlayerColor() + "'s turn is over! ****");
        turn = false;         //upon entering end, the players turn is over

      }else if(completed.contains(command) == false){ //command not done yet

  //***************************** REHEARSE **********************************
        if(command.compareToIgnoreCase(rehearse) == 0){

           result = x.rehearse();
           if(result == 1){//success
             completed.add(rehearse);
             completed.add(act);
             completed.add(work);
             completed.add(move);
             completed.add(upgrade);
           }
  //***************************** ACT **********************************
         }else if(command.compareToIgnoreCase(act) == 0){

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
  //***************************** WORK **********************************
         }else if(command.compareToIgnoreCase(work) == 0){

           ArrayList<String> job = new ArrayList<String>();
           while(parser.hasNext()){
             boolean complete = job.add(parser.next());
           }
           String desRole = "";
           for(String s : job){
             desRole += s + " ";
           }
           String desiredRole = desRole.substring(0, desRole.length() - 1);

           boolean goodToGo = true;
           for(Player player : allPlayers){
             Player currPlayer = player;
             if(currPlayer.getRoleStatus() == true){//has a role
               String playerRole = currPlayer.getRoleName();
               if(playerRole.compareToIgnoreCase(desiredRole) == 0){ // role taken
                 goodToGo = false;
               }
             }
           }


           //finding budget
           String room = x.getPlayerLocation();
           int newBudget = 0;
           for(Set s : allSets){
             String name = s.getName();
             if(name.compareToIgnoreCase(room) == 0){
               newBudget = s.getBudget();
             }
           }

           if(goodToGo){//role isn't taken
             //get desired role

             for(Role r : allRoles){
               String currName = r.getRoleTitle();
               int comparing = currName.compareToIgnoreCase(desiredRole);
               if( comparing == 0){
                 int rank = r.getRoleRank();
                 result = x.work(r, newBudget, rank);
               }
             }

             if(result == 1){
               boolean isOn = false;
               for(Cards c : allCards){
                 if(c.isARole(desiredRole) == true){
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

  //***************************** MOVE **********************************
         }else if(command.compareToIgnoreCase(move) == 0){
           //find destination
           ArrayList<String> place = new ArrayList<String>();
           while(parser.hasNext()){
             boolean complete = place.add(parser.next());
           }
           String desiredDest = "";
           String dest = "";
           for(String s : place){
             dest += s + " ";
           }
           desiredDest = dest.substring(0, dest.length() - 1);

           boolean goodToGo = false;
           Rooms currRoom = x.getRoom();// current location
           String comping = currRoom.peekOne();
           int compared = comping.compareToIgnoreCase(desiredDest);
           if( compared == 0){
             goodToGo = true;
           }

           comping = currRoom.peekTwo();
           compared = comping.compareToIgnoreCase(desiredDest);
           if(compared == 0){
             goodToGo = true;
           }

           comping = currRoom.peekThree();
           compared = comping.compareToIgnoreCase(desiredDest);
           if(compared == 0){
             goodToGo = true;
           }

           comping = currRoom.peekFour();
           compared = comping.compareToIgnoreCase(desiredDest);
           if(compared == 0){
             goodToGo = true;
           }

           if(goodToGo){
             for(Rooms r : allRooms){
               Rooms thisRoom = r;
               String curr = thisRoom.getRoomName();
               if(curr.compareToIgnoreCase(desiredDest) == 0){
                 result = x.move(thisRoom);
               }
             }
             if(result == 1){//success
               completed.add(command);
             }
           }

           //not a valid move
           result = 0;

  //***************************** UPGRADE **********************************
         }else if(command.compareToIgnoreCase(upgrade) == 0){
           String type = null;
           int up = 0;
           int track = 0;
           if(parser.hasNext()){
             type = parser.next();
             track++;
           }
           if(parser.hasNext()){
             up = Integer.parseInt(parser.next());
             track++;
           }
           if(track == 2){
             result = x.upgrade(type, up);
             if(result == 1){//success
               completed.add(command);
             }
          }

        }

      }else{
        System.out.println("SOMETHING IS JUST NOT HAPPENING.");
      }
      parser.close(); //turn over

    }
  }

  //Method: wraps
  //Purpose:
  //Input:
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

    for(Player player : allPlayers){ //on card roles
      String cardRole = player.getonOrOffCard();
      if(cardRole.compareToIgnoreCase("On") == 0){
        onCard = true;
        int roleRank = player.getRoleRank();
        spot = 0;
        while(roleRank != rankOrder[spot]){
          spot++;
        }
        player.wraps(winnings[spot]);
      }
    }

    if(onCard){ // if there is an on the card player
      for(Player player : allPlayers){ //on card roles
        String cardRole = player.getonOrOffCard();
        if(cardRole.compareToIgnoreCase("off") == 0){
          int roleRank = player.getRoleRank();
          player.wraps(roleRank);
        }
      }
    }

    //removing card
    currSet.removeCard();
    boolean done = allCards.remove(currCard);
  }

  public static void finalScore(Player player) {
    int score = calcScore(player);
    System.out.println("--------------------------------------");
    System.out.println("Name:             " + player.getPlayerColor());
    System.out.println("Rank:             " + player.getPlayerRank());
    System.out.println("Credit:           " + player.getPlayerCredits());
    System.out.println("Money:            " + player.getPlayerDollars());
    System.out.println("TOTAL SCORE:      " + score);
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

//********************************************************************** READ IN FILES ******************************************************************
  //Method: cards
  //Purpose: read in the card.xml file
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
          String roomFour = "";
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
          String roomFour = "";
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
            String roomFour = "";
            if(neighbors.getLength() == 4){
              roomFour = adjList[3];
            }

            //Make rooms
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

}
