/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class BasicEnemy extends GameObject {

    private Color color = Color.RED;

    private Handler handler;

    private int ObjHei = 24, ObjWid = 24;
    
    private BufferedImage EnemyImage;
    
    private boolean ActualImage;
    
    private AffineTransform at  = AffineTransform.getTranslateInstance( (int) x ,(int) y);



    
    public BasicEnemy(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);


        this.handler = handler;
        EnemyImage = Main.LoadImage("./Basicenemy.png");

        velX = 5;
        velY = 5;
        this.x = Main.clamp(x, 0, Game.WIDTH - ObjWid - Game.addX);
        this.y = Main.clamp(y, 0, Game.HEIGHT - ObjHei - Game.addY);
        PosX = round(x/Game.getScale());
        PosY = round(y/Game.getYScale());
        this.x += Game.addX;
        this.y += Game.addY;
        
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
        PosX = (int) Main.clamp(PosX, 0, (Game.WIDTH - ObjWid) / Game.getScale());
        PosY = (int) Main.clamp(PosY, 0, (Game.HEIGHT - ObjHei) / Game.getYScale());

        PosX += velX;
        PosY += velY;

        x = PosX * Game.getScale();
        y = PosY * Game.getYScale();

        ObjHei = round(24 * Game.getYScale());
        ObjWid = round(24 * Game.getScale());

        if(y <= 0 || y >= Game.HEIGHT - ObjHei) { this.velY *= -1;}
        if(x <= 0 || x >= Game.WIDTH - ObjWid) { this.velX *= -1;}

        Main.clamp(x , Game.addY , Game.WIDTH - ObjWid);
        Main.clamp(y , Game.addX , Game.HEIGHT - ObjHei);

        x += Game.addX;
        y += Game.addY;
        
        handler.addObject(new Traill((int) x, (int) y, ID.Trail, color, ObjWid, ObjHei, 0.05f, handler));
        

    }
    
    public void render(Graphics g)
    {
    	//g.setColor(color);
        
        //g.fillRect((int)x,(int)y,ObjWid,ObjHei);
        
        if (velX > 0) {
            ActualImage = true;
        } else {
            ActualImage = false;
        }
        at = AffineTransform.getTranslateInstance( (int) x ,(int) y);
        at.scale(1.5f * Game.getScale(), 1.5f * Game.getScale());
        Main.rotate(at, false, ActualImage, EnemyImage);
        //at.rotate(Math.toDegrees(135));
        
        Graphics2D g2d = (Graphics2D) g;
        
        
        g2d.drawImage(EnemyImage, at, null);
        
    }
    
}
