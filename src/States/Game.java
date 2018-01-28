package States;

import Entity.AmmoBox;
import Entity.Bandage;
import Entity.MachineGun;
import Entity.Pistol;
import Entity.Entity;
import Entity.Player;
import Entity.Bullet;
import Entity.dropCheck;
import Hud.BandageCount;
import Hud.HealthBar;
import Hud.Hud;
import Hud.AmmoBar;
import Hud.weaponBar;
import Map.Coordinate;
import Map.Map;
import Map.MapGeneration;
import Map.TileType;
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

  private final static int DEFAULT_SPEED = 5;
  private Coordinate mousePos = new Coordinate(0, 0);
  private Map map;
  private Camera camera;
  private Player player;
  private Player player2;
  List<Entity> items;
  private int width;
  private int height;
  private List<Player> players;
  private Client client;
  List<Hud> HUD;
  List<Key> keys;
  List<Bullet> bullets;
  private int seed;
  List<Bullet> foreignbullets;

  public Game(int width, int height, StateManager manager){
    super("Game", width, height, manager);
    camera = new Camera(64 * 64, 64 * 64);
    map = new Map("Maps/" + seed + ".txt",
        camera);
    player = new Player("Player 1", 1, new Coordinate(64 * 64, 64 * 64),
        mousePos, camera, width, height, true);
    players = new ArrayList<>();
    client = new Client(this);
    client.requestPlayer();
    this.width = width;
    this.height = height;
    makeItems();
    initKeys();
    bullets = new ArrayList<>();
    foreignbullets = new ArrayList<>();
  }

  private void initKeys() {
    keys = new ArrayList<>();
    keys.add(new Key(KeyEvent.VK_A));
    keys.add(new Key(KeyEvent.VK_D));
    keys.add(new Key(KeyEvent.VK_W));
    keys.add(new Key(KeyEvent.VK_S));
    keys.add(new Key(KeyEvent.VK_UP));
    keys.add(new Key(KeyEvent.VK_LEFT));
    keys.add(new Key(KeyEvent.VK_DOWN));
    keys.add(new Key(KeyEvent.VK_RIGHT));
    keys.add(new Key(KeyEvent.VK_R));
    keys.add(new Key(KeyEvent.VK_F));
    keys.add(new Key(KeyEvent.VK_B));
  }

  public void addBullet(Bullet b) {
    if (b.getShooterID() != player.getID()) {
      b.setCamera(camera);
      foreignbullets.add(b);
    }
  }

  public void refreshBullets() {
    for (Bullet b : foreignbullets) {
      bullets.add(b);
    }
    foreignbullets = new ArrayList<>();
  }

  private void initHUD() {
    HUD = new ArrayList<>();
    HUD.add(new AmmoBar(player));
    HUD.add(new HealthBar(player, camera, player.getPlayerPosition()));
    HUD.add(new weaponBar(player));
    HUD.add(new BandageCount(player));
    for (Player p : players) {
      if (p.getID() != player.getID()) {
        HUD.add(new HealthBar(p, camera, p.getPlayerPosition()));
      }
    }
  }

  public void restart() {
    player.setHealth(100);
  }

  private void makeItems() {
    items = new ArrayList<>();
    Random location = new Random();
    for (int i = 0; i < 100; i++) {
      items.add(new Bandage(new Coordinate(location.nextInt(map.getWidth()),
          location
              .nextInt(map.getHeight())), camera));
    }
    for (int i = 0; i < 60; i++) {
      items.add(new MachineGun(
          new Coordinate(location.nextInt(map.getWidth()),
              location.nextInt(map.getHeight())), camera));
      items.add(new Pistol(new Coordinate(location.nextInt(map.getWidth()),
          location.nextInt(map.getHeight())), camera));
      items.add(new AmmoBox(new Coordinate(location.nextInt(map.getWidth()),
          location.nextInt(map.getHeight())), camera));
    }

  }


  @Override
  public void update() {
    dealDamage();
    //player.takeDamage(1); //tests game over screen
    if (!player.isAlive()) {
      getManager().SwitchState(StateManager.GAME_OVER);
    }
    player.update();
    for (Key k : keys) {
      if ((k.getKey() == KeyEvent.VK_UP && k.isPressed()) || (k.getKey() ==
          KeyEvent.VK_W && k.isPressed())) {
        int nextPlayerY = player.getPlayerPosition().getY() - DEFAULT_SPEED;
        if (isGrassTile(player.getPlayerPosition().getX(), nextPlayerY)) { //&&
        // !playersCrossed()) {
          player.getPlayerPosition().setY(nextPlayerY);
          camera.setY(player.getPlayerPosition().getY());
        }
      }
      if ((k.getKey() == KeyEvent.VK_DOWN && k.isPressed()) || (k.getKey() ==
          KeyEvent.VK_S && k.isPressed())) {
        int nextPlayerY = player.getPlayerPosition().getY() + DEFAULT_SPEED;
        if (isGrassTile(player.getPlayerPosition().getX(), nextPlayerY)) {
            //&& !playersCrossed()) {
          player.getPlayerPosition().setY(nextPlayerY);
          camera.setY(player.getPlayerPosition().getY());
        }
      }

      if ((k.getKey() == KeyEvent.VK_LEFT && k.isPressed()) || (k.getKey() ==
          KeyEvent.VK_A && k.isPressed())) {
        int nextPlayerX = player.getPlayerPosition().getX() - DEFAULT_SPEED;
        if (isGrassTile(nextPlayerX, player.getPlayerPosition().getY())) {
            //&& !playersCrossed()) {
          player.getPlayerPosition().setX(nextPlayerX);
          camera.setX(player.getPlayerPosition().getX());
        }
      }
      if ((k.getKey() == KeyEvent.VK_RIGHT && k.isPressed()) || (k.getKey() ==
          KeyEvent.VK_D && k.isPressed())) {
        int nextPlayerX = player.getPlayerPosition().getX() + DEFAULT_SPEED;
        if (isGrassTile(nextPlayerX, player.getPlayerPosition().getY())) {
          //&& !playersCrossed()) {
          player.getPlayerPosition().setX(nextPlayerX);
          camera.setX(player.getPlayerPosition().getX());
        }
      }
      if (k.getKey() == KeyEvent.VK_F && k.isPressed()) {
        attemptPickUp();
      }
      if (k.getKey() == KeyEvent.VK_R && k.isPressed()) {
        player.reload();
      }
      if (k.getKey() == KeyEvent.VK_B && k.isPressed()) {
        player.useBandage();
      }
      client.move(Integer.toString(player.getID()),
          player.getPlayerPosition().getX(), player.getPlayerPosition().getY(),
          player.getRotation(), player.getState());
    }

    dealDamage();
  }

  private void dealDamage() {
    for (Iterator<Bullet> bt = bullets.iterator(); bt.hasNext(); ) {
        Bullet b = bt.next();
      for(Player p : players){
        if(b.getBounds().intersects(p.getBounds().intersection(p.getBounds())) && b.getShooterID() != p.getID()){
          p.takeDamage(b.getDamage());
          bt.remove();
        }
      }
    }
  }

  @Override
  public void init() {
    super.init();
  }


  public void keyPressed(KeyEvent e) {
    for (Key k : keys) {
      if (e.getKeyCode() == k.getKey()) {
        k.Press();
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    for (Key k : keys) {
      if (e.getKeyCode() == k.getKey()) {
        k.unPress();
      }
    }
  }

  private boolean isGrassTile(int x, int y) {
    return map.getTileAtPosition(x / 64, y / 64).getTileType() ==
        TileType.GRASS;
  }

  private boolean playersCrossed() {
    for (Player aPlayer : players) {
      if (player.getID() != aPlayer.getID()) {
        if (player.getBounds().intersects(aPlayer.getBounds())) {
          return true;
        }
      }
    }
    return false;
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
    for (Entity e : returned) {
      items.add(e);
    }
  }

  @Override
  public void clickAt(MouseEvent mouseEvent) {
    if (player.canShoot()) {
      player.shoot(); // -1 off the ammo
      Bullet bullet = new Bullet(player.getWeapon().getAttackDamage(),
          player.getRotation(), player.getPlayerPosition(),
          camera, player.getID()); // generates
      client.shoot(bullet);
      bullets.add(bullet);
    }
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
    mousePos.setX(mouseEvent.getX());
    mousePos.setY(mouseEvent.getY());
    client.move(Integer.toString(player.getID()),
        player.getPlayerPosition().getX(), player.getPlayerPosition().getY(),
        player.getRotation(), player.getState());
  }


  @Override
  public void draw(Graphics2D g) {
    map.draw(g);

    for (Iterator<Entity> et = items.iterator(); et.hasNext(); ) {
      Entity b = et.next();
      b.draw(g);
    }

    refreshBullets();
    for (Bullet b : bullets) {
      b.draw(g);
    }

    player.draw(g);
    for (Player p : players) {
      if (p.getID() != player.getID() && p.isAlive()) {
        p.draw(g);
      }
    }

    initHUD();
    for (Hud h : HUD) {
      h.draw(g);
    }
  }

  public Player addPlayer(String name, String id, int x, int y) {
    Player p = new Player(name, Integer.valueOf(id), new Coordinate(x, y), null,
        camera,
        width, height, false);
    players.add(p);
    System.out.println("Added player with id " + id);
    System.out.println("Num of players " + players.size());
    return p;
  }

  public Player addPlayableplayer(String name, String id, int x, int y) {
    Player p = new Player(name, Integer.valueOf(id), new Coordinate(x, y),
        mousePos, camera, width, height, true);
    player = p;
    players.add(p);
    System.out.println("Added player " + name);
    return p;
  }

  public void setSeed(Integer seed) {
    this.seed = seed;
    System.out.println("seed " + this.seed);
    map = new Map("Maps/" + this.seed+".txt",
        camera);
  }
}
