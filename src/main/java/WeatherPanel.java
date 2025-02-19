import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WeatherPanel extends JPanel {

  public WeatherPanel() {
    setBackground(Color.GRAY);
    JButton help = new JButton("Hint");
    add(help);
  }
}
