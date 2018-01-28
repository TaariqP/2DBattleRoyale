package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bandage extends Entity {

  private BufferedImage image;
  private static final int health = 25;

  public Bandage(Coordinate position, Camera camera) {
    super(position, camera, EntityType.ITEM);
    File location = new File("PNG/Bandage_RS.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(position.getX(), position.getY(), image
        .getWidth(),
        image.getHeight());
  }

  @Override
  public void draw(Graphics2D g) {
    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
    g.setColor(Color.white);
    if (position.getX() >= camera.getX() - 2000 && position.getX() <= camera
        .getX() + 2000 && position.getY() >= camera.getY() - 2000 && position
        .getY() <= camera.getY() + 2000) {
      g.drawImage(image, position.getX() - camera.getX() + 640,
          position.getY() -
              camera.getY() + 480,
          null);
    }
    g.drawString("Bandage", position.getX() -
            camera.getX
                () + 640 - image.getWidth() / 2,
        position.getY
            () -
            camera.getY() + 480);
  }
}
