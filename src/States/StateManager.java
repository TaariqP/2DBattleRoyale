package States;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class StateManager {

  private static int width;
  private static int height;
  private String CurrentStateId;
  java.util.Map<String, State> states = new HashMap<>();
  public static final String Menu_ID = "Menu";
  public static final String Game_ID = "Game";
  public static final String GAME_OVER = "Game Over!";
  public StateManager(int width, int height){
    this.width = width;
    this.height = height;
    CurrentStateId = Menu_ID;
    states.put(Menu_ID, new Menu(width, height, this));
    states.put(Game_ID, new Game(width, height, this));
    states.put(GAME_OVER, new GameOver(width, height, this));
  }


  public State getCurrentState(){
    return states.get(CurrentStateId);
  }

  public void SwitchState(String ID){
    CurrentStateId = ID;
  }

  public void draw(Graphics2D g){
    getCurrentState().draw(g);
  }

  public void update(){
    getCurrentState().update();
  }


  public void keyPressed(KeyEvent e) {
    getCurrentState().keyPressed(e);
  }

  public void restart() {
    CurrentStateId = Game_ID;
    states.get(Game_ID).restart();
  }

  public void keyReleased(KeyEvent e) {
    getCurrentState().keyReleased(e);
  }

  public void clickAt(MouseEvent mouseEvent) {
    getCurrentState().clickAt(mouseEvent);
  }

  public void mouseMoved(MouseEvent mouseEvent) {
    getCurrentState().mouseMoved(mouseEvent);
  }
}
