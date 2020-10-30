/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import pkg3dgame.Game.STATE;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class KeyInput extends KeyAdapter{
    
	
	
    private Handler handler;
    
    private Game game;
    
    public KeyInput(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;
        System.out.println("It Created!");
        
    }
  
    public void keyPressed(KeyEvent e)
    {
        
        int key = e.getKeyCode();

        Game.Init = true;
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player)
            {
                if(key == KeyEvent.VK_W && game.gameState == STATE.Game)tempObject.setW(true);
        
                if(key == KeyEvent.VK_S && game.gameState == STATE.Game)tempObject.setS(true);
                
                if(key == KeyEvent.VK_A && game.gameState == STATE.Game)tempObject.setA(true);
                
                if(key == KeyEvent.VK_D && game.gameState == STATE.Game)tempObject.setD(true);  
            }
        }
        if(key == KeyEvent.VK_ESCAPE)
        {
            if(game.gameState == STATE.Menu)
            {
                game.gameState = STATE.Game;
            }else{
                game.gameState = STATE.Menu;
            }
        }
        
        
    }
    public void keyReleased(KeyEvent e)
    {
      
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player)
            {
                if(e.getKeyCode() == KeyEvent.VK_W && game.gameState == STATE.Game) 
                {
                	tempObject.setW(false);
                	
                	
                }
        
                if(e.getKeyCode() == KeyEvent.VK_S && game.gameState == STATE.Game)
                {
                	tempObject.setS(false);
                	
               	}
                
                if(e.getKeyCode() == KeyEvent.VK_A && game.gameState == STATE.Game) 
                {
                	tempObject.setA(false);
                }
                
                if(e.getKeyCode() == KeyEvent.VK_D && game.gameState == STATE.Game) 
                {
                	tempObject.setD(false);
                }
                
                    
                
                
            }
        } 
    }
    
}
