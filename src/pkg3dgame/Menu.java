package pkg3dgame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.Random;

import pkg3dgame.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private HUD hud;
	//private Handler handler;
	//private Random r = new Random();


	private boolean mouseOverEasy = false;
	private boolean mouseOverStart = false;
	private boolean mouseOverContinue = false;
	private boolean mouseOverQuit = false;
	private boolean mouseOverOption = false;

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





	}

	private boolean mouseOver(int mx, int my, int x, int y , int width, int height)
	{
		if(mx >  x && mx < x + width)
		{
			if(my > y && my < y + height)
			{
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public void tick()
	{
		//System.out.println("Tick");
		Point p = MouseInfo.getPointerInfo().getLocation();
		mx =  (p.x - Window.getXFrameLocation());
		my =  (p.y - Window.getYFrameLocation());
		//System.out.println((Window.getYFrameLocation()));
		//System.out.println("Pegou ponteiro " + mx + " " + my);

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
			g.drawString("Menu", 11, 50);

			g.setFont(fnt2);

			//desenha as strings do menu

			g.drawString("Novo Jogo", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) - 50);
			g.drawString("Continue", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 30);
			g.drawString("Dificuldade", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 110);
			g.drawString("Sair", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 190);

			//desenha os botoes


				if(mouseOverStart)
				{
					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) - 75, 240, 54);
				}
				if(mouseOverContinue)
				{
					g.setColor(Color.white);
					g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) + 5, 240, 54);
				}
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



			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) , 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64);

		}else if(game.gameState == STATE.Lost)
		{
			g.setFont(fnt);

			g.setColor(Color.white);g.setColor(Color.white);
			g.drawString("Você Morreu.", 11, 50);

			g.setFont(fnt2);

			g.drawString("Sua Pontuação foi: " + hud.getScore(),(Game.WIDTH/2) - 300, 200);

			if(mouseOverOption)
			{

				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) + 85, 240, 54);
			}



			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);
			g.drawString("Ok", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 110);
		}else if(game.gameState == STATE.Option)
		{
			//declara as fontes


			g.setFont(fnt);

			//desenha Menu

			g.setColor(Color.white);g.setColor(Color.white);
			g.drawString("Options", 11, 50);

			g.setFont(fnt2);

			//desenha as strings do menu

			g.drawString("Fácil", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) - 130);
			g.drawString("Médio", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) - 50);
			g.drawString("Difícil", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 30);
			g.drawString("Insano", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 110);
			g.drawString("Brutal", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 190);


			//desenha os botoes


			if(mouseOverEasy)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) - 155, 240, 54);
			}

			if(mouseOverStart)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) - 75, 240, 54);
			}
			if(mouseOverContinue)
			{
				g.setColor(Color.white);
				g.drawRect((Game.WIDTH/2) - 120, (Game.HEIGHT/2) + 5, 240, 54);
			}
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

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 160, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) , 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);

			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 160, 250, 64); 
		}
	}
}
