//Melissa Gonzalez and Hannah Montague
//CSCI 345 - Moushumi Sharmin
//Due: May 15, 2017

import java.util.*;
import java.io.*;
import java.lang.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Deadwood2{

  private static int numPlayers = 0;
  private static int numDays    = 0;
  private static int numScenes = 22;
  private static int sceneTrack = 0;
  private static ArrayList<Player> allPlayers = new ArrayList<Player>();   //contains the list of player objects
  private static ArrayList<Cards> allCards = new ArrayList<Cards>();      //contrains the list of card objects
  private static ArrayList<Set> allSets = new ArrayList<Set>();          //contains the list of set objects
  private static ArrayList<Rooms> allRooms = new ArrayList<Rooms>();    //contains the list of room objects
  private static ArrayList<Role> allRoles = new ArrayList<Role>();     //contains the list of role objects

  private static JFrame frame = new JFrame();
  private static Board board;

  public Deadwood2(){
    //object
  }

  public void makeGame(Board board) throws Exception {
        for(Rooms r : allRooms){
          String currRoom = r.getRoomName();
          if(currRoom.compareToIgnoreCase("Trailer") == 0){
            for(Player player : allPlayers){
              String color = player.getPlayerColor();
              board.moveDice(color, 1000, 350, 46, 46);
              player.newDay(r);
              if(color.compareToIgnoreCase("blue") == 0){
                board.blueRank(1);
              }
              if(color.compareToIgnoreCase("red") == 0){
                board.redRank(1);
              }
              if(color.compareToIgnoreCase("green") == 0){
                board.greenRank(1);
              }
              if(color.compareToIgnoreCase("orange") == 0){
                board.orangeRank(1);
              }
            }
          }
        }

        //associateCards();

  }

  public void resetGame(String room) throws Exception {
        for(Rooms r : allRooms){
          String currRoom = r.getRoomName();
          if(currRoom.compareToIgnoreCase("Trailer") == 0){
            for(Player player : allPlayers){
              String color = player.getPlayerColor();
              board.moveDice(color, 1000, 350, 46, 46);
              player.newDay(r);
              if(color.compareToIgnoreCase("blue") == 0){
                board.blueRank(1);
              }
              if(color.compareToIgnoreCase("red") == 0){
                board.redRank(1);
              }
              if(color.compareToIgnoreCase("green") == 0){
                board.greenRank(1);
              }
              if(color.compareToIgnoreCase("orange") == 0){
                board.orangeRank(1);
              }
            }
          }
        }

        //associateCards();

        int xSet = 0;
        int ySet = 0;
        int wSet = 0;
        int hSet = 0;

        //remove last card
        String cardName = "";
        int currCardSceneNum = 0;
        for(Set s : allSets){
          String name = s.getName();
          if(name.compareToIgnoreCase(room) == 0){
            cardName = s.getCardName();
            currCardSceneNum = s.getCardSceneNumber();
            xSet = s.getCardX();
            ySet = s.getCardY();
            wSet = s.getCardW();
            hSet = s.getCardH();
          }
        }
        Cards currCard = null;
        for(Cards c : allCards){
          int cardSceneNum = c.getSceneNumber();
          String name = c.getName();
          if(name.compareToIgnoreCase(cardName) == 0){
            if(cardSceneNum == currCardSceneNum){
              board.coverCard(xSet, ySet, wSet, hSet);
              boolean done = allCards.remove(c);
            }
          }
        }
  }



  //Method: setDays
  //Purpose: determine the number of days based on number of players
  public static int setDays(int numPlayers) {
      if (numPlayers == 2 || numPlayers == 3) {
          return 3;
      } else {
          return 4;
      }
  }

  //Method:  makePlayers
  //Purpose: create players with color IDs
  public static ArrayList<Player> makePlayers(int numPlayers){
    String[] colors = new String[8];
    colors[0] = "blue";
    colors[1] = "red";
    colors[2] = "green";
    colors[3] = "orange";

    for(int x = 0; x < numPlayers; x++){
      String name = colors[x];
      allPlayers.add(new Player(name));
    }
    //System.out.println("Deadwood" + allPlayers.get(0));
    return allPlayers;
  }


  public void rehearse(Player x, Board board){
    int result = 0;
    if(x.getRoleStatus()){
      result = x.rehearse();
    }else{
      board.displayErrorMessage("Sorry, you have to be working a role to rehearse");
    }

     if(result == 1){//success
       board.displayErrorMessage(" ");

     }else{
       board.displayErrorMessage("Sorry, you cannot have more than 6 rehearsal credits");
     }
  }

  public int act(Player x){
    int result = x.act();

    if(result == 1){
      String room = x.getPlayerLocation();
      for(Set s : allSets){
        String name = s.getName();
        if(name.compareToIgnoreCase(room) == 0){
          sceneTrack--;
          s.removeCounters();
          int remaining = s.getCounts();
          if(remaining == 0){//WRAP SCENE
            String place = x.getPlayerLocation();
            int cardBudget = x.getCardBudget();
            wraps(place, cardBudget, s);
          }
        }
      }
      return 1; //success
    }
    return 0; //fail
  }

  public void work(Player x, String desiredRole, Board board){
    String color = x.getPlayerColor();
    int result = 0;

    System.out.println(desiredRole);

    String currPlayerLocation = x.getPlayerLocation();

    boolean goodToGo = true;
    for(Player player : allPlayers){
      //Player currPlayer = player;
      if(player.getRoleStatus() == true){  //has a role
        String pLocal = player.getPlayerLocation();
        if(pLocal.compareToIgnoreCase(currPlayerLocation) == 0){//in the same room --> right role place
          String playerRole = player.getRoleName();
          if(playerRole.compareToIgnoreCase(desiredRole) == 0){ // role taken
            goodToGo = false;
          }
        }
      }
    }

    //finding the budget
    String room = x.getPlayerLocation();
    int newBudget = 0;
    boolean rightPlace = false;

    int xValueSet = 0;
    int yValueSet = 0;

    boolean onCard = false;
    for(Set s : allSets){
      if(s.setHasACard()){
        String name = s.getName();
        if(name.compareToIgnoreCase(room) == 0){
          if(s.hasThisRoleOff(desiredRole)){
            newBudget = s.getBudget();
            xValueSet = s.getCardX();
            yValueSet = s.getCardY();
            rightPlace = true;

          }else if(s.hasThisRoleOn(desiredRole)){
            newBudget = s.getBudget();
            xValueSet = s.getCardX();
            yValueSet = s.getCardY();
            rightPlace = true;
            onCard = true;
          }
        }
      }
    }

    if(goodToGo && rightPlace){//role isn't taken
      //get desired role
      int xValue = 0;
      int yValue = 0;
      int wValue = 0;
      int hValue = 0;
      int rank = 0;

      for(Role r : allRoles){
        String currName = r.getRoleTitle();
        int comparing = currName.compareToIgnoreCase(desiredRole);
        if( comparing == 0){
          if(r.getRolePlace() == onCard)
            rank = r.getRoleRank();
            xValue = r.getRoleX();
            yValue = r.getRoleY();
            wValue = r.getRoleW();
            hValue = r.getRoleH();


            result = x.work(r, newBudget, rank);
        }
      }

      System.out.println("Here " + result);
      if(result == 1){
        System.out.println("Here " + result);

        if(onCard){
          xValue += xValueSet;
          yValue += yValueSet;
        }

        //check on or off card
        x.onOrOff(onCard);

        //move player dice
        board.moveDice(color, xValue, yValue, wValue, hValue);


        board.displayErrorMessage(" ");

      }else{
        board.displayErrorMessage("Sorry,rank is not high enough for this role");
      }
    }else{
      if(goodToGo){

        board.displayErrorMessage("Sorry, this role isn't available at your current location");
      }else{
        board.displayErrorMessage("Sorry, this role is already taken");
      }
    }
  }

  public void move(Player x, String desiredDest, Board board){
    String color = x.getPlayerColor();
    int result = 0;

    if(desiredDest.compareToIgnoreCase("casting office") == 0){
      desiredDest = "office";
    }

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
        board.displayErrorMessage(" ");

        //Move Players Dice
        if(desiredDest.compareToIgnoreCase("office") == 0){
          int xValue = 100;
          int yValue = 500;
          int wValue = 46;
          int hValue = 46;
          board.moveDice(color, xValue, yValue, wValue, hValue);
        }else if(desiredDest.compareToIgnoreCase("trailer") == 0){
          int xValue = 1010;
          int yValue = 350;
          int wValue = 46;
          int hValue = 46;
          board.moveDice(color, xValue, yValue, wValue, hValue);
        }else {
          int xValue = 0;
          int yValue = 0;
          int wValue = 0;
          int hValue = 0;
          for(Set s : allSets){
            String name = s.getName();
            if(name.compareToIgnoreCase(desiredDest) == 0){
              xValue = s.getCardX() + 25;
              yValue = s.getCardY() + 115;
              hValue = 46;
              wValue = 46;
            }
          }
          board.moveDice(color, xValue, yValue, wValue, hValue);
        }
      }else{
        board.displayErrorMessage("Cannot move while working on a scene");
      }
    }else{
      board.displayErrorMessage("Cannot move to this location");
    }
  }

  public void upgradeDollars(Player x, int up, Board board)throws Exception{
    int result = x.upgrade("$", up);
    if(result == 1){//success
      String color = x.getPlayerColor();

      if(color.compareToIgnoreCase("blue") == 0){
        board.blueRank(up);
      }
      if(color.compareToIgnoreCase("red") == 0){
        board.redRank(up);
      }
      if(color.compareToIgnoreCase("green") == 0){
        board.greenRank(up);
      }
      if(color.compareToIgnoreCase("orange") == 0){
        board.orangeRank(up);
      }
    }
  }

  public void upgradeCredits(Player x, int up, Board board)throws Exception{

    int result = x.upgrade("cr", up);
    if(result == 1){//success
      String color = x.getPlayerColor();

      if(color.compareToIgnoreCase("blue") == 0){
        board.blueRank(up);
      }
      if(color.compareToIgnoreCase("red") == 0){
        board.redRank(up);
      }
      if(color.compareToIgnoreCase("green") == 0){
        board.greenRank(up);
      }
      if(color.compareToIgnoreCase("orange") == 0){
        board.orangeRank(up);
      }
    }
  }

  //Method: wraps
  //Purpose: wrap a scene
  //Input: String, int, Set
  public static void wraps(String room, int budget, Set currSet){

    int[] diceRolls = new int[budget];
    Dice newDice = new Dice();
    for(int x = 0; x < budget; x++){
      diceRolls[x] = newDice.getValue();

    }

    //decreasing.
    Arrays.sort(diceRolls);

    //based on current set and current card
    int numRoles = 0;

    for(Cards c : allCards){
      String name = c.getName();
      int sceneNum = c.getSceneNumber();
      String setsCard = currSet.getCardName();
      int setSceneNum = currSet.getCardSceneNumber();
      if(name.compareToIgnoreCase(setsCard) == 0){
        if(sceneNum == setSceneNum){
          numRoles = c.getNumRoles();
        }
      }
    }

    int weird = 1;
    int[] rankOrder = new int[numRoles];
    //int rankCounter = 1;
    for(Cards c : allCards){
      String name = c.getName();
      int sceneNum = c.getSceneNumber();
      String setsCard = currSet.getCardName();
      int setSceneNum = currSet.getCardSceneNumber();
      if(name.compareToIgnoreCase(setsCard) == 0){
        if(sceneNum == setSceneNum){
          for(int check = 0; check < numRoles; check++){
            rankOrder[check] = c.getRoleRanks(check);

          }
        }
      }
    }



    //tracks if there's a player on the card
    boolean onCard = false;

    int spot = budget - 1;//dice rolls
    int track = numRoles - 1;//roles starting at highest ranked
    int[] winnings = new int[numRoles];

    //calculating on card winnings
    while(spot >= 0){
      //highest to lowest
      if(track >= 0){
        winnings[track] = winnings[track] + diceRolls[spot];
        track--;
        spot--;
      }else{
        track = numRoles - 1;
      }
    }

    for(int cash = 0; cash < numRoles; cash++){
      System.out.println("Winnings: " + winnings[cash] + " Role value: " + rankOrder[cash]);
    }


    String currentSetName = currSet.getName();

    //handles on card roles
    for(Player player : allPlayers){
      String cardRole = player.getonOrOffCard();
      if(cardRole.compareToIgnoreCase("On") == 0){
        String currPlayerLocation = player.getPlayerLocation();
        if(currPlayerLocation.compareToIgnoreCase(currentSetName) == 0){
          onCard = true;
          int roleRank = player.getRoleRank();
          //spot = 0;
          int here = 0;
          for(int loop = 0; loop < numRoles; loop++){
            if(roleRank == rankOrder[loop]){
              here = loop;
            }
          }
          System.out.println("Here, winnings @ spot: " + winnings[here]);
          //int bonus = winnings[]
          player.wraps(winnings[here]);
        }
      }
    }

    //handles off card roles
    for(Player player : allPlayers){
      String cardRole = player.getonOrOffCard();
      if(cardRole.compareToIgnoreCase("off") == 0){
        String currPlayerLocation = player.getPlayerLocation();
        if(currPlayerLocation.compareToIgnoreCase(currentSetName) == 0){
          if(onCard){ //if there is an on card player....
            int roleRank = player.getRoleRank();
            player.wraps(roleRank);
          }else{
            player.wraps(0);
          }
        }

      }
    }


    int xSet = 0;
    int ySet = 0;
    int wSet = 0;
    int hSet = 0;

    for(Set s : allSets){
      String name = s.getName();
      String curr = currSet.getName();
      if(name.compareToIgnoreCase(curr) == 0){
        xSet = s.getCardX();
        ySet = s.getCardY();
        wSet = s.getCardW();
        hSet = s.getCardH();
      }
    }



    //removing card from card arrayList
    for(Cards c : allCards){
      String name = c.getName();
      int sceneNum = c.getSceneNumber();
      String setsCard = currSet.getCardName();
      int setSceneNum = currSet.getCardSceneNumber();
      if(name.compareToIgnoreCase(setsCard) == 0){
        if(sceneNum == setSceneNum){
          //removing card from set
          board.coverCard(xSet, ySet, wSet, hSet);
          currSet.removeCard();
          boolean done = allCards.remove(c);
          break;
        }
      }
    }


  }


  //Method: finalScore
  //Purpose: display different aspects of players final score and final score

  public static String finalScore(Player player, Board board) {
    int score = calcScore(player);
    String message = "-------------------<br>Name: " + player.getPlayerColor()
    + "<br>Rank: " + player.getPlayerRank()
    + "<br>Credit: " + player.getPlayerCredits()
    + "<br>Money: " + player.getPlayerDollars()
    + "<br>TOTAL SCORE: " + score
    + "<br>-------------------";
    return message;
  }

  //Method: calcScore()
  //Purpose: take a players credits, dollars, and ranks to calculate a score based on game rules

  public static int calcScore(Player player) {
    return player.getPlayerDollars() + player.getPlayerCredits() + (player.getPlayerRank() * 5);
  }

  //Method: associateCards()
  //Purpose: associate cards with different sets.

  public static void associateCards(Board board) throws Exception{
    Collections.shuffle(allCards); //randomizes cards
    int i = 0;
    for(Set s : allSets){
      Cards currCard = allCards.get(i);
      s.setCard(currCard);
      int xValue = s.getCardX();
      int yValue = s.getCardY();
      int wValue = s.getCardW();
      int hValue = s.getCardH();
      String image = currCard.getImageName();

      board.addImage(image, xValue, yValue, wValue, hValue);

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
        int pngCount = 1;

        //parse the cards
        for (int i = 0; i < nList.getLength(); i++) {
          //card attributes
          String cardName;
          int cardBudget;
          String cardLine; //scene's description
          int cardNumber;
          String pngName = pngCount + ".png";
          pngCount++;

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
              int roleX = 0;
              int roleY = 0;
              int roleW = 0;
              int roleH = 0;

              Element n = (Element) roles.item(j);

              roleTitle = n.getAttribute("name");
              roleRank = Integer.parseInt(n.getAttribute("level"));
              roleLine = n.getElementsByTagName("line").item(0).getTextContent();

              NodeList roleArea = n.getElementsByTagName("area");
              for(int m = 0; m < roleArea.getLength(); m++){
                Element rArea = (Element) roleArea.item(m);
                roleX = Integer.parseInt(rArea.getAttribute("x"));
                roleY = Integer.parseInt(rArea.getAttribute("y"));
                roleW = Integer.parseInt(rArea.getAttribute("w"));
                roleH = Integer.parseInt(rArea.getAttribute("h"));
              }


              allRoles.add(new Role(roleTitle, roleRank, roleLine, roleX, roleY, roleW, roleH, true));
              roleList.add(new Role(roleTitle, roleRank, roleLine, roleX, roleY, roleW, roleH, true));
            }
          allCards.add(new Cards(cardName, cardBudget, cardNumber, roleList, cardLine, pngName)); //add these new card objects into the card arraylist(from beginning)
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
        String[] trailerNeighbors = new String[4];
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
        String[] officeNeighbors = new String[4];
        String castingOfficeName = "office";

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
          int cardX = 0;
          int cardY = 0;
          int cardW = 0;
          int cardH = 0;


          ArrayList<Role> roleArray = new ArrayList<Role>();

          Node nNode = setList.item(i);
          NodeList rolesItems = null;

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element set = (Element) nNode;
            name = set.getAttribute("name");

            NodeList neighbors = set.getElementsByTagName("neighbor");


            //card area
            NodeList area = set.getElementsByTagName("area");
            for(int j = 0; j < area.getLength(); j++){
              Element cardAreas = (Element) area.item(0);
              cardX = Integer.parseInt(cardAreas.getAttribute("x"));
              cardY = Integer.parseInt(cardAreas.getAttribute("y"));
              cardW = Integer.parseInt(cardAreas.getAttribute("w"));
              cardH = Integer.parseInt(cardAreas.getAttribute("h"));
            }

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
              int roleX = 0;
              int roleY = 0;
              int roleW = 0;
              int roleH = 0;

              Element n = (Element) rolesItems.item(j);

              roleName = n.getAttribute("name");
              roleRank = Integer.parseInt(n.getAttribute("level"));
              roleLine = n.getElementsByTagName("line").item(0).getTextContent();

              NodeList roleArea = n.getElementsByTagName("area");
              for(int m = 0; m < roleArea.getLength(); m++){
                Element rArea = (Element) roleArea.item(m);
                roleX = Integer.parseInt(rArea.getAttribute("x"));
                roleY = Integer.parseInt(rArea.getAttribute("y"));
                roleW = Integer.parseInt(rArea.getAttribute("w"));
                roleH = Integer.parseInt(rArea.getAttribute("h"));
              }



              allRoles.add(new Role(roleName, roleRank, roleLine, roleX, roleY, roleW, roleH, false));
              roleArray.add(new Role(roleName, roleRank, roleLine, roleX, roleY, roleW, roleH, false));
            }
            allSets.add(new Set(name, numTakes, roleArray, cardX, cardY, cardW, cardH));
          }

      } finally {
        boardFile.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
