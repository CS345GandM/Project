import java.util.*;
import java.lang.*;

public class Player{

  //differentiate between players
  private String  playerColor;            //ID
  private String  onOffCard;              //Tells us whether the player is on or off the card
  private boolean hasRole;                //Tells us whether the player has a role - true/false
  private Rooms   playerLocation;         //tracks the Room the player is in
  private int     playerCredits;          //tracks the players credits
  private int     playerDollars;          //tracks the players dollars
  private int     playerReheasalCredits;  //tracks their rehearsal credits
  private int     roleRank;               //tracks the player's rank
  private int     roleBudget;             //the card oject holds the role's budget
  private Role    playerRole;             //the role object holds the name of the players role

  //constructor for player with a certain color(ID)
  //Initialize the player's attributes
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

//******************************** Getters & Setters  *****************************************

//these seem similar, revisit these methods?
/////////////////////////////////////////////////////////
  //Setter: setonOrOffCard
  public void setonOrOffCard(String s){
    this.onOffCard = s;
  }

  //Getter:  Get whether status is on or off card
  //Purpose: determine whether player's role is on or off a card
  public String getonOrOffCard(){
    return onOffCard;
  }

  //Method:  onCard()
  //Purpose: determine is player is acting on or off card
  //Return:  boolean
   public boolean onCard(){
     if (onOffCard.compareToIgnoreCase("On") == 0){
        return true;
     }
     else{
       return false;
     }
   }

   //Setter: onOrOff
   public void onOrOff(boolean answer){
     if(answer){
       onOffCard = "On";
     }else{
     onOffCard = "Off";
     }
   }

/////////////////////////////////////////////////////////



//Do we need both?
/////////////////////////////////////////////////////////
  //Getter:  getRoom
  //Purpose: determine what room player is in
  public Rooms getRoom(){
    return playerLocation;
  }

  //Getter:  getPlayerLocation
  //Purpose: return the name of room player is in
  public String getPlayerLocation(){
    String local = playerLocation.getRoomName();
    return local;
  }
/////////////////////////////////////////////////////////

  //Getter:  getCardBudget
  //Purpose: purpose return the card's budget associated with role
  public int getCardBudget(){
    return this.roleBudget;
  }

  //Getter:  getPlayerRank
  //Purpose: determine the player's role rank
  public int getPlayerRank(){
    return this.roleRank;
  }

  //Setter: setRoleStatus
  public void setRoleStatus(boolean t){
    hasRole = t;
  }

  //Getter:  getRoleStatus
  //Purpose: determine whether player has a role
  public boolean getRoleStatus(){
    return hasRole;
  }

  //Getter: getRoleRank
  //Purpose: return the role of the rank the player is acting on
  public int getRoleRank(){
    int rank = playerRole.getRoleRank();
    return rank;
  }

  //Getter: getPlayerCredits
  public int getPlayerCredits(){
    return this.playerCredits;
  }

  //Getter: getPlayerDollars
  //Purpose: return the amount of dollars for each player
  public int getPlayerDollars(){
    return this.playerDollars;
  }

  //Getter: getPlayerColor
  //Purpose: determine the player ID(color)
  public String getPlayerColor(){
    return this.playerColor;
  }

  //Getter: getRoleName
  //Purpose: get the name of the role the player is acting on
  public String getRoleName(){
    return playerRole.getRoleTitle();
  }
//*********************************** Player's Action Options  *****************************************

  //Method: move()
  //Purpose: Allow player to move to a another room
          // can only move to adj room
          // can only move if they dont have a current role (wokring)
  public int move(Rooms room){
    if(!hasRole){
      this.playerLocation = room;
      return 1;
    }
    else{
      System.out.println("Cannot move while working on a scene");   //otherwise player has a role and cannot move
      return 0;
    }
  }

  //Method: work()
  //Purpose: allows player to take a role
            //can take a role if they dont currently have one
            //can take a role if the role's rank is <= to their current rank
  //Input: Role, int
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

  //Method: act()
  //Purpose: allows player to act (roll dice) and determine successful act or not
            //can only act if they have a role
            //success if roll outcome >= budget on the role's card
            //pay depends on or off card
  //Return: int
           //1 - if the act was a success
           //0 - if the act was a failure
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

  //Method: upgrade()
  //Purpose: allow player to upgrade their rank
            //can only upgrade if they are in the Casting Offfice
            // can only upgrade if they have enough dollars or credits
  //Return: int
            //1 - if the player pays in dollars or credits
            //0 - if the player cannot upgrade
  public int upgrade(String t, int rank){
    String location = playerLocation.getRoomName();
    if(location.compareToIgnoreCase("Casting Office") == 0){
      int[] dollars = new int[5];
      int[] credits = new int[5];
      dollars[0] =  4;
      dollars[1] = 10;
      dollars[2] = 18;
      dollars[3] = 28;
      dollars[4] = 40;
      credits[0] =  5;
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

  //Method:  rehearse()
  //Purpose: allow player to rehearse before they act
             //can only rehearse and get up to 6 rehearsal credits
  public int rehearse(){
    if(playerReheasalCredits < 6){
      if(hasRole){
        playerReheasalCredits++;
        return 1;
      }
    }
    return 0;
  }

  //Method:  wraps()
  //Purpose: When a scene wraps update/reset the corresponding attributes
  //Input:   int (bonus)
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

  //Method:  newDay()
  //Purpose: reset/update correspinding attributes each day
  //Input:   Room object
  //Output:  none
  public void newDay(Rooms trailer){
    this.hasRole = false;
    this.roleRank = 0;
    this.roleBudget = 0;
    this.playerLocation = trailer;
    this.playerRole = null;
    this.onOffCard = "none";
    this.playerReheasalCredits = 0;
  }





}
