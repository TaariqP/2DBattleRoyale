package Entity;

import Entity.EntityType.Entity;
import java.util.ArrayList;
import Map.Coordinate;

public class Player {

  private final String PLAYER_NAME;
  private final String PLAYER_TYPE;
  private final int ID;
  private int health;
  private int speed;
  private Coordinate playerPosition;
  private Entity.PlayerState;
  ArrayList<Entity> entities = new ArrayList<>();

  public Player(String PLAYER_NAME, int ID, String PLAYER_TYPE, int health,
      int speed, Coordinate playerPosition) {
    this.PLAYER_NAME = PLAYER_NAME;
    this.ID = ID;
    this.PLAYER_TYPE = PLAYER_TYPE;
  }

  public String getPlayerType() {
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
