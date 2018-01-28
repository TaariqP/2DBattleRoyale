package Hud;

import Entity.Player;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class weaponBar extends Hud {

  private BufferedImage image;
  private int ammo;

  public weaponBar(Player player) {
    super(player);
    ammo = 0;
    reload();
  }

  public void reload() {
    if (player.equippedWeapon()) {
      if (player.getWeapon().getWeaponName().equals("Machine Gun")) {
        image = null;
        File file = new File("PNG/mg.png");
        try {
          image = ImageIO.read(file);
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        image = null;
        File file = new File("PNG/pistol.png");
        try {
          image = ImageIO.read(file);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      ammo = player.getWeapon().getCURRENT_CLIP();
    }
  }

  @Override
  public void draw(Graphics2D g) {
    reload();
    if (player.equippedWeapon()) {
      g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
      g.drawImage(image, 40, 200, null);
      g.drawString(Integer.toString(ammo), 60, 190);
    }
  }
}
