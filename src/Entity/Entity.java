package Entity;

import Map.Coordinate;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {

  Coordinate position;
  Camera camera;
  EntityType type;

  public Entity(Coordinate position, Camera camera, EntityType type) {
    this.position = position;
    this.camera = camera;
    this.type = type;
  }

  public void setPosition(Coordinate c) {
    this.position = c;
  }

  public Coordinate getPosition() {
    return position;
  }

  public void draw(Graphics2D g) {

  }


  public void update() {

  }


  public Rectangle getBounds(){
    return null;
  }

  public EntityType getType(){
    return type;
  }
}
