package States;


import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Menu extends State {

  List<String> options;
  List<Button> buttons;

 public Menu(int width, int height) {
   super("Menu", width, height);
   options = Arrays.asList("Start", "Exit", "Help");
   buttons.add(new Button("Start", "PNG/buttons/button_start.png", super.getWidth()));
 }

  @Override
  public void init() {

  }

  @Override
  public void draw(Graphics2D g) {
   int x = super.getWidth() / 2;
   int y = super.getHeight() / 7;
  for (String s : options) {
    new Button(s,"PNG/buttons/button_start.png", x, y).draw(g);
  }
  }

  @Override
  public void update() {
    super.update();
  }


}
