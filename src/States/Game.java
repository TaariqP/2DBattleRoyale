package States;

import Entity.Bandage;
import Entity.MachineGun;
import Entity.Pistol;
import Entity.Entity;
import Entity.Player;
import Map.Coordinate;
import Map.Map;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game extends State {

  private Coordinate mousePos = new Coordinate(0, 0);
  private Map map;
  private Camera camera;
  private Player player;
  List<Entity> items;
  private int width;
  private int height;
  private List<Player> players;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    camera = new Camera(64 * 64, 64 * 64);
    map = new Map("Maps/output.txt", camera);
    player = new Player("Player 1", 1, new Coordinate(64 * 64, 64 * 64),
        mousePos, camera, width, height);
    this.width = width;
    this.height = height;
    makeItems();
  }

  private void makeItems() {
    items = new ArrayList<>();
    Random location = new Random();
    for (int i = 0; i < 1000; i++) {
      items.add(new Bandage(new Coordinate(location.nextInt(map.getWidth()),
          location
              .nextInt(map.getHeight())), camera));
    }
    for (int i = 0; i < 128; i++) {
      items.add(new MachineGun(
          new Coordinate(location.nextInt(map.getWidth()),
              location.nextInt(map.getHeight())), camera));
      items.add(new Pistol(new Coordinate(location.nextInt(map.getWidth()),
          location.nextInt(map.getHeight())), camera));
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

  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() - 10);
      camera.setY(player.getPlayerPosition().getY());
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() + 10);
      camera.setY(player.getPlayerPosition().getY());
    }

    if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() - 10);
      camera.setX(player.getPlayerPosition().getX());
    }
    if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() + 10);
      camera.setX(player.getPlayerPosition().getX());
    }

    if (e.getKeyCode() == KeyEvent.VK_F) {
      attemptPickUp();
    }
  }

  private void attemptPickUp() {
    for (Iterator<Entity> it = items.iterator(); it.hasNext();) {
      Entity e = it.next();
      Rectangle b1 = player.getBounds();
      Rectangle b2 = e.getBounds();
      if (b1.intersects(b2)) {
        if(player.pickUp(e));
        it.remove();
      }
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
    for (Entity b : items) {
      b.draw(g);
    }
    player.draw(g);
  }

  public void addPlayer(String name,String id){
    //players.add(new Player(name, id, ));
  }

}
