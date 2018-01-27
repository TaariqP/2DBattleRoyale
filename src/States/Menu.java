package States;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu extends State {

  List<String> options;

 public Menu() {
   super("Menu");
   options = Arrays.asList("Start", "Exit", "Help");
 }

  @Override
  public void init() {
   
  }

  @Override
  public void draw(Graphics2D g) {
   int x = 100;
   int y = 50;
  for (String s : options) {
    g.drawString(s, x, y);
    y+= 50;
  }
  }

  @Override
  public void update() {
    super.update();
  }


}
