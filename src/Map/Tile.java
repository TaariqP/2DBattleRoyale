package Map;

import javax.swing.text.html.parser.Entity;

public class Tile {
  private int x;
  private int y;
  private TileType tileType;
  private Entity entityOnTile;

  Tile(int x, int y, TileType tileType, Entity entityOnTile) {
    this.x = x;
    this.y = y;
    this.tileType = tileType;
    this.entityOnTile = entityOnTile;
  }

  public final Entity getEntityOnTile() {
    return entityOnTile;
  }

  public final TileType getTileType() {
    return tileType;
  }

  public final Tile setEntityOnTile(Entity newEntity) {
    return new Tile(x, y, tileType, newEntity);
  }
}
