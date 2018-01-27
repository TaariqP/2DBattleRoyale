package Map;

import Entity.EntityType;
import java.io.*;
import org.lwjgl.system.macosx.ObjCPropertyAttribute.Buffer;

public class Map {

  private final String fileName;
  Tile[][] map;

  Map(String fileName) {
    this.fileName = fileName;
  }


  public static void main(String[] args) {
    new Map("Maps/3x3test").convertStringToMap();
  }

  public void convertStringToMap() {

    // The name of the file to open.

    // This will reference one line at a time
    String line = null;

    try {
      // FileReader reads text files in the default encoding.
      FileReader fileReader =
          new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader =
          new BufferedReader(fileReader);

      String dimensionsLine = bufferedReader.readLine();
      String[] dimensions = dimensionsLine.split(" ");
      int width = Integer.parseInt(dimensions[0]);
      int height = Integer.parseInt(dimensions[1]);

      map = new Tile[width][height];

      populateTilesFromFile(fileName);

      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
      }

      // Always close files.
      bufferedReader.close();

    } catch (FileNotFoundException ex) {
      System.out.println(
          "Unable to open file '" +
              fileName + "'");
    } catch (IOException ex) {
      System.out.println(
          "Error reading file '"
              + fileName + "'");
      // Or we could just do this:
      // ex.printStackTrace();
    }

  }

  // pre: array is non-jagged and the height/width matches that in the file
  private void populateTilesFromFile(String fileName) {

    try {
      FileReader fileReader =
          new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      //bufferedReader.readLine();
      bufferedReader.readLine();
      for (int x = 0; x < map.length; x++) {
        String row = bufferedReader.readLine();
        String[] column = row.split(" ");
        for (int y = 0; y < column.length; y++) {
          convertFromStringToTile(column[y]);
        }
      }
    } catch (FileNotFoundException ex) {
      System.out.println(
          "Unable to open file '" +
              fileName + "'");
    } catch (IOException ex) {
      System.out.println(
          "Error reading file '"
              + fileName + "'");
    }
  }

  private Tile convertFromStringToTile(String tileString) {
    switch (tileString) {
      case "grass":
        return new Tile(TileType.GRASS);
      default:
        return new Tile(TileType.ERROR);
    }
  }
}
