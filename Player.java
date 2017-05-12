import java.util.*;
import java.lang.*;

public class Player{

  //differentiate between players
  private String  playerColor;            //ID
  private String  onOffCard;      //Tells us whether the player is on or off the card
  private boolean hasRole;                //Tells us whether the player has a role - true/false
  private Rooms    playerLocation;         //tracks the Room the player is in
  private int     playerCredits;          //tracks the players credits
  private int     playerDollars;          //tracks the players dollars
  private int     playerReheasalCredits; //tracks their rehearsal credits
  private int     roleRank;              //tracks the players rank
  private int     roleBudget;            //the card oject holds the role's budget
  private Role    playerRole;            //the role object holds the name of the players role

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


  public void newDay(Rooms trailer){
    this.hasRole = false;
    this.roleRank = 0;
    this.roleBudget = 0;
    this.playerLocation = trailer;
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

  //GETTER: ROOM OBJECT
  public Rooms getRoom(){
    return playerLocation;
  }

  //GETTER: LOCATION
  public String getPlayerLocation(){
    String local = playerLocation.getRoomName();
    return local;
  }

  //GETTER: CARD BUDGET
  public int getCardBudget(){
    return this.roleBudget;
  }

  //GETTER: PLAYER RANK
  public int getPlayerRank(){
    return this.roleRank;
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
    int rank = playerRole.getRoleRank();
    return rank;
  }

 //RETURN BOOLEAN: ON OF OFF CARD
  public boolean onCard(){
    if (onOffCard.compareToIgnoreCase("On") == 0){
       return true;
    }
    else{
      return false;
    }
  }

  //SETTER FOR onOffCard
  public void onOrOff(boolean answer){
    if(answer){
      onOffCard = "On";
    }else{
    onOffCard = "Off";
    }
  }


  //GETTER: PLAYER CREDITS
  public int getPlayerCredits(){
    return this.playerCredits;
  }

  //GETTER: PLAYER DOLLARS
  public int getPlayerDollars(){
    return this.playerDollars;
  }

  //GETTER: PLAYER COLORS
  public String getPlayerColor(){
    return this.playerColor;
  }

  //GETTER: PLAYER'S ROLE NAME
  public String getRoleName(){
    return playerRole.getRoleTitle();
  }

  public int move(Rooms room){
    if(!hasRole){                 //if player has no current role,continue
      this.playerLocation = room;
      return 1;
    }
    else{
      System.out.println("Cannot move while working on a scene");   //otherwise player has a role and cannot move
      return 0;
    }
  }

//method for taking a role
  public int work(Role role, int newBudget){

    if(this.roleRank >= role.getRoleRank()){
      this.playerRole = role;
      this.setRoleStatus(true);
      this.roleBudget = newBudget;
      return 1;

    }else{
      System.out.println("Sorry,rank is not high enough for this role");
      return 0;
    }
  }

  public int act(){
    if(hasRole){  //if true
        Dice newDice = new Dice();
        int roll = newDice.getValue();
        if(this.roleBudget <= roll){
          if(this.onCard()){
            this.playerCredits += 2;    //pay for on card
          }
          else{
            this.playerCredits += 1;    //pay for off card
            this.playerDollars += 1;
          }
          return 1;
        }
          System.out.println("SUCCESS");
      }
      else{
        if(!onCard()){
          this.playerCredits += 1;
        }
        System.out.println("FAIL");
      }
    return 0;
  }

  public int upgrade(String t, int rank){
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
          roleRank++;
          playerDollars = playerDollars - dollars[rank - 2];
          return 1;
        }
      }
      if(t.compareToIgnoreCase("cr") == 0){
        int r = credits[rank -2];
        if(playerCredits >= r){
          roleRank++;
          playerCredits = (playerCredits - credits[rank - 2]);
          return 1;
        }
      }
    }
    return 0;
  }

  public int rehearse(){
    if(playerReheasalCredits < 6){
      if(hasRole){
        playerReheasalCredits++;
        return 1;
      }
    }
    return 0;
  }
}
