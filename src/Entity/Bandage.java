package Entity;

import Map.Coordinate;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bandage extends Entity{

  private BufferedImage image;

  public Bandage(Coordinate position){
    super(position);
    File location = new File("PNG/bandage.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawImage(image, super.position.getX(), super.position.getY(), null);
  }
}
