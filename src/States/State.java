package States;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

  private String ID;
  private int width;
  private int height;
  private StateManager manager;

  public State(String ID, int width, int height, StateManager manager) {
    this.ID = ID;
    this.width = width;
    this.height = height;

    this.manager = manager;
  }

  public void draw(Graphics2D g) {

  }

  public void update() {

  }

  public void init() {

  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public abstract void keyPressed(KeyEvent e);

  public abstract void clickAt(MouseEvent mouseEvent);
}
