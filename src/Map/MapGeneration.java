package Map;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MapGeneration {
  
  public void generate() {
    PrintWriter out = null;
    try {
      out = new PrintWriter("Maps/map.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    out.print("128 128");
    out.println();
    for (int i = 0; i < 128; i++) {
      for (int j = 0; j < 128; j++) {
        out.print("grass ");
      }
      out.println();
    }
    out.flush();
    out.close();
  }

}
