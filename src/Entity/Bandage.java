package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bandage extends Entity{

  private BufferedImage image;

  public Bandage(Coordinate position, Camera camera){
    super(position, camera);
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
    if (position.getX() >= camera.getX() - 800 && position.getX() <= camera
        .getX() + 800 && position.getY() >= camera.getY() - 600 && position
        .getY() <= camera.getY() + 600)
    g.drawImage(image, position.getX() - camera.getX(), position.getY() -
            camera.getY(),
        null);
  }
}
