package Entity;

import Map.Coordinate;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;

public class Bullet {

  private String weaponName;
  private BufferedImage image;
  private Coordinate position;

  public Bullet(Weapon weapon, MouseEvent mouseEvent, double rotation,
      Coordinate position) {
    this.weaponName = weapon.getWeaponName();
    this.position = position;
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

  }
}
