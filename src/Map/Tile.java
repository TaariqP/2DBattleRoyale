package Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

public class Tile {
  private TileType tileType;
  private int x;
  private int y;

  Tile(TileType tileType) {
    this.tileType = tileType;
  }

  public final TileType getTileType() {
    return tileType;
  }

  public final Tile setEntityOnTile(Entity newEntity) {
    return new Tile(tileType);
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

  public final Tile setTileType(TileType tileType) {
    return new Tile(tileType);
  }
}
