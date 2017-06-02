import java.awt.*;
import java.awt.event.*;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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

  private JLabel mainStreet1;
  private JLabel mainStreet2;
  private JLabel mainStreet3;
  private JLabel saloon1;
  private JLabel saloon2;
  private JLabel hotel1;
  private JLabel hotel2;
  private JLabel hotel3;
  private JLabel church1;
  private JLabel church2;
  private JLabel bank1;
  private JLabel secretHideout1;
  private JLabel secretHideout2;
  private JLabel secretHideout3;
  private JLabel ranch1;
  private JLabel ranch2;
  private JLabel generalStore1;
  private JLabel generalStore2;
  private JLabel jail1;
  private JLabel trainStation1;
  private JLabel trainStation2;
  private JLabel trainStation3;



  //throws excpetion incase jpg isn't there. best way to handle error

  public Board() throws Exception {

  }
  public void addShotCounters() throws Exception{
    Class cls = getClass();
    ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream("shot.png")));

    mainStreet1 = new JLabel();
    mainStreet1.setIcon(image);
    mainStreet1.setBounds(804, 23, image.getIconWidth(), image.getIconHeight());
    add(mainStreet1, new Integer(3));

    mainStreet2 = new JLabel();
    mainStreet2.setIcon(image);
    mainStreet2.setBounds(858, 23, image.getIconWidth(), image.getIconHeight());
    add(mainStreet2, new Integer(3));

    mainStreet3 = new JLabel();
    mainStreet3.setIcon(image);
    mainStreet3.setBounds(912, 23, image.getIconWidth(), image.getIconHeight());
    add(mainStreet3, new Integer(3));

    saloon1 = new JLabel();
    saloon1.setIcon(image);
    saloon1.setBounds(679, 216, image.getIconWidth(), image.getIconHeight());
    add(saloon1, new Integer(3));

    saloon2 = new JLabel();
    saloon2.setIcon(image);
    saloon2.setBounds(626, 216, image.getIconWidth(), image.getIconHeight());
    add(saloon2, new Integer(3));

    hotel1 = new JLabel();
    hotel1.setIcon(image);
    hotel1.setBounds(1111, 683, image.getIconWidth(), image.getIconHeight());
    add(hotel1, new Integer(3));

    hotel2 = new JLabel();
    hotel2.setIcon(image);
    hotel2.setBounds(1058, 683, image.getIconWidth(), image.getIconHeight());
    add(hotel2, new Integer(3));

    hotel3 = new JLabel();
    hotel3.setIcon(image);
    hotel3.setBounds(1005, 683, image.getIconWidth(), image.getIconHeight());
    add(hotel3, new Integer(3));

    church1 = new JLabel();
    church1.setIcon(image);
    church1.setBounds(682, 675, image.getIconWidth(), image.getIconHeight());
    add(church1, new Integer(3));

    church2 = new JLabel();
    church2.setIcon(image);
    church2.setBounds(623, 675, image.getIconWidth(), image.getIconHeight());
    add(church2, new Integer(3));

    bank1 = new JLabel();
    bank1.setIcon(image);
    bank1.setBounds(840, 549, image.getIconWidth(), image.getIconHeight());
    add(bank1, new Integer(3));

    secretHideout1 = new JLabel();
    secretHideout1.setIcon(image);
    secretHideout1.setBounds(354, 764, image.getIconWidth(), image.getIconHeight());
    add(secretHideout1, new Integer(3));

    secretHideout2 = new JLabel();
    secretHideout2.setIcon(image);
    secretHideout2.setBounds(299, 764, image.getIconWidth(), image.getIconHeight());
    add(secretHideout2, new Integer(3));

    secretHideout3 = new JLabel();
    secretHideout3.setIcon(image);
    secretHideout3.setBounds(244, 764, image.getIconWidth(), image.getIconHeight());
    add(secretHideout3, new Integer(3));

    ranch1 = new JLabel();
    ranch1.setIcon(image);
    ranch1.setBounds(525, 473, image.getIconWidth(), image.getIconHeight());
    add(ranch1, new Integer(3));

    ranch2 = new JLabel();
    ranch2.setIcon(image);
    ranch2.setBounds(472, 473, image.getIconWidth(), image.getIconHeight());
    add(ranch2, new Integer(3));

    generalStore1 = new JLabel();
    generalStore1.setIcon(image);
    generalStore1.setBounds(313, 330, image.getIconWidth(), image.getIconHeight());
    add(generalStore1, new Integer(3));

    generalStore2 = new JLabel();
    generalStore2.setIcon(image);
    generalStore2.setBounds(313, 277, image.getIconWidth(), image.getIconHeight());
    add(generalStore2, new Integer(3));

    jail1 = new JLabel();
    jail1.setIcon(image);
    jail1.setBounds(442, 156, image.getIconWidth(), image.getIconHeight());
    add(jail1, new Integer(3));

    trainStation1 = new JLabel();
    trainStation1.setIcon(image);
    trainStation1.setBounds(141, 11 , image.getIconWidth(), image.getIconHeight());
    add(trainStation1, new Integer(3));

    trainStation2 = new JLabel();
    trainStation2.setIcon(image);
    trainStation2.setBounds(89, 11, image.getIconWidth(), image.getIconHeight());
    add(trainStation2, new Integer(3));

    trainStation3 = new JLabel();
    trainStation3.setIcon(image);
    trainStation3.setBounds(36, 11, image.getIconWidth(), image.getIconHeight());
    add(trainStation3, new Integer(3));
  }
  public void showShotCounters(){
    mainStreet1.setVisible(true);
    mainStreet2.setVisible(true);
    mainStreet3.setVisible(true);
    saloon1.setVisible(true);
    saloon2.setVisible(true);
    hotel1.setVisible(true);
    hotel2.setVisible(true);
    hotel3.setVisible(true);
    church1.setVisible(true);
    church2.setVisible(true);
    bank1.setVisible(true);
    jail1.setVisible(true);
    trainStation1.setVisible(true);
    trainStation2.setVisible(true);
    trainStation3.setVisible(true);
    generalStore1.setVisible(true);
    ranch1.setVisible(true);
    ranch2.setVisible(true);
    secretHideout1.setVisible(true);
    secretHideout2.setVisible(true);
    secretHideout3.setVisible(true);
  }

  public void removeShotCounter(String roomName, int remainingShots){
    remainingShots++; //gives the right number of which to remove

    if(roomName.compareToIgnoreCase("Main Street") == 0){
      if(remainingShots == 1){
        mainStreet1.setVisible(false);
      }else if(remainingShots == 2){
        mainStreet2.setVisible(false);
      }else if(remainingShots == 3){
        mainStreet3.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Saloon") == 0){
      if(remainingShots == 1){
        saloon1.setVisible(false);
      }else if(remainingShots == 2){
        saloon2.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Hotel") == 0){
      if(remainingShots == 1){
        hotel1.setVisible(false);
      }else if(remainingShots == 2){
        hotel2.setVisible(false);
      }else if(remainingShots == 3){
        hotel3.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Church") == 0){
      if(remainingShots == 1){
        church1.setVisible(false);
      }else if(remainingShots == 2){
        church2.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Bank") == 0){
      bank1.setVisible(false);
    }
    if(roomName.compareToIgnoreCase("Jail") == 0){
      jail1.setVisible(false);
    }
    if(roomName.compareToIgnoreCase("Train Station") == 0){
      if(remainingShots == 1){
        trainStation1.setVisible(false);
      }else if(remainingShots == 2){
        trainStation2.setVisible(false);
      }else if(remainingShots == 3){
        trainStation3.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("General Store") == 0){
      if(remainingShots == 1){
        generalStore1.setVisible(false);
      }else if(remainingShots == 2){
        generalStore2.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Ranch") == 0){
      if(remainingShots == 1){
        ranch1.setVisible(false);
      }else if(remainingShots == 2){
        ranch2.setVisible(false);
      }
    }
    if(roomName.compareToIgnoreCase("Secret Hideout") == 0){
      if(remainingShots == 1){
        secretHideout1.setVisible(false);
      }else if(remainingShots == 2){
        secretHideout2.setVisible(false);
      }else if(remainingShots == 3){
        secretHideout3.setVisible(false);
      }
    }
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
    cardCover.setText("      SET WRAPPED!  ");
    cardCover.setForeground(Color.red);
    cardCover.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 16));
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
