package Entity;

import Map.Coordinate;
import States.Camera;

public class Weapon extends Entity {

  private final String weaponName;
  private final int attackDamage;
  private final int CLIP_SIZE;
  protected int CURRENT_CLIP;

  public Weapon(Coordinate position, String weaponName, int attackDamage,
      Camera camera, int clip_size, int current_clip){
    super(position, camera, EntityType.WEAPON);
    this.weaponName = weaponName;
    this.attackDamage = attackDamage;
    this.CLIP_SIZE = clip_size;
    this.CURRENT_CLIP = current_clip;
  }

  public String getWeaponName() {
    return weaponName;
  }

  public int reload(int ammo) {
    return 0;
  }

  public void shoot() {
    CURRENT_CLIP -= 1;
  }

  public int getCURRENT_CLIP() {
    return CURRENT_CLIP;
  }

  public int getAttackDamage() {
    return attackDamage;
  }
}
