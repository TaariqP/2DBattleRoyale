package Entity;

public enum PlayerType {
  ZOMBIE ("Zombie 1"), SURVIVOR ("Survivor 1"), ROBOT ("Robot 1");

  private String location;

  PlayerType(String location){
    this.location = location;
  }

  public String get(){
    return location;
  }
}
