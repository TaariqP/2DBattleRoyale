package Map;

public enum TileType {
  GRASS("grass ",4,1), WATER("water ",2,5), WALL("wall ",1,7), ERROR("error"
      + " ",1,8);

  private String representation;
  private int numberOfImages;
  private int startingIndex;

  TileType(String representation, int numberOfImages, int startingIndex){
    this.representation = representation;
    this.numberOfImages = numberOfImages;
    this.startingIndex = startingIndex;
  }

  public String getRepresentation(){
    return representation;
  }

  public int getNumberOfImages() {
    return numberOfImages;
  }

  public int getStartingIndex() {
    return startingIndex;
  }

}


