package Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
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

  public void draw(Graphics2D g, int topLeftX, int topLeftY) {
    if (tileType == TileType.GRASS) {
      Random number = new Random();
      int choice = number.nextInt(4) + 1;
      File location = new File("PNG/Tiles/tile_0" + Integer.toString(choice));
      BufferedImage image = null;
      try {
        image = ImageIO.read(location);
      } catch (IOException e) {
        e.printStackTrace();
      }
      g.drawImage(image, x - topLeftX, y - topLeftY, null);
    }
  }
}
