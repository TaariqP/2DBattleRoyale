package Map;

import static Map.TileType.WALL;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MapGeneration {

  private Map map;
  private static final int MAP_WIDTH = 128;
  private static final int MAP_HEIGHT = 128;
  private String filename = "Maps/output.txt";

  /*
  private static final int PLAYER_SPAWN;
  private static final int MIN_BOUND = PLAYER_SPAWN - 20;
  private static final int MAX_BOUND = PLAYER_SPAWN + 20;
  */
  public MapGeneration() {
    generateMap();
  }

  MapGeneration(String fileName) {
      this.filename = fileName;
      generateMap();
  }

  public void generateMap() {
    PrintWriter out = null;
    String grassMapFile = filename;
    try {
      out = new PrintWriter(grassMapFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    out.print(MAP_WIDTH + " " + MAP_HEIGHT);
    out.println();
    for (int i = 0; i < MAP_HEIGHT; i++) {
      for (int j = 0; j < MAP_WIDTH; j++) {
        out.print(TileType.GRASS.getRepresentation());
      }
      out.println();
    }
    out.flush();
    out.close();
    mapWithRandomElementsAdded(grassMapFile);
    for (int i = 0; i < map.getTileWidth(); i++) {
      map.setTileAtPosition(i, 0, WALL);
      map.setTileAtPosition(i, map.getTileHeight() - 1, WALL);
      map.setTileAtPosition(0, i, WALL);
      map.setTileAtPosition(map.getTileWidth() - 1, i, WALL);
    }
    generateMapFile(map);
  }

  private void generateMapFile(Map mapWithOtherElements) {

    PrintWriter out = null;
    String mapFile = filename;
    try {
      out = new PrintWriter(mapFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    out.print(MAP_WIDTH + " " +
        MAP_HEIGHT);
    out.println();
    for (int y = 0; y < mapWithOtherElements.getTileHeight(); y++) {
      for (int x = 0; x < mapWithOtherElements.getTileWidth(); x++) {
        out.print(mapWithOtherElements.getTileAtPosition(x, y).getTileType()
            .getRepresentation());

      }
      out.println();
    }
    out.flush();
    out.close();
  }

  private void mapWithRandomElementsAdded(String grassMapFile) {
    map = new Map(grassMapFile);
    map.convertStringToMap();
    Random generator = new Random();
    // lake ratio = 5 means a maximum of a fifth of the map can be taken up by
    // lakes
    final int LAKE_RATIO = 2;
    final int MAX_LAKE_SIZE = 64;
    final int MIN_LAKE_SIZE = 5;
    int bound =
        map.getTileHeight() * map.getTileWidth() / (MAX_LAKE_SIZE * LAKE_RATIO);
    int numberOfLakesOnMap = generator.nextInt(bound);
    for (int i = 0; i < numberOfLakesOnMap; i++) {
      int x = generator.nextInt(map.getTileWidth() - 1);
      int y = generator.nextInt(map.getTileHeight() - 1);
      if (map.getTileAtPosition(x, y).getTileType() == TileType.GRASS) {
        int maxLength = generator.nextInt(MAX_LAKE_SIZE - MIN_LAKE_SIZE) +
            MIN_LAKE_SIZE;
        createPath(x, y, TileType.WATER, maxLength);
      }
    }
    final int BUSH_RATIO = 2;
    final int MAX_BUSH_SIZE = 64;
    final int MIN_BUSH_SIZE = 5;
    int numberOfBushesOnMap = generator.nextInt((map.getTileHeight() *
        map.getTileWidth() / (MAX_BUSH_SIZE * BUSH_RATIO)));
    for (int i = 0; i < numberOfBushesOnMap; i++) {
      int x = generator.nextInt(map.getTileWidth() - 1);
      int y = generator.nextInt(map.getTileHeight() - 1);
      if (map.getTileAtPosition(x, y).getTileType() == TileType.GRASS) {
        int maxLength = generator.nextInt(MAX_BUSH_SIZE - MIN_BUSH_SIZE) +
            MIN_BUSH_SIZE;
        createPath(x, y, WALL, maxLength);
      }
    }
  }

  private void createPath(int x, int y, TileType tileType, int maxLength) {
    if (maxLength != 0) {
      Random generator = new Random();
      MapDirection nextDirection = MapDirection.values()[generator.nextInt
          (MapDirection.values()
              .length - 1)];
      map.setTileAtPosition(x, y, tileType);
      switch (nextDirection) {
        case UP:
          if (y > 0) {
            createPath(x, y - 1, tileType, maxLength - 1);
            break;
          }
        case DOWN:
          if (y < map.getTileHeight() - 1) {
            createPath(x, y + 1, tileType, maxLength - 1);
            break;
          }
        case LEFT:
          if (x > 0) {
            createPath(x - 1, y, tileType, maxLength - 1);
            break;
          }
        case RIGHT:
          if (x < map.getTileWidth() - 1) {
            createPath(x + 1, y, tileType, maxLength - 1);
            break;
          }
        default:
          // in case the path runs out of options
          createPath(x, y, tileType, 0);

      }
    }
  }
}

