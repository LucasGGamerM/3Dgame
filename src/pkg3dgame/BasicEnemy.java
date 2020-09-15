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
public class BasicEnemy extends GameObject{

    private Color color = Color.RED;

    private Handler handler;

    private int ObjHei = 16, ObjWid = 16;
    
    public BasicEnemy(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        velX = 5;
        velY = 5;

        ObjHei = 16 * Game.getScale();
        ObjWid = 16 * Game.getScale();
        
       
            
        
    }
    public void setW(boolean a) { } ;
    public void setS(boolean a) { } ;
    public void setA(boolean a) { } ;
    public void setD(boolean a) { } ;
    public Rectangle getBounds()
    {
        return new Rectangle((int)x , (int)y , ObjWid, ObjHei);
    }
    public void destroy(){};

    public void tick()
    {
        x += velX * Game.getScale();
        y += velY * Game.getScale();

        ObjHei = 16 * Game.getScale();
        ObjWid = 16 * Game.getScale();

        if(y <= 0 || y >= Game.HEIGHT - ObjHei) { this.velY *= -1;}
        if(x <= 0 || x >= Game.WIDTH - ObjWid) { this.velX *= -1;}
        handler.addObject(new Traill((int)x, (int)y, ID.Traill, color, ObjWid, ObjHei, 0.05f,handler));

        Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        Main.clamp(y , 0 , Game.HEIGHT - ObjHei);

    }
    
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,ObjWid,ObjHei);
        
        
    }
    
}
