package pkg3dgame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
//import java.util.Random;

import pkg3dgame.Game.STATE;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu extends MouseAdapter{



	private Game game;
	private HUD hud;

	public static float TraillLifeOp = 1f;

	public static float TraillLife = (float) 1 / TraillLifeOp;
	//private Handler handler;
	//private Random r = new Random();


	private boolean mouseOverEasy = false;
	private boolean mouseOverStart = false;
	private boolean mouseOverContinue = false;
	private boolean mouseOverQuit = false;
	private boolean mouseOverOption = false;
	private boolean mouseOverBack = false;

	public static int mx;
	public static int my;

	public Menu(Game game, HUD hud)
	{
		this.game = game;
		this.hud = hud;
		//this.handler = handler;

	}

	public void mouseMoved(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();

	}
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();



	}

	public void mouseReleased(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();

		if (mouseOver(mx, my, (Game.WIDTH / 2) - 125, (Game.HEIGHT / 2) - 160, 250, 64) && game.gameState == STATE.Option) {
			if(game.gameDificulty == Game.DIFICULTY.FACIL)
			{
				game.gameDificulty = Game.DIFICULTY.NORMAL;
			}else if(game.gameDificulty == Game.DIFICULTY.NORMAL)
			{
				game.gameDificulty = Game.DIFICULTY.DIFICIL;
			}else if(game.gameDificulty == Game.DIFICULTY.DIFICIL)
			{
				game.gameDificulty = Game.DIFICULTY.INSANO;
			}else if(game.gameDificulty == Game.DIFICULTY.INSANO)
			{
				game.gameDificulty = Game.DIFICULTY.BRUTAL;
			}else if(game.gameDificulty == Game.DIFICULTY.BRUTAL)
			{
				game.gameDificulty = Game.DIFICULTY.FACIL;
			}

		}
		if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) - 75, 30, 27) && game.gameState == STATE.Option)
		{
			TraillLifeOp += 0.1f;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) - 48, 30, 27) && game.gameState == STATE.Option)
		{
			TraillLifeOp -= 0.1f;
		}
		/*
		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 160, 250, 64) && game.gameState == STATE.Option)
		{
			game.gameDificulty = Game.DIFICULTY.FACIL;

		}

		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64) && game.gameState == STATE.Option)
		{
			game.gameDificulty = Game.DIFICULTY.NORMAL;

		}

		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) , 250, 64 )&& game.gameState == STATE.Option)
		{
			game.gameDificulty = Game.DIFICULTY.DIFICIL;

		}

		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64) && game.gameState == STATE.Option)
		{
			game.gameDificulty = Game.DIFICULTY.INSANO;


		}

		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64) && game.gameState == STATE.Option)
		{
			game.gameDificulty = Game.DIFICULTY.BRUTAL;

			System.out.println("Vai sair?");
		}
		*/
		if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) + 85, 30, 27))
		{
			Game.master += 10;
			Game.master = (int)Main.clamp(Game.master, 0, 100);
			Game.mainSound.setVolume((float)Game.master/100);
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) + 112, 30, 27))
		{
			Game.master -= 10;
			Game.master = (int)Main.clamp(Game.master, 0, 100);
			Game.mainSound.setVolume((float)Game.master/100);
		}

		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64) && game.gameState == STATE.Menu)
		{
			game.reset();
			game.gameState = STATE.Game;

		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2), 250, 64) && game.gameState == STATE.Menu)
		{
			if(HUD.HEALTH != 0)
			{
				game.gameState = STATE.Game;

				System.out.println("Sispause");
			}

		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, (Game.HEIGHT/2) + 20, 64) && game.gameState == STATE.Menu)
		{

			game.gameState = STATE.Option;

		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, (Game.HEIGHT/2) + 20, 64) && game.gameState == STATE.Lost)
		{
			game.gameState = STATE.Menu;

		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64) && game.gameState == STATE.Menu)
		{
			System.out.println("Saindo");
			System.exit(3);
		}
		//voltar
		if(mouseOver(mx, my, Game.WIDTH/2 - 60, Game.HEIGHT - 45, 120, 40) && game.gameState == STATE.Option)
		{
			game.gameState = STATE.Menu;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2), 250, 64) && game.gameState == STATE.Option)
		{
			Game.Music = !Game.Music;
			if(Game.Music){}
		}




	}

	public static boolean mouseOver(int mx, int my, int x, int y , int width, int height)
	{
		if(mx > x + Game.addX && mx < x + Game.addX + width)
		{
			if(my > y + Game.addY && my < y + Game.addY + height)
			{
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public void tick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		//System.out.println("Tick");
		Point p = MouseInfo.getPointerInfo().getLocation();
		mx =  (p.x - Window.getXFrameLocation());
		my =  (p.y - Window.getYFrameLocation());
		//System.out.println((Window.getYFrameLocation()));
		//System.out.println("Pegou ponteiro " + mx + " " + my);

		TraillLife = (float) 1 / TraillLifeOp;

		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64))
		{
			mouseOverStart = true;

		}else{
			mouseOverStart = false;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2), 250, 64))
		{
			mouseOverContinue = true;
		}else{
			mouseOverContinue = false;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64))
		{
			mouseOverOption = true;
		}else{
			mouseOverOption = false;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64))
		{
			mouseOverQuit = true;
		}else{
			mouseOverQuit = false;
		}
		if(mouseOver(mx,my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 160, 250, 64))
		{
			mouseOverEasy = true;
		}else{
			mouseOverEasy = false;
		}
		if(mouseOver(mx, my, Game.WIDTH/2 - 60, Game.HEIGHT - 45, 120, 40))
		{
			mouseOverBack = true;

		}else{
			mouseOverBack = false;
		}


	}



	public void render(Graphics g)
	{

		Font fnt = new Font("arial", 1 , 50);
		Font fnt2 = new Font("arial", 1 , 30);
		if(game.gameState == STATE.Menu)
		{
			//declara as fontes

			g.setFont(fnt);

			//desenha Menu

			g.setColor(Color.white);g.setColor(Color.white);
			g.drawString("Arcade Adventure", 50 + Game.addX, 60 + Game.addY);

			g.setFont(fnt2);

			//desenha as strings do menu

			g.drawString("New Game", (Game.WIDTH/2) - 110 + Game.addX, (Game.HEIGHT/2) - 50 + Game.addY);
			g.drawString("Continue", (Game.WIDTH/2) - 110 + Game.addX, (Game.HEIGHT/2) + 30 + Game.addY);
			g.drawString("Options", (Game.WIDTH/2) - 110 + Game.addX, (Game.HEIGHT/2) + 110 + Game.addY);
			g.drawString("Quit", (Game.WIDTH/2) - 110 + Game.addX, (Game.HEIGHT/2) + 190 + Game.addY);

			//desenha os botoes


				if(mouseOverStart)
				{
					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) - 75 + Game.addY, 240, 54);

				}
				if(mouseOverContinue)
				{
					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) + 5 + Game.addY, 240, 54);
				}
				if(mouseOverOption)
				{
					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) + 85 + Game.addY, 240, 54);
				}
				if(mouseOverQuit)
				{

					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) + 165 + Game.addY, 240, 54);
				}



			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) - 80 + Game.addY, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2)  + Game.addY, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) + 80 + Game.addY, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) + 160 + Game.addY, 250, 64);

		}else if(game.gameState == STATE.Lost)
		{
			g.setFont(fnt);

			g.setColor(Color.white);g.setColor(Color.white);
			g.drawString("You died.", 11 + Game.addX, 50 + Game.addY);

			g.setFont(fnt2);

			g.drawString("Your Score: " + hud.getScore(),(Game.WIDTH/2) - 300 + Game.addX, 200 + Game.addY);

			if(mouseOverOption)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) + 85 + Game.addY, 240, 54);
			}



			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) + 80 + Game.addY, 250, 64);
			g.drawString("Ok", (Game.WIDTH/2) - 110 + Game.addX, (Game.HEIGHT/2) + 110 + Game.addY);
		}else if(game.gameState == STATE.Option)
		{
			//declara as fontes


			g.setFont(fnt);

			//desenha Menu

			g.setColor(Color.white);
			g.drawString("Options", 11 + Game.addX, 50 + Game.addY);


			g.setFont(fnt2);

			//desenha as strings do menu
			if(game.gameDificulty == Game.DIFICULTY.FACIL)
			{
				g.drawString("Easy", (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 130 + Game.addY);
			}else if (game.gameDificulty == Game.DIFICULTY.NORMAL)
			{
				g.drawString("Normal", (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 130 + Game.addY);
			}else if (game.gameDificulty == Game.DIFICULTY.DIFICIL)
			{
				g.drawString("Hard", (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 130 + Game.addY);
			}else if (game.gameDificulty == Game.DIFICULTY.INSANO)
			{
				g.drawString("Insane", (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 130 + Game.addY);
			}else if (game.gameDificulty == Game.DIFICULTY.BRUTAL)
			{
				g.drawString("Brutal", (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 130 + Game.addY);
			}
			TraillLifeOp = Main.clamp(TraillLifeOp, 0.1f, 5);
			g.drawString("Shadows: " +  new DecimalFormat("#.0").format(TraillLifeOp), (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) - 50 + Game.addY);
			g.drawString("Back", Game.WIDTH/2 - 55 + Game.addX, Game.HEIGHT - 15 + Game.addY);

			g.drawString("Music: " + Game.Music, (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) + 30 + Game.addY);
			g.drawString("Volume: " + Game.master, (Game.WIDTH / 2) - 110 + Game.addX, (Game.HEIGHT / 2) + 110 + Game.addY);


			//desenha os botoes


			if(mouseOverEasy)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) - 155 + Game.addY, 240, 54);
			}
			/*
			if(mouseOverStart)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) - 75, 240, 54);
			}
			*/
			if(mouseOverContinue)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120 + Game.addX, (Game.HEIGHT/2) + 5 + Game.addY, 240, 54);
			}
			/*
			if(mouseOverOption)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) + 85, 240, 54);
			}
			if(mouseOverQuit)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) + 165, 240, 54);
			}
			*/
			if(mouseOverBack)
			{
				g.drawRect(Game.WIDTH/2 - 55 + Game.addX, Game.HEIGHT - 40 + Game.addY, 110, 30);
			}



			g.setColor(Color.WHITE);
			g.drawRect((Game.WIDTH/2) + 90 + Game.addX, (Game.HEIGHT/2) - 75 + Game.addY, 30, 27);
			if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) - 75, 30, 27))
			{
				g.drawRect((Game.WIDTH/2) + 95 + Game.addX, (Game.HEIGHT/2) - 70 + Game.addY, 20, 17);

			}
			g.setColor(Color.WHITE);
			g.drawRect((Game.WIDTH/2) + 90 + Game.addX, (Game.HEIGHT/2) - 48 + Game.addY, 30, 27);
			if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) - 48, 30, 27))
			{
				g.drawRect((Game.WIDTH/2) + 95 + Game.addX, (Game.HEIGHT/2) - 43 + Game.addY, 20, 17);
			}

			g.setColor(Color.WHITE);
			g.drawRect((Game.WIDTH/2) + 90 + Game.addX, (Game.HEIGHT/2) + 85 + Game.addY, 30, 27);
			if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) + 85, 30, 27))
			{
				g.drawRect((Game.WIDTH/2) + 95 + Game.addX, (Game.HEIGHT/2) + 90 + Game.addY, 20, 17);

			}
			g.setColor(Color.WHITE);
			g.drawRect((Game.WIDTH/2) + 90 + Game.addX, (Game.HEIGHT/2) + 112 + Game.addY, 30, 27);
			if(mouseOver(mx,my,(Game.WIDTH/2) + 90, (Game.HEIGHT/2) + 112, 30, 27))
			{
				g.drawRect((Game.WIDTH/2) + 95 + Game.addX, (Game.HEIGHT/2) + 117 + Game.addY, 20, 17);
			}
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) + 80 + Game.addY, 250, 64);


			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) - 160 + Game.addY, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) - 80 + Game.addY, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125 + Game.addX, (Game.HEIGHT/2) + Game.addY, 250, 64);
			/*
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) , 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64);

*/

			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2 - 60 + Game.addX, Game.HEIGHT - 45 + Game.addY, 120, 40);

		}
	}
}
