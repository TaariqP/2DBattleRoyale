package Entity;

import Entity.EntityType.Entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Map.Coordinate;
import java.awt.Image.*;
import javax.imageio.ImageIO;

public class Player {

  private final String PLAYER_NAME;
  private final PlayerType PLAYER_TYPE;
  private final int ID;
  private int health;
  private int speed;
  private Coordinate playerPosition;
  private PlayerState state;
  ArrayList<Entity> inventory = new ArrayList<>();

  public Player(String PLAYER_NAME, int ID, PlayerType PLAYER_TYPE, int health,
      int speed, Coordinate playerPosition) {
    this.PLAYER_NAME = PLAYER_NAME;
    this.ID = ID;
    this.PLAYER_TYPE = PLAYER_TYPE;

  }


  public void draw(Graphics2D graphics) {
    BufferedImage image;
    try {
      image = ImageIO.read(new File(location()));
      graphics.drawImage(image, playerPosition.getX(),
          playerPosition.getY(), null);
    } catch (IOException e) {
    }

  }

  private String location() {
    String location = "PNG/" + PLAYER_TYPE.get() + "/" + state.get() + ".png";
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
}
