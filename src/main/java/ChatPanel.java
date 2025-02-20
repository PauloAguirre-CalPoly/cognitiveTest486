import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatPanel extends JPanel {

  private ArrayList<JLabel> labels;

  public ChatPanel() {
    setBackground(Color.black);
    setLayout(new GridLayout(0,1));
    labels = new ArrayList<>();
    gameStart();
  }

  public void gameStart(){
    JLabel testLabel = new JLabel("<html>Hello Player<br/><br/>" +
            "Welcome to Towers of Hanoi!!<br/><br/>" + "Move all four disk to another pole to win!!<br/><br/>"+
            "Rules:<br/>" +
            "1. Press and drag a disk to another pole.<br/>" +
            "2. Only one disk can be moved at a time.<br/>" +
            "3. A larger disk cannot be placed on top of a small one.</html>");
    testLabel.setFont(new Font("Serif", Font.BOLD, 15));
    testLabel.setForeground(Color.GREEN);
    add(testLabel);
    JLabel chatPlaceHolder = new JLabel("<html>> Have Fun!!!</html>");
    chatPlaceHolder.setFont(new Font("Serif", 0, 17));
    chatPlaceHolder.setForeground(Color.CYAN);
    add(chatPlaceHolder);
    labels.add(chatPlaceHolder);
  }
  public void moveMSG(boolean move){
    remove(1);
    labels.remove(0);
    if(move){
      JLabel tLabel = new JLabel("<html>>Great move!!</html>");
      tLabel.setFont(new Font("Serif", 0, 17));
      tLabel.setForeground(Color.CYAN);
      add(tLabel);
      labels.add(tLabel);
    }else{
      JLabel tLabel = new JLabel("<html>>Can't Move there!!</html>");
      tLabel.setFont(new Font("Serif", 0, 17));
      tLabel.setForeground(Color.ORANGE);
      add(tLabel);
      labels.add(tLabel);
    }
    revalidate();
  }
  public void playerWinMSG(){
    remove(labels.get(0));
    labels.remove(0);
    //labels.remove(0);
    JLabel wLabel = new JLabel("<html>You win!!</html>");
    wLabel.setFont(new Font("Serif", 0, 34));
    wLabel.setForeground(Color.YELLOW);
    add(wLabel);
    labels.add(wLabel);
    revalidate();
  }
}
