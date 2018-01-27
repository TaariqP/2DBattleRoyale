package States;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button implements Clickable{

  private String ID;
  private BufferedImage image;
  private int x;
  private int y;
  private Clickable c;

  public Button(String ID, BufferedImage img, int x, int y, Clickable c) {
    this.ID = ID;
    this.c = c;
    image = img;
    this.x = x;
    this.y = y;
  }


  @Override
  public void click() {

    c.click();
  }

  public void draw(Graphics2D g) {
    g.drawImage(image, x, y, null);
  }

  public void clickAt(MouseEvent e) {
    if (e.getX() >= x && e.getX() <= x + image.getWidth() && e.getY() >= y &&
        e.getY() <= y + image.getHeight()) {
      click();
    }
  }
}