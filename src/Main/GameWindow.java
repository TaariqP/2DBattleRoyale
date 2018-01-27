package Main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

  public static void main(String[] args){
    new GameWindow();
  }

  public GameWindow(){
    super("IC Hack Game");
    this.setContentPane(new GameView());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

}
