package Entity;

import Map.Coordinate;
import States.Camera;

public class AmmoBox extends Entity {

  private final int addAmmo = 25;

  public AmmoBox(Coordinate position, Camera camera) {
    super(position, camera);
  }

}
