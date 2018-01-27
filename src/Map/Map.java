package Map;

import Entity.EntityType;

public class Map {

  final int mapSize;
  Tile[][] map;

  Map(int mapSize) {
    this.mapSize = mapSize;
  }

  public void moveEntityToPosition(Tile initial, Tile next) {
    next.setEntityOnTile(initial.getEntityOnTile());
  }
}
