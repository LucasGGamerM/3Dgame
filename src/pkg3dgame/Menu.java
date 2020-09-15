package pkg3dgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.Random;

import pkg3dgame.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private HUD hud;
	//private Handler handler;
	//private Random r = new Random();
	
	public Menu(Game game, HUD hud)
	{
		this.game = game;
		this.hud = hud;
		//this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx , my,(Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64) && game.gameState == STATE.Menu)
		{
			game.reset();
			game.gameState = STATE.Game;
			
		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2), 250, 64)&& game.gameState == STATE.Menu)
		{
			if(HUD.HEALTH != 0)
			{
				game.gameState = STATE.Game;
			
				System.out.println("Sispause");
			}
			
		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64)&& game.gameState == STATE.Menu)
		{
			System.exit(3);
		}
		if(mouseOver(mx, my , (Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, (Game.HEIGHT/2) + 20, 64)&& game.gameState == STATE.Lost)
		{
			game.gameState = STATE.Menu;
					
		}
	}
	
	public void mouseReleased (MouseEvent e)
	{
		
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
			g.drawString("Sair", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 110);

			//desenha os botoes
			
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) - 80, 250, 64);
			
			
			
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) , 250, 64);
			
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);
		}else if(game.gameState == STATE.Lost)
		{
			g.setFont(fnt);
			
			g.setColor(Color.white);g.setColor(Color.white);
			g.drawString("Você Morreu.", 11, 50);
			
			g.setFont(fnt2);
			
			g.drawString("Sua Pontuação foi: " + hud.getScore(),(Game.WIDTH/2) - 300, 200);
			
			
			
			
			g.setColor(Color.white);
			g.drawRect((Game.WIDTH/2) - 125, (Game.HEIGHT/2) + 80, 250, 64);
			g.drawString("Ok", (Game.WIDTH/2) - 110, (Game.HEIGHT/2) + 110);
		}
		
		
		
		
		
	}
}
