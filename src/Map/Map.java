package Map;

import Entity.EntityType;
import States.Camera;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {

  private final int mapSize;
  private Tile[][] map;
  private Camera camera;

  Map(int mapSize) {
    this.mapSize = mapSize;
  }

  public void moveEntityToPosition(Tile initial, Tile next) {
    next.setEntityOnTile(initial.getEntityOnTile());
  }

  public void draw(Graphics2D g) {
    int topLeftX = camera.getX() - 640;
    int topLeftY = camera.getY() - 480;
    for (int i = camera.getX() / 64 - 11; i < camera.getX() / 64 + 11; i++) {
      for (int j = camera.getY() / 64 - 11; j < camera.getY() / 64 + 11; j++) {
        if (i >= 0 && j >= 0 && i < map.length && j < map[0].length) {
          map[i][j].draw(g, topLeftX, topLeftY);
        } else {
          File location = new File("PNG/Tiles/tile_86.png");
          BufferedImage image = null;
          try {
            image = ImageIO.read(location);
          } catch (IOException e) {
            e.printStackTrace();
          }
          g.drawImage(image, i * 64 - topLeftX, j * 64 - topLeftY, null);
        }
      }
    }
  }
}
