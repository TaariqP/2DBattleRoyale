package Entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PlayerType {
  ZOMBIE ("Zombie 1", "zombie1"), SURVIVOR ("Survivor 1", "survivor1"), ROBOT
      ("Robot 1", "robot1");


  private String location;
  private String lowerLocation;

  PlayerType(String location, String lowerLocation){
    this.location = location;
    this.lowerLocation = lowerLocation;
  }

  public String getLocation(){
    return location;
  }

  public String getLowerLocation() {
    return lowerLocation;
  }

  private static final List<PlayerType> VALUES = Collections.unmodifiableList
      (Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random random = new Random();

  public static PlayerType randomType() {
    return VALUES.get(random.nextInt(SIZE));
  }

}
