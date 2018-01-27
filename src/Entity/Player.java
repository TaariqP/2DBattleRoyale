package Entity;

import Entity.EntityType.Entity;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
  //private int speed;
  private Coordinate playerPosition;
  private Coordinate mousePosition;
  private PlayerState state;
  ArrayList<Entity> inventory = new ArrayList<>();
  BufferedImage currentState;

  public Player(String PLAYER_NAME, int ID, Coordinate playerPosition,
      Coordinate mousePosition) {
    this.PLAYER_NAME = PLAYER_NAME;
    this.ID = ID;
    this.playerPosition = playerPosition;
    this.health = 100;
    this.mousePosition = mousePosition;
    this.PLAYER_TYPE = PlayerType.randomType();
    this.state = PlayerState.STAND;
    System.out.println(location());
    File location = new File(location());
    currentState = null;
    try {
      currentState = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void draw(Graphics2D graphics) {
    try {
      currentState = ImageIO.read(new File(location()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    double drawX = playerPosition.getX() - currentState.getWidth() / 2;
    double drawY = playerPosition.getY() - currentState.getHeight() / 2;
    double angle = Math.atan2(mousePosition.getY() -
        getPlayerPosition().getY(), mousePosition.getX() -
        getPlayerPosition
        ().getX());
    System.out.println(angle);
    AffineTransform at = new AffineTransform();
    at.rotate(angle, playerPosition.getX(), playerPosition.getY());
    at.translate(drawX, drawY);
    graphics.drawImage(currentState, at, null);
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
}
