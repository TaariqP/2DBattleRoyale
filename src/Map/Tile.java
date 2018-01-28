package Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

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

  public void draw(Graphics2D g, int topLeftX, int topLeftY) {
    g.drawImage(image, topLeftX, topLeftY, null);
  }


  public void setTileType(TileType tileType) {
    this.tileType = tileType;
    generateImage();
  }

  public void generateImage() {
    Random number = new Random();
    int choice = number.nextInt(tileType.getNumberOfImages()) +
        tileType.getStartingIndex();
    String input = "PNG/Tiles/tile_" + Integer.toString(choice) + ".png";
    File location = new File(input);

    image = null;
    try {
      image = ImageIO.read(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
