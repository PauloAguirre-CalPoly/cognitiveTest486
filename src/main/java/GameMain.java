import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


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

  public static void main(String[] args) throws IOException {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      saveDataToFile("appData.csv", GameData.getInstance().getAppData());
    }));
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      saveDataToFile("devData.csv", GameData.getInstance().getDeviceData());
    }));

    Subscriber sub = new Subscriber();
    Publisher pub = new Publisher();

    Thread modelThread1 = new Thread(sub);
    Thread modelThread2 = new Thread(pub);

    modelThread2.start();
    modelThread1.start();

    GameMain main = new GameMain();

    main.setTitle("Towers of Hanoi");
    main.setSize(1600, 1000 );
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }

  private static void saveDataToFile(String appData, ArrayList<String> list) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(appData))) {
      int i = 0;
      //GameData.getInstance().getAppData().get(i) != null
      while(list.get(i) != null) {
        writer.write(list.get(i) + "\n");
        i++;
      }
     // writer.write(data);
      System.out.println("Data saved to " + appData);
    } catch (IOException e) {
      System.err.println("Error saving data to file: " + e.getMessage());
    }

  }


}
