/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import static java.lang.Math.round;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class CornerEnemy extends GameObject{

    private Color color = new Color(99,33,99);

    private int ObjHei = 16, ObjWid = 16;

    private Handler handler;

    private int PosX, PosY;
    
    public void setW(boolean a) { } ;
    public void setS(boolean a) { } ;
    public void setA(boolean a) { } ;
    public void setD(boolean a) { } ;
    
    public CornerEnemy(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);

        x = (int) Main.clamp(x, 1, Game.WIDTH - 1);
        y = (int) Main.clamp(y, 1, Game.HEIGHT - 1);



        this.handler = handler;
        velX = 2;
        velY = 10;
        PosX = round(x/Game.getScale());
        PosY = round(y/Game.getYScale());

        this.x += Game.addX;
        this.y += Game.addY;




    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x ,(int) y , ObjWid, ObjHei );
    }

    public void destroy(){}

    public void tick()
    {
        PosX = (int) Main.clamp(PosX, 0, Game.WIDTH / Game.getScale());
        PosY = (int) Main.clamp(PosY, 0, Game.HEIGHT / Game.getYScale());

        PosX += velX;
        PosY += velY;

        x = PosX * Game.getScale();
        y = PosY * Game.getYScale();



        ObjHei =  round(16 * Game.getScale());
        ObjWid = round(16 * Game.getScale());


        if(y <= 0 || y >= Game.HEIGHT - ObjHei) { this.velY *= -1;}
        if(x <= 0 || x >= Game.WIDTH - ObjWid) { this.velX *= -1;}

        Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        Main.clamp(y , 0 , Game.HEIGHT - ObjHei);

        x += Game.addX;
        y += Game.addY;

        handler.addObject(new Traill((int)x, (int)y, ID.Trail, color, ObjWid, ObjHei, 0.05f,handler));


    }
    
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,ObjWid,ObjHei);
        
        
    }
    
}
