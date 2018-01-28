package States;

import Entity.Bandage;
import Entity.MachineGun;
import Entity.Pistol;
import Entity.Entity;
import Entity.Player;
import Entity.dropCheck;
import Hud.HealthBar;
import Hud.Hud;
import Map.Coordinate;
import Map.Map;
import Map.MapGeneration;
import Server.Client;
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
  private Player player2;
  List<Entity> items;
  private int width;
  private int height;
  private Hud hud;
  private HealthBar healthBar;
  private List<Player> players;
  private Client client;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    MapGeneration randMap = new MapGeneration();
    camera = new Camera(64 * 64, 64 * 64);
    map = new Map("Maps/output.txt", camera);
    player = new Player("Player 1", 1, new Coordinate(64 * 64, 64 * 64),
        mousePos, camera, width, height,true);
    players = new ArrayList<>();
    camera = new Camera(64 * 64, 64 * 64);
    map = new Map("Maps/map.txt", camera);
    client = new Client(this);
    client.requestPlayer();
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
    player.update();
  }

  @Override
  public void init() {
    super.init();
  }


  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() - 10);
      camera.setY(player.getPlayerPosition().getY());
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() + 10);
      camera.setY(player.getPlayerPosition().getY());
    }

    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() - 10);
      camera.setX(player.getPlayerPosition().getX());
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT
        || e.getKeyCode() == KeyEvent.VK_D) {
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() + 10);
      camera.setX(player.getPlayerPosition().getX());
    }

    if (e.getKeyCode() == KeyEvent.VK_F) {
      attemptPickUp();
    }
  }

  private void attemptPickUp() {
    List<Entity> returned = new ArrayList<>();
    for (Iterator<Entity> it = items.iterator(); it.hasNext(); ) {
      Entity e = it.next();
      Rectangle b1 = player.getBounds();
      Rectangle b2 = e.getBounds();
      if (b1.intersects(b2)) {
        dropCheck dc = player.pickUp(e);
        if (dc.isHasPickedUp()) {
          it.remove();
          if (dc.existsReturnedItem()) {
            Entity e2 = dc.getReturnedItem();
            e2.setPosition(e.getPosition());
            returned.add(e2);
          }
        }
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
    System.out.println("Drawing");
    map.draw(g);
    healthBar = new HealthBar(player, camera, player.getPlayerPosition());
    healthBar.draw(g);
    for (Entity b : items) {
      b.draw(g);
    }

    for(Player p : players){
      System.out.println(p.getPlayerPosition().getX() + " " +
          p.getPlayerPosition().getY());
      p.draw(g);
    }
  }

  public Player addPlayer(String name,String id, int x, int y){
    Player p = new Player(name, Integer.valueOf(id), new Coordinate(x,y),null,camera,
        width, height, false);
    players.add(p);
    System.out.println("Added player with id " + id);
    System.out.println("Num of players " + players.size());
    return p;
  }

  public Player addPlayableplayer(String name,String id, int x, int y) {
    Player p = new Player(name, Integer.valueOf(id), new Coordinate(x,y),
        mousePos, camera, width, height, true);
    player = p;
    players.add(p);
    System.out.println("Added player " + name);
    return p;
  }
}
