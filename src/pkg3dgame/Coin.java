/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import pkg3dgame.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;



import static java.lang.Math.round;

/**
 *    Testing pull requests
 * @author lucasgabrielpatriciodoamaral
 */
public class Coin extends GameObject {

    private Color color = Color.YELLOW;

    private Handler handler;

    private int ObjHei = 12, ObjWid = 8;

    private BufferedImage CoinImage;

    private int PosX, PosY;

    public Coin(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);

        x = (int) Main.clamp(x, 1, Game.WIDTH - 1);
        y = (int) Main.clamp(y, 1, Game.HEIGHT - 1);




        this.handler = handler;

        velX = 0;
        velY = 0;

        CoinImage = Main.LoadImage("./Moedinha.png");

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

    public void tick()
    {
        PosX = (int) Main.clamp(PosX, 0, Game.WIDTH - ObjWid/ Game.getScale());
        PosY = (int) Main.clamp(PosY, 0, Game.HEIGHT - ObjHei/ Game.getYScale());


        PosX += velX;
        PosY += velY;

        x = PosX * Game.getScale();
        y = PosY * Game.getYScale();

        ObjHei = round(24 * Game.getYScale());
        ObjWid = round(16 * Game.getScale());


        

        Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        Main.clamp(y , 0 , Game.HEIGHT - ObjHei);

        x += Game.addX;
        y += Game.addY;


    }

    public void render(Graphics g)
    {
        g.setColor(color);
        //g.fillRect((int)x,(int)y,ObjWid,ObjHei);
        AffineTransform at = AffineTransform.getTranslateInstance( (int) x ,(int) y);
        at.scale(2 * Game.getScale(), 2 * Game.getScale());

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(CoinImage, at, null);

    }
    public void destroy()
    {
        handler.removeObject(this);
        if (Spawn.getIsBoss()) {
            HUD.BOSSHEALTH -= 5;
        }

    }

}
