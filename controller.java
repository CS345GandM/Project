import java.util.*;
import java.io.*;
import java.lang.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JLabel;


public class controller{
  private String command = " ";
  private JPanel buttonPanel= new JPanel();
  private JPanel movePanel = new JPanel();
  private JButton act;
  private JButton move;
  private JButton work;
  private JButton rehearse;
  private JButton upgrade;
  private JButton end;
  private JTextField getDesiredDest;

  private Board display;
  private JFrame frame;


  private String desiredDest;
  private String desiredRole;
  private int up;
  private String type;

  public static void main(String[] args) throws Exception{
    controller c = new controller();
    c.start(args);
  }

  private void start(String[] args) throws Exception{
    display = new Board();
    frame = new JFrame();


    buttonPanel.setSize(200, 500);
    buttonPanel.setBounds(1250, 400, 90, 300);

    movePanel.setSize(200, 500);
    movePanel.setBounds(1250, 700, 90, 200);


    Deadwood2 deadwood = new Deadwood2();


    int numPlayers = Integer.parseInt(args[0]);
    int totalScenes = 22;
    int remainingScenes = totalScenes;

    display.makeBoard(); //Board LayeredPane
    display.setUpPlayerInfo(); //Player LayeredPane
    frame.add(buttonPanel);
    makeButtons();
    display.makeDice(numPlayers);

    frame.setTitle("Deadwood");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1400,900)); //not right dimension
    frame.setResizable(false);
    //frame.add(buttonPanel, BoardLayout.EAST);
    frame.getContentPane().add(display);
    frame.pack();
    frame.setVisible(true);

    int numDays = deadwood.setDays(numPlayers);
    ArrayList<Player> players = deadwood.makePlayers(numPlayers);
    deadwood.board();
    deadwood.cards();
    deadwood.makeGame(display);

    String message = "";
    for(Player p : players){
      message = message + "<br>" + deadwood.finalScore(p, display);
    }
    display.displayErrorMessage(message);

    String room = "";
    while(numDays > 0){
      deadwood.associateCards(display);
      while(remainingScenes > 1){//checks day
        for(Player x : players){//loops through players
          boolean turn = true;
          while(turn){//still have a turn
            if(remainingScenes > 1){//the day is not over
              //display player info
              String color = x.getPlayerColor();
              int dollars = x.getPlayerDollars();
              int credits = x.getPlayerCredits();
              int rehearsalCredits = x.getPlayerRehearsalCredits();
              display.addPlayerInfo(color, credits, dollars, rehearsalCredits);

              if(command.compareToIgnoreCase("act") == 0){
                command = " ";

                int result = deadwood.act(x);
                if(result == 1){
                  remainingScenes--;
                }
              }else if(command.compareToIgnoreCase("move") == 0){
                command = " ";
                //deadwood.move(x, desiredDest, display);
              }else if(command.compareToIgnoreCase("work") == 0){
                command = " ";
                //deadwood.work(x, desiredRole, display);
              }else if(command.compareToIgnoreCase("upgradeDollars") == 0){
                command = " ";
                //deadwood.upgradeDollars(x, up, display);
              }else if(command.compareToIgnoreCase("upgradeCredits") == 0){
                command = " ";
                //deadwood.upgradeCredits(x, up, display);
              }else if(command.compareToIgnoreCase("rehearse") == 0){
                command = " ";
                //deadwood.rehearse(x, display);
              }else if(command.compareToIgnoreCase("end") == 0){
                command = " ";
                turn = false;
              }
              room = x.getPlayerLocation();
            }else{//day over
              turn = false;
            }
          }
        }
      }
      deadwood.resetGame(room);
      remainingScenes = totalScenes;
      numDays--;
    }

    for(Player x : players){
      deadwood.finalScore(x, display);
    }

  }

  public void callAct(){
    command = "act";
  }

  public void callMove(){
    //buttonPanel.setVisible(false);
    frame.add(movePanel);
    callGetDest();
    //movePanel.setSize(200, 500);
    //movePanel.setBounds(1201, 400, 90, 300);


    frame.revalidate();
    frame.repaint();
    frame.setVisible(true);


    frame.remove(movePanel);


    //buttonPanel.setSize(200, 500);
    //buttonPanel.setBounds(1201, 400, 90, 300);


    frame.revalidate();
    frame.repaint();
    //frame.pack();

    command = "move";
  }

  public void callGetDest(){

    JLabel test = new JLabel();
    test.setText("Is this working?");
    movePanel.add(test);


    getDesiredDest = new JTextField(50);
    movePanel.add(getDesiredDest);
    getDesiredDest.addActionListener(new ActionListener(){
      //System.out.println("HERE");

      public void actionPerformed(ActionEvent e){
        desiredDest = getDesiredDest.getText();
        System.out.println(desiredDest);
        System.out.println("HERE");
      }
    });
  }

  public void callUpgrade(){
    command = "upgrade";
    //call something to get upgrade type and value
    if(type.compareToIgnoreCase("$") == 0){
      command = "upgradeDollars";
    }else if(type.compareToIgnoreCase("cr") == 0){
      command = "upgradeCredits";
    }
  }

  public void callEndTurn(){
    command = "end";
  }

  public void callRehearse(){
    command = "rehearse";
  }

  public void callWork(){
    command = "work";
    //get desired role
  }

  private void makeButtons(){

    act = new JButton("Act");
    act.addMouseListener(new CustomMouseListener("act"));
    act.setBackground(Color.green);
    act.setBounds(1210, 300, 60, 30);

    move = new JButton("Move");
    move.addMouseListener(new CustomMouseListener("move"));
    move.setBackground(Color.gray);
    move.setBounds(1210, 350, 75, 30);

    work = new JButton("Work");
    work.addMouseListener(new CustomMouseListener("work"));
    work.setBackground(Color.red);
    work.setBounds(1210, 400, 75, 30);

    rehearse = new JButton("Rehearse");
    rehearse.addMouseListener(new CustomMouseListener("rehearse"));
    rehearse.setBackground(Color.pink);
    rehearse.setBounds(1210, 450, 110, 30);

    upgrade = new JButton("Upgrade");
    upgrade.addMouseListener(new CustomMouseListener("upgrade"));
    upgrade.setBackground(Color.blue);
    upgrade.setBounds(1210, 500, 100, 30);

    end = new JButton("End Turn");
    end.addMouseListener(new CustomMouseListener("end"));
    end.setBackground(Color.cyan);
    end.setBounds(1210, 550, 100, 30);

    buttonPanel.add(act);
    buttonPanel.add(move);
    buttonPanel.add(work);
    buttonPanel.add(rehearse);
    buttonPanel.add(upgrade);
    buttonPanel.add(end);


  }

  class CustomMouseListener implements MouseListener {
     private String button;
     public CustomMouseListener(String name){
      button = name;
     }
     public void mouseClicked(MouseEvent e) {
          switch(button){
          case "act":
          callAct();
          System.out.println("CLICKED ACT");
          break;
          case "move":
          callMove();
          System.out.println("CLICKED MOVE");
          break;
          case "work":
          callWork();
          System.out.println("CLICKED WORK");
          break;
          case "upgrade":
          callUpgrade();
          System.out.println("CLICKED UPGRADE");
          break;
          case "rehearse":
          callRehearse();
          System.out.println("CLICKED REHEARSE");
          break;
          case "end":
          callEndTurn();
          System.out.println("CLICKED END");
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
