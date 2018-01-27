package States;

import Entity.Bandage;
import Entity.MachineGun;
import Entity.Pistol;
import Entity.Entity;
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

  private Coordinate mousePos = new Coordinate(0, 0);
  private Map map;
  private Camera camera;
  private Player player;
  List<Entity> bandages;
  List<Entity> machinegun;
  List<Entity> pistol;
  private int width;
  private int height;

  public Game(int width, int height, StateManager manager) {
    super("Game", width, height, manager);
    camera = new Camera(64 * 64, 64 * 64);
    map = new Map("Maps/map.txt", camera);
    player = new Player("Player 1", 1, new Coordinate(64 * 64, 64 * 64),
        mousePos, camera, width, height);
    this.width = width;
    this.height = height;
    makeBandages();
  }

  private void makeBandages() {
    bandages = new ArrayList<>();
    machinegun = new ArrayList<>();
    pistol = new ArrayList<>();
    Random location = new Random();
    for (int i = 0; i < 1000; i++) {
      bandages.add(new Bandage(new Coordinate(location.nextInt(map.getWidth()),
          location
              .nextInt(map.getHeight())), camera));
    }
    for (int i = 0; i < 128; i++) {
      machinegun.add(new MachineGun(
          new Coordinate(location.nextInt(map.getWidth()),
              location.nextInt(map.getHeight())), camera));
      pistol.add(new Pistol(new Coordinate(location.nextInt(map.getWidth()),
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

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() - 10);
      camera.setY(player.getPlayerPosition().getY());
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      player.getPlayerPosition().setY(player.getPlayerPosition().getY() + 10);
      camera.setY(player.getPlayerPosition().getY());
    }

    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() - 10);
      camera.setX(player.getPlayerPosition().getX());
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      player.getPlayerPosition().setX(player.getPlayerPosition().getX() + 10);
      camera.setX(player.getPlayerPosition().getX());
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
    for (Entity b : bandages) {
      b.draw(g);
    }
    for (Entity m : machinegun){
      m.draw(g);
    }
    for (Entity p : pistol){
      p.draw(g);
    }
  }
}
