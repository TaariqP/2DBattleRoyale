package Entity;

import Map.Coordinate;
import java.awt.Graphics2D;

public class Entity {

  Coordinate position;

  public Entity(Coordinate position) {
    this.position = position;
  }

  public Coordinate getPosition() {
    return position;
  }

  public void draw(Graphics2D g) {

  }
}
