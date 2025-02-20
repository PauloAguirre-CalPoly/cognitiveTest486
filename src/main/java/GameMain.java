import javax.swing.*;
import java.awt.*;


/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Paulo Aguirre and Tanner Tran
 */
public class GameMain extends JFrame {

  public GameMain() {

    TowerPanel towerPanel = new TowerPanel();
    ChatPanel chatPanel = new ChatPanel();
    JPanel rightPanel = new JPanel();

    rightPanel.setLayout (new GridLayout(1,2));
    rightPanel.add(towerPanel);

    this.setLayout (new GridLayout(2,1));
    add(rightPanel);
    add(chatPanel);



    GameController controller = new GameController(chatPanel);

    towerPanel.addMouseListener(controller);
    towerPanel.addMouseMotionListener(controller);
    towerPanel.addComponentListener(controller);

    GameData.getInstance().setnDisks(4);
    GameData.getInstance().setSize(this.getWidth(), this.getHeight());
    GameData.getInstance().addPropertyChangeListener(towerPanel);
    //I added this code
    for(Tower tw : GameData.getInstance().getTowers()){
      if(tw.getLastDiskWidth() == 0){
        for(Disk dk : GameData.getInstance().getDisks()){
          tw.dropDisk(dk);
        }
        break;
      }
    }
  }

  public static void main(String[] args) {

    GameMain main = new GameMain();
    main.setTitle("Towers of Hanoi");
    main.setSize(1600, 1000 );
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }
}
