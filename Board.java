import java.awt.*;
import java.awt.event.*;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Color;
import javax.swing.JButton;

public class Board extends JLayeredPane {
  private JLabel boardLabel;
  private JLabel name;
  private JLabel credits;
  private JLabel dollars;
  private JLabel rehearsalCredits;
  private JButton act;
  private JButton move;
  private JButton work;
  private JButton rehearse;
  private JButton upgrade;
  private JButton end;
  private JLabel errorMessage;
  private JLabel cardCover;

  private JLabel blueJLabel;
  private JLabel redJLabel;
  private JLabel greenJLabel;
  private JLabel orangeJLabel;



  //throws excpetion incase jpg isn't there. best way to handle error

  public Board() throws Exception {

  }
  public void makeBoard() throws Exception{
    boardLabel = new JLabel();

    Class cls = getClass();
    ImageIcon icon =
      new ImageIcon(ImageIO.read(cls.getResourceAsStream("board.jpg")));

    boardLabel.setIcon(icon);

    //x, y, width, height
    boardLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

    add(boardLabel, new Integer(0));
    //name = new JLabel();
  }

  public void coverCard(int x, int y, int w, int h){
    cardCover = new JLabel();
    cardCover.setBounds(x, y, w, h);
    cardCover.setOpaque(true);
    cardCover.setText("Set Wrapped");
    add(cardCover, new Integer(2));
  }

  public void removeCovers(){
    remove(2);
  }

  public void addImage(String imageName, int xValue, int yValue, int wValue, int hValue) throws Exception{
    Class cls = getClass();
    ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(imageName)));
    JLabel newPic = new JLabel();
    newPic.setIcon(image);
    newPic.setBounds(xValue, yValue, wValue, hValue);
    add(newPic, new Integer(1));
  }

  public void displayErrorMessage(String message){
    this.errorMessage.setText("<html>" + message + "</html>");
    this.errorMessage.setBounds(1210,130, 150, 250);
  }




  public void setUpPlayerInfo(){
    this.name = new JLabel();
    this.credits = new JLabel();
    this.dollars = new JLabel();
    this.rehearsalCredits = new JLabel();
    this.errorMessage = new JLabel();

    add(name, new Integer(0));
    add(credits, new Integer(0));
    add(dollars, new Integer(0));
    add(rehearsalCredits, new Integer(0));
    add(errorMessage, new Integer(1));

  }

  public void addPlayerInfo(String color, int crd, int dol, int rhal){

    this.name.setText("Player: " + color.toUpperCase());
    this.name.setBounds(1210,10, 150, 15);

    this.credits.setText("Players Credits: " + crd);
    this.credits.setBounds(1210, 40, 150, 15);

    this.dollars.setText("Players Dollars " + dol);
    this.dollars.setBounds(1210, 70, 150, 15);

    this.rehearsalCredits.setText("<html>" + "Players Rehearsal Credits " + rhal + "</html");
    this.rehearsalCredits.setBounds(1210, 100, 150, 30);

  }

  public void moveDice(String color, int x, int y, int w, int h){
    String blue = "blue";
    String red = "red";
    String green = "green";
    String orange = "orange";
    if(color.compareToIgnoreCase(blue) == 0){
      this.blueJLabel.setBounds(x, y, w, h);
    }
    if(color.compareToIgnoreCase(red) == 0){
      this.redJLabel.setBounds(x, y, w, h);
    }
    if(color.compareToIgnoreCase(green) == 0){
      this.greenJLabel.setBounds(x, y, w, h);
    }
    if(color.compareToIgnoreCase(orange) == 0){
      this.orangeJLabel.setBounds(x, y, w, h);
    }
  }

  public void makeDice(int numPlayers) throws Exception{
    blueJLabel = new JLabel();
    redJLabel = new JLabel();
    greenJLabel = new JLabel();
    orangeJLabel = new JLabel();


    blueRank(1);
    redRank(1);

    add(blueJLabel, new Integer(3));
    add(redJLabel, new Integer(3));

    if(numPlayers == 3){
      greenRank(1);

      add(greenJLabel, new Integer(3));
    }

    if(numPlayers == 4){
      greenRank(1);
      orangeRank(1);

      add(greenJLabel, new Integer(3));
      add(orangeJLabel, new Integer(3));
    }
  }

  public void blueRank(int i)throws Exception{
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b1.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(1000, 350, image.getIconWidth(), image.getIconHeight());
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b2.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b3.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b4.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b5.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b6.png")));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }
  }

  public void redRank(int i)throws Exception{
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r1.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(1050, 350, image.getIconWidth(), image.getIconHeight());
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r2.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r3.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r4.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r5.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("r6.png")));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }
  }

  public void greenRank(int i)throws Exception{
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g1.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(1100, 350, image.getIconWidth(), image.getIconHeight());
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g2.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g3.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g4.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g5.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("g6.png")));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }
  }

  public void orangeRank(int i)throws Exception{
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o1.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(1150, 350, image.getIconWidth(), image.getIconHeight());
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o2.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o3.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o4.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o5.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("o6.png")));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(150, 550, image.getIconWidth(), image.getIconHeight());
    }
  }



}
