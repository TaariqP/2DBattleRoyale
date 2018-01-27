package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Graphics2D;

public class Entity {

  Coordinate position;
  Camera camera;

  public Entity(Coordinate position, Camera camera) {
    this.position = position;
    this.camera = camera;
  }

  public Coordinate getPosition() {
    return position;
  }

  public void draw(Graphics2D g) {

  }

  public void update(){

  }
}
