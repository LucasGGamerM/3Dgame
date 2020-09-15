package pkg3dgame;

import java.util.Random;

public class Spawn {

	private Handler handler;

	private HUD hud;

	private int scoreKeep = 0;

	private Random r;

	private boolean Init = false, IsBoss = false;

	private Dialog dialog;

	public Spawn(Handler handler, HUD hud, Dialog dialog)
	{
		this.dialog = dialog;
		this.handler = handler;
		this.hud = hud;
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
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.SmartEnemy, handler));
			for(int j = 0; j < 5; j++)
			{
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.BasicEnemy, handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.Coin, handler));
			}
			if(IsBoss)
			{
				for(int k = 0; k < 10; k++)
				{
					handler.addObject(new Coin(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.Coin,handler) );
				}
			}

			System.out.println("Pondo Level 1");

		}else if(scoreKeep == 700)
		{

			hud.setLevel(hud.getLevel() + 1);
			for(int i = 0; i < 5; i++)
			{
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.FastEnemy, handler));
				handler.addObject(new CornerEnemy(0,0,ID.CornerEnemy, handler));
				for(i = 0; i < 5; i++)
				{
					handler.addObject(new Coin(r.nextInt(Game.WIDTH - 30),r.nextInt(Game.HEIGHT - 30),ID.Coin,handler) );
				}

				System.out.println("Pondo Level 2");
			}
			if(IsBoss)
			{
				scoreKeep = 0;
			}

		}else if(hud.getScore() == 1000)
		{

			handler.addObject(new Boss((Game.WIDTH/2) - (64 * Game.getScale()) / 2, - (64 * Game.getScale()) / 2,ID.Boss, handler));
			IsBoss = true;
			scoreKeep = 0;
			//dialog.createDialog("Lucas é muito legal" , true);

		}




	}

	private void killAllEnemies(boolean killBoss) {
		for(int i = 0; i < handler.object.size();)
		{
			GameObject tempObject = handler.object.get(i);
			//if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.CornerEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.FastEnemy)
			if(tempObject.getID() == ID.Player || (tempObject.getID() == ID.Boss && !killBoss) || tempObject.getID() == ID.Traill)
			{
				i++;
				continue;
			}else{
				System.out.println("Removido Objeto: " + i + " de ID: " +  handler.object.get(i).getID());
				handler.removeObject(handler.object.get(i));

			}

		}

	}

}
