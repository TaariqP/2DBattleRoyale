package States;

import java.awt.Graphics2D;

public class GameOver extends State {

  public GameOver(int width, int height) {
    super("GameOver", width, height);
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawString("Game over!", 50, 50);
  }

  @Override
  public void init() {
    super.init();
  }

  @Override
  public void update() {
    super.update();
  }
}
