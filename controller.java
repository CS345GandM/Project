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
import java.awt.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import javax.swing.JLabel;


public class controller{
  private String command = " ";
  private JPanel buttonPanel= new JPanel();
  private JPanel movePanel = new JPanel();
  private JButton act;
  private JButton move;
  private JButton work;
  private JButton rehearse;
  private JButton upgradeDollars;
  private JButton upgradeCredits;
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
    buttonPanel.setBounds(1225, 400, 175, 300);

    movePanel.setSize(200, 500);
    movePanel.setBounds(1210, 630, 180, 75);


    Deadwood2 deadwood = new Deadwood2();


    int numPlayers = Integer.parseInt(args[0]);
    int totalScenes = 22;
    int remainingScenes = totalScenes;

    display.makeBoard(); //Board LayeredPane
    display.setUpPlayerInfo(); //Player LayeredPane
    frame.add(buttonPanel);
    frame.add(movePanel);
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

    String room = "";
    while(numDays > 0){
      deadwood.associateCards(display);
      while(remainingScenes > 1){//checks day
        for(Player x : players){//loops through players
          display.displayErrorMessage(" ");
          ArrayList<String> hasDone = new ArrayList<String>();
          boolean turn = true;
          int result = 0;
          while(turn){//still have a turn
            if(remainingScenes > 1){//the day is not over
              //display player info
              String color = x.getPlayerColor();
              int dollars = x.getPlayerDollars();
              int credits = x.getPlayerCredits();
              int rehearsalCredits = x.getPlayerRehearsalCredits();
              display.addPlayerInfo(color, credits, dollars, rehearsalCredits);

              if(command.compareToIgnoreCase("act") == 0){
                if(!hasDone.contains(command)){
                  command = " ";

                  result = deadwood.act(x, display);
                  if(result == 1){
                    remainingScenes--;
                    hasDone.add("act");
                    hasDone.add("move");
                    hasDone.add("work");
                    hasDone.add("rehearse");
                    hasDone.add("upgrade");
                    //display.displayErrorMessage(" ");
                  }else{
                    //display.displayErrorMessage("Cannot do that Action");
                  }
                }else{
                  display.displayErrorMessage("Cannot do that Action");
                }

              }else if(command.compareToIgnoreCase("move") == 0){
                if(!hasDone.contains(command)){
                  command = " ";

                  result = deadwood.move(x, desiredDest, display);
                  if(result == 1){
                    hasDone.add("act");
                    hasDone.add("move");
                    hasDone.add("rehearse");
                    display.displayErrorMessage(" ");
                  }else{
                    //display.displayErrorMessage(" ");
                  }
                }else{
                  display.displayErrorMessage("Cannot do that Action");
                }
              }else if(command.compareToIgnoreCase("work") == 0){
                if(!hasDone.contains(command)){
                  command = " ";

                  result = deadwood.work(x, desiredRole, display);
                  if(result == 1){
                    hasDone.add("act");
                    hasDone.add("move");
                    hasDone.add("work");
                    hasDone.add("rehearse");
                    hasDone.add("upgrade");
                    display.displayErrorMessage(" ");
                  }else{
                    //display.displayErrorMessage("Cannot do that Action");
                  }
                }else{
                  display.displayErrorMessage("Cannot do that Action");
                }
              }else if(command.compareToIgnoreCase("upgrade") == 0){
                if(!hasDone.contains(command)){
                  command = " ";

                  if(type.compareToIgnoreCase("cr") == 0){
                    result = deadwood.upgradeCredits(x, up, display);
                  }else{
                    result = deadwood.upgradeDollars(x, up, display);
                  }
                  if(result == 1){
                    hasDone.add("act");
                    hasDone.add("rehearse");
                    hasDone.add("upgrade");
                    display.displayErrorMessage(" ");
                  }else{
                    //display.displayErrorMessage("Cannot do that Action");
                  }
                }else{
                  display.displayErrorMessage("Cannot do that Action");
                }

              }else if(command.compareToIgnoreCase("rehearse") == 0){
                if(!hasDone.contains(command)){
                  command = " ";

                  result = deadwood.rehearse(x, display);
                  if(result == 1){
                    hasDone.add("act");
                    hasDone.add("move");
                    hasDone.add("work");
                    hasDone.add("rehearse");
                    hasDone.add("upgrade");
                    display.displayErrorMessage(" ");
                  }else{
                    //display.displayErrorMessage("Cannot do that Action");
                  }
                }else{
                  display.displayErrorMessage("Cannot do that Action");
                }
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
      display.removeCovers();
      deadwood.resetGame(room);
      remainingScenes = totalScenes;
      numDays--;
    }

    //display final scores
    String message = "";
    for(Player p : players){
      message = message + "<br>" + deadwood.finalScore(p, display);
    }
    display.displayErrorMessage(message);

  }

  public void callAct(){
    command = "act";
  }

  public void callMove(){

    getDesiredDest = new JTextField(10);
    JLabel info = new JLabel();
    info.setText("Enter room name:");
    movePanel.add(info);
    movePanel.add(getDesiredDest);

    frame.setVisible(true);

    getDesiredDest.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e){
        desiredDest = getDesiredDest.getText();
        movePanel.remove(getDesiredDest);
        movePanel.remove(info);
        frame.revalidate();
        frame.repaint();
        command = "move";
        System.out.println(desiredDest);
        System.out.println("HERE");

      }
    });
  }



  public void callUpgrade(){
    getDesiredDest = new JTextField(10);
    JLabel info = new JLabel();
    info.setText("Enter # 2-6:");
    movePanel.add(info);

    movePanel.add(getDesiredDest);

    frame.setVisible(true);

    getDesiredDest.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e){
        up = Integer.parseInt(getDesiredDest.getText());
        movePanel.remove(getDesiredDest);
        movePanel.remove(info);
        frame.revalidate();
        frame.repaint();
        command = "upgrade";
        System.out.println(up);
        System.out.println("HERE");
      }
    });
  }

  public void callEndTurn(){
    command = "end";
  }

  public void callRehearse(){
    command = "rehearse";
  }

  public void callWork(){
    getDesiredDest = new JTextField(10);
    JLabel info = new JLabel();
    info.setText("Enter role name:");
    movePanel.add(info);

    movePanel.add(getDesiredDest);

    frame.setVisible(true);

    getDesiredDest.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e){
        desiredRole = getDesiredDest.getText();
        movePanel.remove(getDesiredDest);
        movePanel.remove(info);
        frame.revalidate();
        frame.repaint();
        command = "work";
        System.out.println(desiredDest);
        System.out.println("HERE");

      }
    });
  }

  private void makeButtons(){

    act = new JButton("Act");
    act.addMouseListener(new CustomMouseListener("act"));
    act.setBackground(Color.green);
    act.setBounds(1210, 300, 150, 30);

    move = new JButton("Move");
    move.addMouseListener(new CustomMouseListener("move"));
    move.setBackground(Color.gray);
    move.setBounds(1210, 350, 150, 30);

    work = new JButton("Work");
    work.addMouseListener(new CustomMouseListener("work"));
    work.setBackground(Color.red);
    work.setBounds(1210, 400, 150, 30);

    rehearse = new JButton("Rehearse");
    rehearse.addMouseListener(new CustomMouseListener("rehearse"));
    rehearse.setBackground(Color.pink);
    rehearse.setBounds(1210, 450, 150, 30);

    upgradeDollars = new JButton("Upgrade w/ Dollars");
    upgradeDollars.addMouseListener(new CustomMouseListener("upgradeDollars"));
    upgradeDollars.setBackground(Color.orange);
    upgradeDollars.setBounds(1210, 500, 150, 30);

    upgradeCredits = new JButton("Upgrade w/ Credits");
    upgradeCredits.addMouseListener(new CustomMouseListener("upgradeCredits"));
    upgradeCredits.setBackground(Color.orange);
    upgradeCredits.setBounds(1210, 550, 150, 30);

    end = new JButton("End Turn");
    end.addMouseListener(new CustomMouseListener("end"));
    end.setBackground(Color.cyan);
    end.setBounds(1210, 600, 150, 30);

    buttonPanel.add(act);
    buttonPanel.add(move);
    buttonPanel.add(work);
    buttonPanel.add(rehearse);
    buttonPanel.add(upgradeDollars);
    buttonPanel.add(upgradeCredits);
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
          case "upgradeDollars":
          type = "$";
          callUpgrade();
          System.out.println("CLICKED UPGRADE");
          break;
          case "upgradeCredits":
          type = "cr";
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
