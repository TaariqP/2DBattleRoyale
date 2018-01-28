package Map;

public class GenerateRandomMaps {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      MapGeneration mapGeneration = new MapGeneration("Maps/" + i + ".txt");
      System.out.println(i);
    }
  }
}
