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

  public void act(){

  }

  public void upgrade(String t, int rank){


  }

  public void rehearse(){

  }
