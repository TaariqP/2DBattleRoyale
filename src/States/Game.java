package States;

import Map.Map;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game extends State {

  private Map map;
  private Camera camera;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    camera = new Camera(64*64, 64*64);
    map = new Map("Maps/map.txt", camera);
  }

  @Override
  public void update() {
    super.update();
  }

  @Override
  public void init() {
    super.init();
  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void clickAt(MouseEvent mouseEvent) {

  }

  @Override
  public void draw(Graphics2D g) {
    map.draw(g);
  }
}
