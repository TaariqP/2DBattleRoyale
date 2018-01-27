package States;

import Entity.Bandage;
import Entity.Player;
import Map.Coordinate;
import Map.Map;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends State {

  private Coordinate mousePos = new Coordinate(0,0);
  private Map map;
  private Camera camera;
  private Player player;
  List<Bandage> bandages;
  private int width;
  private int height;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    camera = new Camera(64*64, 64*64);
    map = new Map("Maps/map.txt", camera);
    player = new Player("Player 1", 1, new Coordinate(width / 2, height /
        2), mousePos);
    this.width = width;
    this.height = height;
    makeBandages();
  }

  private void makeBandages() {
    bandages = new ArrayList<>();
    Random location = new Random();
    for (int i = 0; i < 10; i++) {
      bandages.add(new Bandage(new Coordinate(location.nextInt(map.getWidth()),
          location
          .nextInt(map.getHeight()))));
    }
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
  }

  @Override
  public void draw(Graphics2D g) {
    map.draw(g);
    player.draw(g);
    for (Bandage b : bandages) {
      b.draw(g);
    }
  }
}
