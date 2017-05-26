import java.util.*;
import java.io.*;
import java.lang.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class controller{

  public static void main(String[] args) throws Exception{
    board = new Board();

    board.makeBoard(); //Board LayeredPane
    board.setUpPlayerInfo(); //Player LayeredPane

    board.makeDice(numPlayers);

    frame.setTitle("Deadwood");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1400,900)); //not right dimension
    frame.setResizable(false);

    frame.getContentPane().add(board);

    //JPanel act = new JPanel();
    //act.setlovation();

    //JPanel move = new JPanel();



    //board.makeDice(numPlayers);

    frame.pack();
    frame.setVisible(true);
    Deadwood2 deadwood = new Deadwood2();

    int numPlayers = Integer.parseInt(args[0]);
    int totalScenes = 22;
    int remainingScenes = totalScenes;

    int numDays = deadwood.setDays(numPlayers);
    ArrayList<Player> players = deadwood.makePlayers(numPlayers);
    deadwood.board();
    deadwood.cards();
    deadwood.makeGame(Board board);

    String room = "";
    while(numDays > 0){
      deadwood.associateCards(Board board);
      while(remainingScenes > totalScenes - 1){
        for(Player x : players){
          if(remainingScenes > totalScenes - 1){

            String color = x.getPlayerColor();
            int dollars = x.getPlayerDollars();
            int credits = x.getPlayerCredits();
            int rehearsalCredits = x.getPlayerRehearsalCredits();
            //board.addPlayerInfo(color, credits, dollars, rehearsalCredits);
            //deadwood.act(x);
            //deadwood.
            /////////////////////////////////////turns
            room = x.getPlayerLocation();
          }
        }
      }
      deadwood.resetGame(room);
      remainingScenes = totalScenes;
      numDays--;
    }

    for(Player x : players){
      deadwood.finalScore(x);
    }

  }

  class CustomMouseListener implements MouseListener {
     public void mouseClicked(MouseEvent e) {
       board.addListeners(CustomMouseListener)
        String button;
        swtich(button){
          case "act":
          deadwood.act();
          System.out.println("CLICKED ACT");
          break;
          default:
          System.out.println("ERROR NO BUTTON");
        }

     }
     public void mousePressed(MouseEvent e) {
     }
     public void mouseReleased(MouseEvent e) {
     }
     public void mouseEntered(MouseEvent e) {
     }
     public void mouseExited(MouseEvent e) {
     }
  }






}
