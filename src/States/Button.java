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

  public Button(String ID, String imageLocation, int x, int y) {
    this.ID = ID;
    File location = new File(imageLocation);
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void click() {

  }

  public void draw(Graphics2D g) {
    g.drawImage(image, x, y, null);
  }
}
