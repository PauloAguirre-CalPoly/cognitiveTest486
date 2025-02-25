import java.awt.*;

/**
 * A disk in the Towers of Hanoi game.
 * It can be drawn, selected, deselected, and moved to a tower, and compared to other disks based on width.
 *
 * @author Professor
 */
class Disk {

  private int   diskX;
  private int   diskY;
  private int   width;
  private Color color;
  private char c;

  public Disk(int x, int y, int width, Color color, char c) {
    this.diskX = x - width / 2; // Center the disk horizontally
    this.diskY = y;
    this.width = width;
    this.color = color;
    this.c = c;
  }

  public void draw(Graphics g) {
    if (GameData.getInstance().getSelectedDisk() == this) {
      g.setColor(Color.YELLOW);
      g.fillRect(diskX, diskY, width, 20);
      g.setColor(Color.BLACK);
      g.drawRect(diskX, diskY, width, 20);
      g.setFont(new Font("TimesRoman", Font.BOLD, 20));
      g.drawString(String.valueOf(c), diskX + 5 , diskY + 15);
    } else {
      g.setColor(color);
      g.fillRect(diskX, diskY, width, 20);
    }
  }

  public boolean contains(int x, int y) {
    return x >= diskX && x <= diskX + width && y >= diskY && y <= diskY + 20;
  }

  public int getDiskX() {
    return diskX;
  }

  public void setDiskX(int diskX) {
    this.diskX = diskX;
  }

  public int getDiskY() {
    return diskY;
  }

  public void setDiskY(int diskY) {
    this.diskY = diskY;
  }

  public int getWidth() {
    return width;
  }
}
