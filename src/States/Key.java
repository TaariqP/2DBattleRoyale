package States;

import java.awt.event.KeyEvent;

public class Key {

  private int key;
  private boolean pressed;

  public Key(int key) {
    this.key = key;
    pressed = false;
  }

  public boolean isPressed() {
    return pressed;
  }

  public int getKey() {
    return key;
  }

  public void Press() {
    pressed = true;
  }

  public void unPress() {
    pressed = false;
  }
}
