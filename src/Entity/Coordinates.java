package Entity;

import Hud.Hud;
import java.awt.Graphics2D;

public class Coordinates extends Hud {

  public Coordinates(Player player) {
    super(player);
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawString(String.valueOf(player.getPlayerPosition().getX()), 570, 60);
    g.drawString(String.valueOf(player.getPlayerPosition().getY()), 650, 60);
  }
}
