
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Color;
import javax.swing.JButton;

public class Board extends JLayeredPane {
  private JLabel boardLabel;
  public JLabel name;
  public JLabel credits;
  public JLabel dollars;
  public JButton act;
  public JButton move;
  public JButton work;
  public JButton rehearse;
  public JButton upgrade;
  public JButton end;

  JLabel blueJLabel;
  JLabel redJLabel;
  JLabel greenJLabel;
  JLabel orangeJLabel;

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

  public void addImage(String imageName, int xValue, int yValue, int wValue, int hValue) throws Exception{
    Class cls = getClass();
    ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(imageName)));
    JLabel newPic = new JLabel();
    newPic.setIcon(image);
    newPic.setBounds(xValue, yValue, wValue, hValue);
    //JPanel pan = new JPanel();
    //pan.add(newPic, new);
    add(newPic, new Integer(1));
  }

  public void setUpPlayerInfo(){
    this.name = new JLabel();
    this.credits = new JLabel();
    this.dollars = new JLabel();

    act = new JButton("Act");
    act.setBackground(Color.green);
    act.setBounds(1210, 300, 60, 30);

    move = new JButton("Move");
    move.setBackground(Color.gray);
    move.setBounds(1210, 350, 75, 30);

    work = new JButton("Work");
    work.setBackground(Color.red);
    work.setBounds(1210, 400, 75, 30);

    rehearse = new JButton("Rehearse");
    rehearse.setBackground(Color.pink);
    rehearse.setBounds(1210, 450, 110, 30);

    upgrade = new JButton("Upgrade");
    upgrade.setBackground(Color.blue);
    upgrade.setBounds(1210, 500, 100, 30);

    end = new JButton("End Turn");
    end.setBackground(Color.cyan);
    end.setBounds(1210, 550, 100, 30);


    add(name, new Integer(0));
    add(credits, new Integer(0));
    add(dollars, new Integer(0));
    add(act, new Integer(0));
    add(move, new Integer(0));
    add(work, new Integer(0));
    add(rehearse, new Integer(0));
    add(upgrade, new Integer(0));
    add(end, new Integer(0));
  }

  public void addPlayerInfo(String color, int crd, int dol){

    this.name.setText("Player: " + color);
    this.name.setBounds(1210,0, 150, 150);

    this.credits.setText("Players credits: " + crd);
    this.credits.setBounds(1210, 50, 150, 150);

    this.dollars.setText("Players dollars " + dol);
    this.dollars.setBounds(1210, 100, 150, 150);

  }

  public void makeDice(int numPlayers){
    blueJLabel = new JLabel();
    redJLabel = new JLabel();
    greenJLabel = new JLabel();
    orangeJLabel = new JLabel();

    blueRank(1);
    redRank(1);

    add(blueJLabel, new Integer(1));
    add(redJLabel, new Integer(1));

    if(i == 3){
      greenRank(1);

      add(greenJLabel, new Integer(1));
    }

    if(i == 4){
      greenRank(1);
      orangeRank(1);

      add(greenJLabel, new Integer(1));
      add(orangeJLabel, new Integer(1));
    }
  }

  public void blueRank(int i){
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b1.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, image.getIconWidth(), image.getIconHeight());///////////////////////////////////////////////////////////////
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b2.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b3.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b4.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b5.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(b6.png)));
      this.blueJLabel.setIcon(image);
      this.blueJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }
  }

  public void redRank(int i){
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r1.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r2.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r3.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r4.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r5.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(r6.png)));
      this.redJLabel.setIcon(image);
      this.redJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }
  }

  public void greenRank(int i){
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g1.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g2.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g3.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g4.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g5.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(g6.png)));
      this.greenJLabel.setIcon(image);
      this.greenJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }
  }

  public void orangeRank(int i){
    Class cls = getClass();

    if(i == 1){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o1.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 2){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o2.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 3){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o3.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 4){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o4.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 5){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o5.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }else if(i == 6){
      ImageIcon image = new ImageIcon(ImageIO.read(cls.getResourceAsStream(o6.png)));
      this.orangeJLabel.setIcon(image);
      this.orangeJLabel.setBounds(xValue, yValue, wValue, hValue);///////////////////////////////////////////////////////////////
    }
  }



}
