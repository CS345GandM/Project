import java.util.*;
import java.lang.*;

public class Player{

  //differentiate between players
  private String playerColor;  //ID
  private boolean hasRole;
  private Room playerLocation;
  private int playerCredits;
  private int playerDollars;
  private int playerReheasalCredits;
  private int roleRank;
  private Card roleBudget;
  private Role playerRole;

  //constructor for player with a certain color(ID)
  public Player(String playerColor){
    this.playerColor = playerColor;
    this.hasRole = false;
    this.playerCredits = 0;
    this.playerDollars = 0;
    this.roleRank = 0;
    this.roleBudget = roleBudget;
    this.playerLocation = playerLocation;
  }

  public void wraps(){
    //if on card
    this.playerReheasalCredits = 0;
    this.hasRole = false;
    this.playerDollars = 0;
    //if off card
  }

  //GETTER: LOCATION
  public void getPlayerLocation(){
    return playerLocation.getRoomName();
  }
  //SETTER: PLAYER RANK
  public void setPlayerRank(int rank){
    playerRank = rank;
    return playerRank;
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
  public void getRoleRank(){
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
  ////////////////////////////////////////////////////Move
  ////////////////////////////////////////////////////Take role
  public void act(){
    if(hasRole){
        Dice newDice = new Dice();
        int roll = newDice.getValue();
        if(roleBudget.getBudget() <= roll){
          //SUCCESS
        }else{
          //FAIL
        }
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
          playerCredits = playerCredits - credits[rank - 1];
          return 1;
        }
      }
    }
    return 0;
  }

  public void rehearse(){
    if(rehersalCredits < 6){
      if(hasRole){
        playerReheasalCredits++;
        return 1;
      }
    }
    return 0;
  }
