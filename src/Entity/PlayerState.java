package Entity;

public enum PlayerState {
  GUN("gun"), HOLD("hold"), MACHINE("machine"), RELOAD("reload"), SILENCER(
      "silencer"), STAND("stand");
  private final String IMAGE_ID;

  PlayerState(String IMAGE_ID) {
    this.IMAGE_ID = IMAGE_ID;
  }

  public String get(){
    return IMAGE_ID;
  }


}
