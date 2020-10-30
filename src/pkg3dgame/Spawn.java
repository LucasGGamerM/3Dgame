
package pkg3dgame;

import java.util.Random;

import static java.lang.Math.round;

public class Spawn {

	private Handler handler;

	private HUD HUD;

	private int scoreKeep = 0;

	private Random r;

	private boolean Init = false;
	private static boolean IsBoss = false;

	private Dialog dialog;

	private Game game;

	public Spawn(Handler handler, HUD hud, Dialog dialog, Game game)
	{
		this.dialog = dialog;
		this.handler = handler;
		this.HUD = hud;
		this.game = game;
		r = new Random();

	}
	public void tick()
	{
		if(Init == false)
		{
			handler.addObject(new Player(Game.WIDTH/2 - 32,Game.HEIGHT/2 - 32,ID.Player, handler));
            System.out.println("It Created the Player!");
		}

		Init = true;
		scoreKeep++;
		if(scoreKeep == 10)
		{
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT - (int)Game.getYScale() * 50),ID.SmartEnemy, handler));
			for(int j = 0; j < 3 * HUD.Dif; j++)
			{
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT  - (int)Game.getYScale() * 50),ID.BasicEnemy, handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT - (int)Game.getYScale() * 50),ID.Coin, handler));
			}
			if(IsBoss)
			{
				for(int k = 0; k < 5 * HUD.Dif; k++)
				{
					handler.addObject(new Coin(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT - (int)Game.getYScale() * 50),ID.Coin,handler) );
				}
			}

			System.out.println("Pondo Level 1");

		}else if(scoreKeep == 700)
		{
			
			HUD.setLevel(HUD.getLevel() + 1);
			for(int i = 0; i < 3 * HUD.Dif; i++)
			{
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT - (int)Game.getYScale() * 50),ID.FastEnemy, handler));
				handler.addObject(new CornerEnemy(1,1,ID.CornerEnemy, handler));


				System.out.println("Pondo Level 2");
			}
			for(int i = 0; i < 10/ pkg3dgame.HUD.Dif; i++)
			{
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - (int)Game.getScale() * 50),r.nextInt(Game.HEIGHT - (int)Game.getYScale() * 50),ID.Coin,handler) );
			}
			if(IsBoss)
			{
				scoreKeep = 0;
			}
		//valor normal 1000
		}else if(HUD.getScore() == 1000)
		{

			handler.addObject(new Boss((Game.WIDTH/2) - (64 * round(Game.getScale())) / 2, - (64 * round(Game.getScale())) / 2,ID.Boss, handler));
			IsBoss = true;
			scoreKeep = 0;
			//dialog.createDialog("Lucas é muito legal" , true);

		}




	}

	public void killAllEnemies(boolean killBoss, boolean resetScoreKeep) {
		for(int i = 0; i < handler.object.size();)
		{
			GameObject tempObject = handler.object.get(i);
			//if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.CornerEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.FastEnemy)
			if(tempObject.getID() == ID.Player || (tempObject.getID() == ID.Boss && !killBoss) || tempObject.getID() == ID.Trail)
			{
				i++;
				continue;
			}else{
				System.out.println("Removido Objeto: " + i + " de ID: " +  handler.object.get(i).getID());
				handler.removeObject(handler.object.get(i));

			}

		}
		if(resetScoreKeep)
		{
			scoreKeep = 0;
		}
		

	}
	public static boolean getIsBoss()
	{
		return IsBoss;
	}
	
	public static void setIsBoss(boolean a) {
		IsBoss = a;
	}

}
