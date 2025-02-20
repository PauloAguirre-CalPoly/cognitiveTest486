import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Paulo Aguirre and Tanner Tran
 */
public class GameMain extends JFrame {

  public GameMain() throws IOException {

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

  public static void main(String[] args) throws IOException {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      saveDataToFile("output.txt", "Test");
    }));

    GameMain main = new GameMain();
    main.setTitle("Towers of Hanoi");
    main.setSize(1600, 1000 );
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }

  private static void saveDataToFile(String filePath, String data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      int i = 0;
      while(GameData.getInstance().getData().get(i) != null) {
        writer.write(GameData.getInstance().getData().get(i) + "\n");
        i++;
      }
     // writer.write(data);
      System.out.println("Data saved to " + filePath);
    } catch (IOException e) {
      System.err.println("Error saving data to file: " + e.getMessage());
    }
  }

}
