package States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GameOver extends State {

  private List<Button> buttons;

  public GameOver(int width, int height, StateManager manager) {
    super("GameOver", width, height, manager);
    buttons = new ArrayList<>();
    initStart();
    initExit();
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(Color.black);
    g.drawString("Game over!", getWidth() / 2,  getHeight() / 2);
    for (Button b : buttons) {
      b.draw(g);
    }
  }

  private void initExit() {
    Clickable exitClick = new Clickable() {
      @Override
      public void click() {
        System.exit(0);
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
        .getWidth() / 2, (super.getHeight() / 2) + 160, exitClick));
  }

  private void initStart() {
    Clickable startClick = new Clickable() {
      @Override
      public void click() {
        getManager().SwitchState(StateManager.Game_ID);
      }
    };
    File file = new File("PNG/buttons/button_start.png");
    BufferedImage image = null;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    buttons.add(new Button("Start", image, super.getWidth() / 2 - image
        .getWidth() / 2, super.getHeight() / 2 + 30, startClick));
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
