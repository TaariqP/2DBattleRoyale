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

    Random number = new Random();
    int choice;
    String initialString, input;

    switch (tileType){
      case GRASS: choice = number.nextInt(4) + 1;
                           initialString =  "PNG/Tiles/tile_0";
                           break;
      case BUSH:  choice = 8;
                  initialString = "PNG/Tiles/tile_16";
                  break;
      case WATER: choice = number.nextInt(1) + 1;
                  initialString = "PNG/Tiles/tile_water_1";
                  break;
      default:    choice = number.nextInt(4) + 1;
                  initialString = "PNG/Tiles/tile_0";
                  break;
    }


      input = initialString + Integer.toString(choice) + ".png";
      File location = new File(input);

      image = null;
      try {
        image = ImageIO.read(location);
      } catch (IOException e) {
        e.printStackTrace();
      }

  }

  public void draw(Graphics2D g, int x, int y) {
      g.drawImage(image, x, y, null);
  }
}
