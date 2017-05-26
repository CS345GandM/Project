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
    Board display = new Board();
    JFrame frame = new JFrame();


    Deadwood2 deadwood = new Deadwood2();

    int numPlayers = Integer.parseInt(args[0]);
    int totalScenes = 22;
    int remainingScenes = totalScenes;

    display.makeBoard(); //Board LayeredPane
    display.setUpPlayerInfo(); //Player LayeredPane

    display.makeDice(numPlayers);

    frame.setTitle("Deadwood");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1400,900)); //not right dimension
    frame.setResizable(false);

    frame.getContentPane().add(display);

    //JPanel act = new JPanel();
    //act.setlovation();

    //JPanel move = new JPanel();



    //display.makeDice(numPlayers);

    frame.pack();
    frame.setVisible(true);


    int numDays = deadwood.setDays(numPlayers);
    ArrayList<Player> players = deadwood.makePlayers(numPlayers);
    deadwood.board();
    deadwood.cards();
    deadwood.makeGame(display);

    String room = "";
    while(numDays > 0){
      deadwood.associateCards(display);
      while(remainingScenes > totalScenes - 1){
        for(Player x : players){
          if(remainingScenes > totalScenes - 1){

            String color = x.getPlayerColor();
            int dollars = x.getPlayerDollars();
            int credits = x.getPlayerCredits();
            int rehearsalCredits = x.getPlayerRehearsalCredits();
            display.addPlayerInfo(color, credits, dollars, rehearsalCredits);
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

}


class CustomMouseListener implements MouseListener {
   public void mouseClicked(MouseEvent e) {
     //display.addListeners(CustomMouseListener)
      String button;
      switch(button){
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
