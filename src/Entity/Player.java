package Entity;

import Map.Coordinate;
import Map.Tile;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Map.Coordinate;
import java.util.Optional;
import javax.imageio.ImageIO;

public class Player {

  private static final int MAX_HEALTH = 100;
  private final String PLAYER_NAME;
  private final PlayerType PLAYER_TYPE;
  private final int ID;
  private int health;
  private Coordinate playerPosition;
  private Coordinate mousePosition;
  private PlayerState state;
  ArrayList<Entity> inventory;
  BufferedImage currentState;
  private int screenWidth;
  private int screenHeight;
  private Camera camera;
  private static final int MAX_BAG_SIZE = 10;
  private Weapon weapon;
  private double rotation;
  private boolean isPlayable;
  private int ammo;

  public Player(String PLAYER_NAME, int ID, Coordinate playerPosition,
      Coordinate mousePosition, Camera camera, int screenWidth, int
      screenHeight, boolean isPlayable) {
    this.PLAYER_NAME = PLAYER_NAME;
    this.ID = ID;
    this.playerPosition = playerPosition;
    this.health = MAX_HEALTH;
    this.mousePosition = mousePosition;
    this.PLAYER_TYPE = PlayerType.randomType();
    this.state = PlayerState.STAND;
    File location = new File(location());
    currentState = null;
    try {
      currentState = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.camera = camera;
    inventory = new ArrayList<>();
    weapon = null;
    this.rotation = 0;
    this.isPlayable = isPlayable;
    this.ammo = 0;
  }

  public int getID() {
    return ID;
  }

  public Rectangle getBounds() {
    return new Rectangle(playerPosition.getX(), playerPosition.getY(),
        currentState
            .getWidth(), currentState.getHeight());
  }

  public void draw(Graphics2D graphics) {
    try {
      currentState = ImageIO.read(new File(location()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    double onScreenX = playerPosition.getX() - camera.getX() + 640;
    double onScreenY = playerPosition.getY() - camera.getY() + 480;
    double drawX = onScreenX - currentState.getWidth() / 2;
    double drawY = onScreenY - currentState.getHeight() / 2;

    AffineTransform old = new AffineTransform();
    AffineTransform at = new AffineTransform();
    at.rotate(rotation, onScreenX, onScreenY);
    at.translate(drawX, drawY);
    graphics.drawImage(currentState, at, null);
    graphics.setTransform(old);
    graphics.drawString(PLAYER_NAME, (int) drawX - currentState.getWidth() / 5,
        (int)
            drawY);
  }

  private String location() {
    String location = "PNG/" + PLAYER_TYPE.getLocation() + "/" + PLAYER_TYPE
        .getLowerLocation() + "_" + state.get() + ".png";
    return location;
  }

  public PlayerType getPlayerType() {
    return PLAYER_TYPE;
  }

  public void setPlayerPosition(Coordinate playerPosition) {
    this.playerPosition = playerPosition;
  }

  public void takeDamage(int d) {
    this.health -= d;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Coordinate getPlayerPosition() {
    return playerPosition;
  }

  public void update() {
    if (isPlayable) {
      double onScreenX = playerPosition.getX() - camera.getX() + 640;
      double onScreenY = playerPosition.getY() - camera.getY() + 480;
      rotation = Math.atan2(mousePosition.getY() -
          onScreenY, mousePosition.getX() -
          onScreenX);
    }
  }

  private void changeState() {
    if (weapon.getWeaponName().equals("Machine Gun")) {
      state = PlayerState.MACHINE;
    } else if (weapon.getWeaponName().equals("pistol")) {
      state = PlayerState.GUN;
    }
  }

  public int getHealth() {
    return health;
  }

  public int getAmmo() {
    return ammo;
  }

  public String getName() {
    return PLAYER_NAME;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public void reload() {
    if (equippedWeapon()) {
      ammo = weapon.reload(ammo);
    }
  }

  public boolean canShoot() {
    return (equippedWeapon() && weapon.CURRENT_CLIP > 0);
  }

  public void shoot() {
    if (equippedWeapon() && weapon.CURRENT_CLIP > 0) {
      weapon.shoot();
      //Create projectile using mouseEvent
    }
  }

  public int inventorySize() {
    return inventory.size();
  }

  public boolean useBandage() {
    if (inventory.size() > 0 && health < MAX_HEALTH) {
      inventory.remove(0);
      health += 25;
      if (health > 100) {
        health = 100;
      }
      return true;
    }
    return false;
  }

  public boolean equippedWeapon() {
    return weapon != null;
  }

  public dropCheck pickUp(Entity e) {
    if (e.type == EntityType.WEAPON && equippedWeapon()) {
      dropCheck dc = new dropCheck(true, Optional.of(weapon));
      weapon = (Weapon) e;
      changeState();
      return dc;
    } else if (inventory.size() >= MAX_BAG_SIZE) {
      return new dropCheck(false, Optional.empty());
    } else {
      if (e.type == EntityType.WEAPON) {
        weapon = (Weapon) e;
        changeState();
        return new dropCheck(true, Optional.empty());
      } else if (e.type == EntityType.AMMO) {
        ammo += ((AmmoBox) e).getAddAmmo();
        return new dropCheck(true, Optional.empty());
      } else {
        inventory.add(e);
        return new dropCheck(true, Optional.empty());
      }
    }
  }

  public void move(int x, int y, double rot, String state) {
    if (!isPlayable) {
      playerPosition.setX(x);
      playerPosition.setY(y);
      rotation = rot;
      this.state = PlayerState.valueOf(state);
    }
  }

  public double getRotation() {
    return rotation;
  }

  public PlayerState getState() {
    return state;
  }

  public void heal() {
    health += 25;
  }
}

