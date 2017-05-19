import javax.swing.JFrame;
import java. awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display{

  //Makes program stop running when window is closed
  private  class Closer extends WindowAdapter{
    public void windowClosing (WindowEvent e){
      System.exit(0);
    }
  }

  public Display(){
    
  }

  //creates a new window/frame    throws for in board....
  public void makeDisplay() throws Exception{
    JFrame frame = new JFrame();
    Board board = new Board();

    frame.setTitle("Deadwood");
    frame.setPreferredSize(new Dimension(1200,900)); //not right dimension
    frame.setResizable(false);
    frame.addWindowListener(new Closer());

    frame.add(board);

    frame.pack();
    frame.setVisible(true);
  }
}
