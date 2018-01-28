package Hud;

import Entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BandageCount extends Hud {

  private int count;
  private BufferedImage image;

  public BandageCount(Player player) {
    super(player);
    image = null;
    File location = new File("PNG/bandage50.png");
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void draw(Graphics2D g) {
    count = player.inventorySize();
    if (count > 0) {
      g.drawImage(image, 40, 400, null);
      g.drawString(String.valueOf(count), 40, 390);
    }
  }
}
