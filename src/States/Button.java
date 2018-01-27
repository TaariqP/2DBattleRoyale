package States;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Button implements Clickable{

  private String ID;
  private BufferedImage image;
  private int x;
  private int y;
  private Clickable c;

  public Button(String ID, String imageLocation, int x, int y, Clickable c) {
    this.ID = ID;
    this.c = c;
    File location = new File(imageLocation);
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void click() {
    c.click();
  }

  public void draw(Graphics2D g) {
    g.drawImage(image, x, y, null);
  }
}
