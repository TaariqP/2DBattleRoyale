package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullet {

  private String weaponName;
  private BufferedImage image;
  private Coordinate position;
  private Camera camera;
  private final double rotation;
  private final int iX;
  private final int iY;
  private int x;
  private int y;

  public Bullet(Weapon weapon, double rotation,
      Coordinate position, Camera camera) {
    this.weaponName = weapon.getWeaponName();
    this.position = position;
    iX = position.getX();
    iY = position.getY();
    this.camera = camera;
    this.rotation = rotation;
    this.x = iX;
    this.y = iY;
    File location = new File("PNG/001-bullet.png");
    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setPosition(Coordinate position) {
    this.position = position;
  }

  public void draw(Graphics2D g) {
    if (x >= camera.getX() - 2000 && x <= camera
        .getX() + 2000 && y >= camera.getY() - 2000
        && y <= camera.getY() + 2000) {
      //(int) (y + 2 * Math.sin(rotation));
      int onScreenX = x - camera.getX() + 640 - image.getWidth() / 2;
      int onScreenY = y - camera.getY() + 480 - image.getHeight() / 2;
      System.out.println(onScreenX);
      System.out.println(onScreenY);
      Graphics2D copy = (Graphics2D)g.create();
      AffineTransform at = new AffineTransform();
      at.rotate(rotation, iX, iY);
      at.translate(onScreenX, onScreenY);
      copy.drawImage(image, at, null);
      copy.dispose();
    }
  }
}
