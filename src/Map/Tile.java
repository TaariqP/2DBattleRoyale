package Map;

import javax.swing.text.html.parser.Entity;

public class Tile {
  private TileType tileType;

  Tile(TileType tileType) {
    this.tileType = tileType;
  }

  public final TileType getTileType() {
    return tileType;
  }

}
