package States;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu extends State {

  List<String> options;

 public Menu(int width, int height) {
   super("Menu", width, height);
   options = Arrays.asList("Start", "Exit", "Help");
 }

  @Override
  public void init() {

  }

  @Override
  public void draw(Graphics2D g) {
   int x = super.getWidth() / 2;
   int y = super.getHeight() / ;
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
