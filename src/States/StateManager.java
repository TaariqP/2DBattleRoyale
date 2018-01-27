package States;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateManager {

  private static int width;
  private static int height;
  private String CurrentStateId;
  Map<String, State> states = new HashMap<>();
  public final String Menu_ID = "Menu";
  public final String Game_ID = "Game";
  public StateManager(int width, int height){
    this.width = width;
    this.height = height;
    CurrentStateId = Menu_ID;
    states.put(Menu_ID, new Menu(width, height));
    states.put(Game_ID, null);
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

}
