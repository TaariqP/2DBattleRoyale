package Entity;

import States.Camera;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Map.Coordinate;
import javax.imageio.ImageIO;

public class Player {

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
  private int MAX_BAG_SIZE = 10;
  private Weapon weapon;

  public Player(String PLAYER_NAME, int ID, Coordinate playerPosition,
      Coordinate mousePosition, Camera camera, int screenWidth, int
      screenHeight) {
    this.PLAYER_NAME = PLAYER_NAME;
    this.ID = ID;
    this.playerPosition = playerPosition;
    this.health = 100;
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
  }


  public Rectangle getBounds() {
    return new Rectangle(playerPosition.getX(), playerPosition.getY(), currentState
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
    double angle = Math.atan2(mousePosition.getY() -
        onScreenY, mousePosition.getX() -
        onScreenX);
    AffineTransform at = new AffineTransform();
    at.rotate(angle, onScreenX, onScreenY);
    at.translate(drawX, drawY);
    graphics.drawImage(currentState, at, null);
    graphics.drawString(PLAYER_NAME , (int)drawX - currentState.getWidth() / 5,
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

  public void setHealth(int health) {
    this.health = health;
  }

  public Coordinate getPlayerPosition() {
    return playerPosition;
  }

  public int getHealth() {
    return health;
  }


  public String getName() {
    return PLAYER_NAME;
  }
}

  public boolean equippedWeapon() {
    return weapon != null;
  }

  public boolean pickUp(Entity e) {
    if (e.type == EntityType.WEAPON && equippedWeapon()) {
      return false;
    } else if (inventory.size() >= 10) {
      return false;
    }
    else {
      if (e.type == EntityType.WEAPON) {
        weapon = (Weapon) e;
        return true;
      } else {
        inventory.add(e);
        return true;
      }
    }
  }
  }

