package States;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameOver extends State {

  public GameOver(int width, int height, StateManager manager) {
    super("GameOver", width, height, manager);
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
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void clickAt(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {

  }

  @Override
  public void update() {
    super.update();
  }
}
