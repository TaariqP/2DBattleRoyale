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
  private BufferedImage image;

  Tile(TileType tileType) {
    this.tileType = tileType;
    generateImage();
  }

  public TileType getTileType() {
    return tileType;
  }

  private void generateImage() {
    if (tileType == TileType.GRASS) {
      Random number = new Random();
      int choice = number.nextInt(4) + 1;
      String input = "PNG/Tiles/tile_0" + Integer.toString(choice) + ".png";
      File location = new File(input);

      image = null;
      try {
        image = ImageIO.read(location);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void draw(Graphics2D g, int x, int y) {
      g.drawImage(image, x, y, null);
  }
}
