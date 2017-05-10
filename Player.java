import java.util.*;
import java.lang.*;

public class Player{

  //differentiate between players
<<<<<<< HEAD
  private String  playerColor;            //ID
  private String  onOffCard = "None";      //Tells us whether the player is on or off the card
  private boolean hasRole;                //Tells us whether the player has a role - true/false
  private Room    playerLocation;         //tracks the Room the player is in
  private int     playerCredits;          //tracks the players credits
  private int     playerDollars;          //tracks the players dollars
  private int     playerReheasalCredits; //tracks their rehearsal credits
  private int     roleRank;              //tracks the players rank
  private int    roleBudget;            //the card oject holds the role's budget
  private Role    playerRole;            //the role object holds the name of the players role
=======
  private String playerColor;  //ID
  private boolean hasRole;
  private Room playerLocation;
  private int playerCredits;
  private int playerDollars;
  private int playerReheasalCredits;
  private int roleRank;
  private int roleBudget;
  private Role playerRole;
>>>>>>> f6ac4df6c4d0e1c9d3c38be41838500da2ef1f6b

  //constructor for player with a certain color(ID)
  public Player(String playerColor){
    this.playerColor = playerColor;
    this.hasRole = false;
    this.playerCredits = 0;
    this.playerDollars = 0;
    this.roleRank = 0;
    this.roleBudget = 0;
    this.playerLocation = null;
    this.playerRole = null;
    this.onOffCard = "none";
    this.playerReheasalCredits = 0;
  }

  public void wraps(int bonus){
    this.hasRole = false;
    this.roleRank = 0;
    this.roleBudget = 0;
    this.playerLocation = null;
    this.playerRole = null;
    this.onOffCard = "none";
    this.playerReheasalCredits = 0;
    this.playerDollars += bonus;
  }

<<<<<<< HEAD
  public void newDay(){
    this.hasRole = false;
    this.roleRank = 0;
    this.roleBudget = 0;
    this.playerLocation = "Trailer";
    this.playerRole = null;
    this.onOffCard = "none";
    this.playerReheasalCredits = 0;
  }
  //SETTER: Set the players state to "On" or "OFF"
  public void setonOrOffCard(String s){
    this.onOffCard = s;
  }
  //GETTER: Get whether status is on or off card
  public String getonOrOffCard(){
    return onOffCard;
  }
=======
  public Room getRoom(){
    return playerLocation;
  }

>>>>>>> f6ac4df6c4d0e1c9d3c38be41838500da2ef1f6b
  //GETTER: LOCATION
  public String getPlayerLocation(){
    return playerLocation.getRoomName();
  }

  public int getCardBudget(){
    return roleBudget.getBudget();
  }

  //GETTER: PLAYER RANK
  public int getPlayerRank(){
    return playerRank;
  }

  //SETTER: PLAYER STATUS
  public void setRoleStatus(boolean t){
    hasRole = t;
  }

  //GETTER: PLAYER STATUS
  public boolean getRoleStatus(){
    return hasRole;
  }

  //GETTER: PLAYER'S ROLE RANK
  public int getRoleRank(){
    return playerRole.getRoleRank();
  }

  //RETURN BOOLEAN
  public boolean onCard(){
    if (Cards.contains(playerRole)){
       return true;
    }
    else{
      return false;
    }
  }

  //GETTER: PLAYER CREDITS
  public void getPlayerCredits(){
    return this.playerCredits;
  }

  //GETTER: PLAYER DOLLARS
  public void getPlayerDollars(){
    return this.playerDollars;
  }

  //GETTER: PLAYER COLORS
  public void getPlayerColor(){
    return this.player;
  }

  //GETTER: PLAYER'S ROLE NAME
  public String getRoleName(){
    return playerRole.getRoleTitle();
  }

  public void Move(Room room){
    if(!hasRole){                 //if player has no current role,continue
      this.playerLocation = room;
    }
    else{
      System.out.println("Cannot move while working on a scene");   //otherwise player has a role and cannot move
    }
  }

//method for taking a role
  public void work(Role role){
    if(false){
      System.out.println("Another player is already on this role");

    if(this.roleRank >= role.getRoleRank()){
      this.playerRole = role;
      this.setRoleStatus(true);
      this.roleRank = role.getBudget();
      this.roleBudget = 0;
    }
    if(role.onCard()){
      this.setonOrOffCard("On");
    }else{
      this.setonOrOffCard("Off");
    }

    }else{
      System.out.println("Sorry,rank is not high enough for this role");
    }
  }

  public void act(){
    if(hasRole){  //if true
        Dice newDice = new Dice();
        int roll = newDice.getValue();
        if(roleBudget.getBudget() <= roll){
          if(this.onCard){
            this.playerCredits += 2;    //pay for on card
          }
          else{
            this.playerCredits += 1;    //pay for off card
            this.playerDollars += 1;
          }
          return 1; // check remainingshots //***********************************************************CHECK IF LAST SCENE
        }
          System.out.println("SUCCESS");
      }
      else{
        if(!onCard){
          this.playerCredits += 1;
        }
        System.out.println("FAIL");
      }
    return 0;
  }

  public void upgrade(String t, int rank){
    String location = playerLocation.getRoomName();
    if(location.compareToIgnoreCase("Casting Office") == 0){
      int[] dollars = new int[5];
      int[] credits = new int[5];
      dollars[0] = 4;
      dollars[1] = 10;
      dollars[2] = 18;
      dollars[3] = 28;
      dollars[4] = 40;
      credits[0] = 5;
      credits[1] = 10;
      credits[2] = 15;
      credits[3] = 20;
      credits[4] = 25;
      if(t.compareToIgnoreCase("$") == 0){
        int r = dollars[rank - 2];
        if(playerDollars >= r){
          playerRank++;
          playerDollars = playerDollars - dollars[rank - 2];
          return 1;
        }
      }
      if(t.compareToIgnoreCase("cr") == 0){
        int r = credits[rank -2];
        if(playerCredits >= r){
          playerRank++;
          playerCredits = (playerCredits - credits[rank - 2]);
          return 1;
        }
      }
    }
    return 0;
  }

  public int rehearse(){
    if(rehersalCredits < 6){
      if(hasRole){
        playerReheasalCredits++;
        return 1;
      }
    }
    return 0;
  }
}
