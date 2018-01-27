package States;


import java.awt.Graphics2D;

public abstract class State {

  private String ID;

  public State(String ID) {
    this.ID = ID;
  }

  public void draw(Graphics2D g) {

  }

  public void update() {

  }

  public void init() {

  }

}
