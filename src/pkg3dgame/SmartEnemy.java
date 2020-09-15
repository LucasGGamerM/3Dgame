/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class SmartEnemy extends GameObject{

    private int ObjHei = 16, ObjWid = 16;

    private Color color = Color.GREEN;

    private Handler handler;
    
    private GameObject player;
    
    public SmartEnemy(float x , float y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i ++)
        {
        	if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }


    public void setW(boolean a) { } ;
    public void setS(boolean a) { } ;
    public void setA(boolean a) { } ;
    public void setD(boolean a) { } ;
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)x , (int)y , ObjWid, ObjHei );
    } 
    
    public void tick()
    {
        float diffX =  x - player.getX() - 12;
        float diffY =  y - player.getY() - 12;

        x += velX * Game.getScale();
        y += velY * Game.getScale();

        ObjHei = 16 * Game.getScale();
        ObjWid = 16 * Game.getScale();
        

        
        double distance = Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())); 
        
        velX = (float)  ((-1.0f/distance) * diffX);
        Main.clamp(velX , -3 , 3);
        velY = (float)  ((-1.0f/distance) * diffY);
        Main.clamp(velY , -3 , 3);
        
        handler.addObject(new Traill((int)x, (int)y, ID.Traill, color, ObjWid, ObjHei, 0.05f,handler));

        Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        Main.clamp(y , 0 , Game.HEIGHT - ObjHei);
    }
    
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,ObjWid,ObjHei);
        
        
    }
    public void destroy(){}
}

