package entities;

public class Player {

  private final String playerName;
  private final int ID;
  private int health;
  private Coordinate playerPosition;
  
  public Player(String playerName, int ID) {
    this.playerName = playerName;
    this.ID = ID;
  }



}
