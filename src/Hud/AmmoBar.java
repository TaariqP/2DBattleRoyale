package Hud;

import Entity.Player;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;

public class AmmoBar {

  private int value = 100;
  private Player player;
  private BufferedImage image;

  public AmmoBar(Player player) {
    this.player = player;
    File location = new File("PNG/bullet.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g) {
    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
    g.drawImage(image, 40, 50, null);
    g.drawString(Integer.toString(value), 60, 40);
  }

}
