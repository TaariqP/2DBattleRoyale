package entities;

public class Player {

  private final String playerType;
  private final String playerName;
  private final int ID;
  private int health;
  private Coordinate playerPosition;

  public Player(String playerName, int ID, String playerType) {
    this.playerName = playerName;
    this.ID = ID;
  }

  public String getPlayerType() {
    return playerType;
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
