package Hud;

import Map.Coordinate;
import States.Camera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JProgressBar;
import Entity.Player;

public class HealthBar extends Hud {

  private int value = 100;
  private Player player;
  private Camera camera;
  private Coordinate position;

  public HealthBar(Player player, Camera camera, Coordinate position) {
    super(player);
    this.player = player;
    this.camera = camera;
    this.position = position;
  }

  public void draw(Graphics2D g) {

    if (position.getX() >= camera.getX() - 2000 && position.getX() <= camera
        .getX() + 2000 && position.getY() >= camera.getY() - 2000 && position
        .getY() <= camera.getY() + 2000) {
      g.drawRect(position.getX() - 50 - camera.getX() + 640, position.getY() -
          camera.getY() - 60 + 480, value, 20);
    }

    g.setColor(Color.white);
    //g.fillRect(player.getPlayerPosition().getX(),
    //    player.getPlayerPosition().getY(), player.getHealth(), 20);
  }

  public void setValue(int value) {
    this.value = value;
  }
}
