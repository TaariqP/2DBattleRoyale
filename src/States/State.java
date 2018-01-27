package States;


import java.awt.Graphics2D;

public abstract class State {

  private String ID;
  private int width;
  private int height;

  public State(String ID, int width, int height) {
    this.ID = ID;
    this.width = width;
    this.height = height;
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
}
