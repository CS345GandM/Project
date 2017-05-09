//Melissa Gonzalez and Hannah Montague

import java.util.*;

public class Player{

  //differentiate between players
  private String playerColor;

  //to keep track of / calcualte
  private int playerCredits;
  private int playerDollars;
  private int playerRank;

  private String playerActivity;   //to check if they have a role
  private boolean moved;
  private Role playerRole;
  private int rehersalCredits;
  private int diceValue;
  private Rooms currRoom;
  private boolean turn;


   public Player(GameBoard board, int numPlayers, String color) {
     //distinguish between players
    this.playerColor = color;

 		this.playerCredits = 0;
 		this.playerDollars = 0;
 		this.playerRank = 1;

 		this.currWork = "Not currently acting";
 		this.moved = false;
 		this.playerRole = null;  //set the players role to empty
 		this.rehersalCredits = 0;
 		this.diceValue = 0;
 		this.rehearsedDiceValue = 0;
 		this.currRoom = board.getRoomByName("Trailer");
 		this.turn = false;


 	}

  public void resetBoard(GameBoard board) {
		this.state = "No current role";
		this.playerRole = null;
		this.rehersalCredits = 0;
		this.diceValue = 0;
		this.currRoom = GameBoard.getRoomName("Trailer");
		this.turn = false;
	}

  public void defaultPlayer() {
		this.moved = false;
		this.turn = false;
	}

  public void setPlayerWork(String currWork) {
		this.currWork = currWork;
	}

  public String getPlayerWork() {
    return this.currWork;
  }

  public boolean hasMoved() {
		return this.moved;
	}

	public void payPlayerBonus(int amount) {
		this.playerDollars += amount;
	}

	public void setTurn() {
		this.turn = true;
	}

	public boolean returnTurn() {
		return this.turn;
	}

	public void newRole() {
		this.playerRole = null;
	}

	public String getPlayerColor() {
		return this.playerColor;
	}

	public int getScore() {
		return this.playerDollars + this.playerCredits + (5 * this.playerRank);
	}

	public Rooms getCurrentRoom() {
		return this.currRoom;
	}

	public Role getRole() {
		return this.playerRole;
	}

  public void move(Rooms room) {
    //if players state is set to "no role" then they can move
      //if the room they gave as parameter is adj to their current room
        //update their current room to room(from this methods parameter)
        //update their move status to true
    //else the room wasnt adj---
        //print error statement
   //else - last case of not being able to move is when they are working on a role already
}
public void act(){
  return null;
}

public void rehearse(){
  return null;
  //if credits < 6
  //increment the credits
  //else cannot rehearse because they are currently working
}

public void upgrade(){
  //can only think of a way to this this by having a case for every rank and checking credits/dollars and decrementing that rank amount
  //else cannot upgrade because player.getCurrentRoom is not equal to the casting room
}



}
