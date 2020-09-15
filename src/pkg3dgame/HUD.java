/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;

import pkg3dgame.Game.STATE;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class HUD {
    
    public static int HEALTH = 100;

    public static int BOSSHEALTH = 100;

    private int greenValue = 255;

    private int redValue = 255;
    
    private int score = 0;
    
    private int level = 1;

    private static boolean showBossHealth = false;


    private Game game;
    
    public HUD(Game game)
    {
    	this.game = game;
    }
    
    public void tick()
    {
       
        HEALTH = (int) Main.clamp(HEALTH, 0, 100);
       
        greenValue = (int) Main.clamp(greenValue, 0, 255);
        greenValue = HEALTH * 2;
        score ++;
        
        if(HEALTH == 0)
        {
        	game.gameState = STATE.Lost;
        	
        	
        	
        	
        }
    }
    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(Game.WIDTH - 224 ,Game.HEIGHT - 64, 200, 32);
        g.setColor(new Color(75,greenValue,0));
        g.fillRect(Game.WIDTH - 220,Game.HEIGHT - 60 , (int) ((double)HEALTH * 1.92), 24);



        //desenha o Nível e a Pontuação
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, Game.HEIGHT - 50);
        g.drawString("Level: " + level, 10, Game.HEIGHT - 34);
        //codigo temporario que tira o jogador depois da vida dele cair a zero;
        
        if(showBossHealth)
        {
            g.setColor(Color.gray);
            g.fillRect(10,10 , Game.WIDTH - 20, 32);
            g.setColor(new Color(redValue,0,0));
            g.fillRect(14, 14, (int) ((double)BOSSHEALTH * ((double)(Game.WIDTH - 28) /100)), 24);
        }
       
    }
    public void setScore(int score)
    {
    	this.score = score;
    }
    public int getScore()
    {
    	return score;
    }
    public int getLevel()
    {
    	return level;
    }
    public void setLevel(int level) 
    {
    	this.level = level;
    }

    public static void setShowBossHealth(boolean a)
    {
        showBossHealth = a;
    }

            
}
