package Hud;

import Entity.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;

public class AmmoBar extends Hud{

  private int value;
  private BufferedImage image;

  public AmmoBar(Player player) {
    super(player);
    File location = new File("PNG/bullet.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
    value = player.getAmmo();
  }

  @Override
  public void draw(Graphics2D g) {
    value = player.getAmmo();
    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
    g.setColor(Color.white);
    g.drawImage(image, 40, 50, null);
    g.drawString(Integer.toString(value), 67, 40);
  }

}
