package States;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public class Menu extends State {

  List<String> options;
  List<Button> buttons;
  BufferedImage titleImage;

  public Menu(int width, int height, StateManager stateManager) {
    super("Menu", width, height, stateManager);
    options = Arrays.asList("Start", "Exit", "Help");
    buttons = new ArrayList<>();
    initStart();
    initExit();
    initHelp();
    initTitleImage();
  }


  @Override
  public void init() {

  }

  @Override
  public void draw(Graphics2D g) {
    for (Button b : buttons) {
      b.draw(g);
    }
    drawTitle(g);
  }

  @Override
  public void update() {
    super.update();
  }

  private void drawTitle(Graphics2D g) {
    g.drawImage(titleImage, getWidth() / 2 - 350, 0, null);
  }

  private void initTitleImage() {
    File file = new File("PNG/title.png");
    titleImage = null;
    try {
      titleImage = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  private void initHelp() {
    Clickable helpClick = new Clickable() {
      @Override
      public void click() {

      }
    };
    File file = new File("PNG/buttons/button_help.png");
    BufferedImage image = null;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    buttons.add(new Button("Help", image, super.getWidth() / 2 - image
        .getWidth() / 2, (super.getHeight() / 2) + 290, helpClick));
  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  public void clickAt(MouseEvent e) {
    for (Button b : buttons) {
      b.clickAt(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {

  }


}
