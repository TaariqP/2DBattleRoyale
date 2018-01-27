package Entity;

import Map.Coordinate;
import States.Camera;

public class Weapon extends Entity {

  private final String weaponName;
  private final int attackDamage;

  public Weapon(Coordinate position, String weaponName, int attackDamage, Camera camera){
    super(position, camera);
    this.weaponName = weaponName;
    this.attackDamage = attackDamage;
  }

}
