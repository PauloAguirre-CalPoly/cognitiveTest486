import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The controller for the Towers of Hanoi game.
 * It implements MouseListener, MouseMotionListener, and ComponentListener to handle mouse events and resizing.
 * It moves disks when dragged and dropped, and repaints the game when resized.
 *
 * @author Professor
 */
public class GameController implements MouseListener, MouseMotionListener, ComponentListener {

  private int initialY;
  private int initialX;
  private ChatPanel chat;

  public GameController(ChatPanel chat) {
    this.chat = chat;
  }

  public void mousePressed(MouseEvent e) {
    for (int i = GameData.getInstance().getDisks().size() - 1; i >= 0; i--) {
      boolean isLast = false;
      Disk disk = GameData.getInstance().getDisks().get(i);
      for(Tower tw : GameData.getInstance().getTowers()){
          if (tw.getLastDiskWidth() == disk.getWidth()) {
              isLast = true;
              break;
          }
      }
      if (disk.contains(e.getX(), e.getY()) && isLast) {
        GameData.getInstance().setSelectedDisk(disk);
        GameData.getInstance().setMouseXOffset( e.getX() - disk.getDiskX());
        GameData.getInstance().setMouseYOffset( e.getY() - disk.getDiskY());
        initialX = disk.getDiskX();
        initialY = disk.getDiskY();
        break;
      }
    }
  }

  public void mouseReleased(MouseEvent e) {
    //BufferedWriter out = new BufferedWriter(fstream);

    boolean moved = false;
    if (GameData.getInstance().getSelectedDisk() != null) {
      for (Tower tower : GameData.getInstance().getTowers()) {
        int lastW = tower.getLastDiskWidth();
        int diskW = GameData.getInstance().getSelectedDisk().getWidth();
        if (tower.contains(e.getX(), e.getY()) &&
                (lastW > diskW || tower.getLastDiskWidth() == 0)) {
          //calc drop disk next
          tower.dropDisk(GameData.getInstance().getSelectedDisk());
          tower.setSelected(true);
          moved = true;
          for (Tower otherT : GameData.getInstance().getTowers()) {
            if (!tower.equals(otherT)) {
              otherT.resetLastDiskWidth(diskW);
            }
          }
          if (GameData.getInstance().isPlayerWin()) {
            chat.playerWinMSG();
          } else {
            chat.moveMSG(moved);
          }
        } else {
          tower.setSelected(false);
        }
      }
      if (!moved) {
        chat.moveMSG(moved);
        GameData.getInstance().getSelectedDisk().setDiskX(initialX);
        GameData.getInstance().getSelectedDisk().setDiskY(initialY);
      }
      GameData.getInstance().setSelectedDisk(null);
      GameData.getInstance().repaint();
    }
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String formattedTime = now.format(formatter);
    GameData.getInstance().addData("x: " + e.getX()
            +  " y: " + e.getY() + " "+ moved + " "+ formattedTime);
  }

  public void mouseDragged(MouseEvent e) {
    if (GameData.getInstance().getSelectedDisk() != null) {
      for (Tower tower : GameData.getInstance().getTowers()) {
          tower.setSelected(tower.contains(e.getX(), e.getY()));
        if (tower.contains(e.getX(), e.getY())) {
          tower.setSelected(true);
        } else {
          tower.setSelected(false);
        }
      }
      GameData.getInstance().getSelectedDisk().setDiskX(e.getX() - GameData.getInstance().getMouseXOffset());
      GameData.getInstance().getSelectedDisk().setDiskY(e.getY() - GameData.getInstance().getMouseYOffset());
      GameData.getInstance().repaint();
    }
  }

  public void componentResized(ComponentEvent e) {
    // Handle resizing event
    System.out.println("Panel resized to: " + e.getComponent().getSize());
    GameData.getInstance().setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void componentMoved(ComponentEvent e) {
  }

  @Override
  public void componentShown(ComponentEvent e) {
  }

  @Override
  public void componentHidden(ComponentEvent e) {
  }

}
