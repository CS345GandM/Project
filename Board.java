
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


  public void addPlayerInfo(String color, int crd, int dol){
    name = new JLabel();
    name.setText("Player: " + color);
    name.setBounds(1210,0, 150, 150);

    credits = new JLabel("Players credits: " + crd);
    credits.setBounds(1210, 50, 150, 150);

    dollars = new JLabel("Players dollars " + dol);
    dollars.setBounds(1210, 100, 150, 150);

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

}
