package States;

import Map.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class Help extends State {


  private BufferedImage image;
  private List<Button> buttons;
  private Map endmap;
  private Camera deathcamera;

  public Help(int width, int height, StateManager manager) {
    super("Help", width, height, manager);
    buttons = new ArrayList<>();
    initExit();
    pickaMap();

  }

  private void initExit() {
    Clickable exitClick = new Clickable() {
      @Override
      public void click() {
        getManager().SwitchState(StateManager.Menu_ID);
      }
    };
    File file = new File("PNG/buttons/button_exit.png");
    BufferedImage image = null;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    buttons.add(new Button("Exit", image, super.getWidth() / 2 - image
        .getWidth() / 2, (super.getHeight() / 2) + 250, exitClick));
  }


  private void pickaMap() {
    deathcamera = new Camera(64 * 64, 64 * 64);
    Random picker = new Random();
    int m = picker.nextInt(10);
    endmap = new Map("Maps/" + String.valueOf(m) + ".txt", deathcamera);
  }

  private void moveMap() {
    if (deathcamera.getX() < 128 * 64 - getWidth()) {
      deathcamera.setX(deathcamera.getX() + 4);
    } else {
      deathcamera.setX(getWidth());
    }
  }

  @Override
  public void draw(Graphics2D g) {
    moveMap();
      endmap.draw(g);
    g.setColor(Color.white);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    g.drawString(
        "Use the mouse to aim",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 350);
    g.drawString(
        "Left Click to Shoot",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 300);
    g.drawString(
        "R for Reload",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 250);

    g.drawString(
        "B for Bandage",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 200);

    g.drawString(
        "F to pick up items",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 150);

    g.drawString(
        "WASD/Arrow Keys to move",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 100);

    g.drawString(
        "This Game is implemented without third party libraries.",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2 - 50);

    g.drawString(
        "Coded fully by Taariq, Luke, Robert, Matthew & Tiger",
        super.getWidth() / 2 - 400,
        super.getHeight() / 2);
    for (Button b : buttons) {
      b.draw(g);
    }
  }

  @Override
  public void init() {
    super.init();
  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void clickAt(MouseEvent mouseEvent) {
    for (Button b : buttons) {
      b.clickAt(mouseEvent);
    }
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {

  }

  @Override
  public void update() {
    super.update();
  }
}

