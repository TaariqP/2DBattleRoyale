package Map;

public enum TileType {
  GRASS("grass "), WATER("water "), WALL("wall "), ERROR("error ");

  private String representation;

  TileType(String representation){
    this.representation = representation;
  }

  public String getRepresentation(){
    return representation;
  }
}
