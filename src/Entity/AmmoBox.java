package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AmmoBox extends Entity {

  private static final int addAmmo = 25;
  private BufferedImage image;

  public AmmoBox(Coordinate position, Camera camera) {
    super(position, camera, EntityType.AMMO);
    File location = new File("PNG/ammo.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getAddAmmo() {
    return addAmmo;
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(position.getX(), position.getY(), image
        .getWidth(),
        image.getHeight());
  }

  public void draw(Graphics2D g) {
    if (position.getX() >= camera.getX() - 2000 && position.getX() <= camera
        .getX() + 2000 && position.getY() >= camera.getY() - 2000 && position
        .getY() <= camera.getY() + 2000) {
      g.drawImage(image, position.getX() - camera.getX() + 640, position
              .getY() -
              camera.getY() + 480,
          null);
      g.drawString("Ammo", position.getX() -
              camera.getX
                  () + 640 - image.getWidth() / 2,
          position.getY
              () -
              camera.getY() + 480);
    }
  }

}
