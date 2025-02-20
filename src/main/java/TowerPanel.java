import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The panel that displays the towers and disks.
 * It overrides paintComponent to draw the towers and disks, and
 * It implements PropertyChangeListener (observer pattern) to listen for changes in the game data.
 *
 * @author Professor
 */
class TowerPanel extends JPanel implements PropertyChangeListener {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    for (Tower tower : GameData.getInstance().getTowers()) {
      tower.draw(g);
    }
    for (Disk disk : GameData.getInstance().getDisks()) {
      disk.draw(g);
      //change to have disk given to first tower on initial state
    }
    // title
    String title = "Towers of Hanoi";
    Font titleFont = new Font("Impact", Font.PLAIN, 40);
    g.setFont(titleFont);
    g.setColor(Color.RED);

    FontMetrics metrics = g.getFontMetrics(titleFont);
    int x = (getWidth() - metrics.stringWidth(title)) / 2;
    int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() - 175;

    g.drawString(title, x, y);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    repaint();
  }
}
