package States;

import Entity.Player;
import Map.Coordinate;
import Map.Map;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game extends State {

  private Coordinate mousePos = new Coordinate(0,0);
  private Map map;
  private Camera camera;
  private Player player;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    camera = new Camera(64*64, 64*64);
    map = new Map("Maps/map.txt", camera);
    player = new Player("Player 1", 1, new Coordinate(width / 2, height /
        2), mousePos);
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
    if(e.getKeyCode() == KeyEvent.VK_UP){
      camera.setY(camera.getY() - 10);
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN){
      camera.setY(camera.getY() + 10);
    }

    if(e.getKeyCode() == KeyEvent.VK_LEFT){
      camera.setX(camera.getX() - 10);
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      camera.setX(camera.getX() + 10);
    }
  }

  @Override
  public void clickAt(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
    mousePos.setX(mouseEvent.getX());
    mousePos.setY(mouseEvent.getY());
    System.out.println("mousePos " + mousePos.getX() + " " + mousePos.getY());
  }

  @Override
  public void draw(Graphics2D g) {
    map.draw(g);
    player.draw(g);
  }
}
