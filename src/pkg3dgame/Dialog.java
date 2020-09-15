package pkg3dgame;

import java.awt.*;


public class Dialog {

    private Game game;

    private String Dialog;



    public Dialog(Game game)
    {
      this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
      g.setColor(Color.WHITE);

      g.fillRect(10, Game.HEIGHT - 90, Game.WIDTH -10 , Game.HEIGHT - 10);

      g.drawString(Dialog, 15 , 15);
    }


    public void createDialog(String Dialog, boolean pause)
    {

      if(pause)
      {
          game.gameState = Game.STATE.Dialog;
      }
      this.Dialog = Dialog;


    }

}
