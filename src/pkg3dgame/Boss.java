/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Boss extends GameObject{

    private Random r = new Random();

    private Handler handler;

    private boolean Init = true;

    public int ObjWid = 64, ObjHei = 64;

    private int bossLevel = 0;

    public Boss(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        velX = 0;
        velY = 0;





    }
    public void setW(boolean a) { }
    public void setS(boolean a) { }
    public void setA(boolean a) { }
    public void setD(boolean a) { }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x , (int)y , ObjWid, ObjHei );
    }

    public void tick()
    {
        int i = 0;
        x += velX * Game.getScale();
        y += velY * Game.getScale();

        ObjHei = 64 * Game.getScale();
        ObjWid = 64 * Game.getScale();
        if(bossLevel != 0)
        {
            if(y <= 0 || y >= Game.HEIGHT - ObjHei) this.velY *= -1;
            if(x <= 0 || x >= Game.WIDTH - ObjWid ) this.velX *= -1;
        }




        if(bossLevel == 0)
        {
            float diffX =  0;
            float diffY =  y - 100;

            double distance = Math.sqrt((x - (ObjWid / 2)) * (x - (ObjWid / 2)) + (y - 100) * y - (y - 100) * 100);

            velX = (float)  ((-1.0f/distance) * diffX);
            Main.clamp(velX , -3 , 3);
            velY = (float)  ((-1.0f/distance) * diffY);
            Main.clamp(velY , 2 , 3);
            if(y >= 60)
            {
                velY = 0;
                System.out.println("Trocando para o Level 1");
                bossLevel = 1;
            }




        }else if(bossLevel == 1)
        {
            HUD.setShowBossHealth(true);
            if(Init)
            {
                setVelX(3);
                setVelY(3);
                Init = false;
            }


        }


        handler.addObject(new Traill((int)x, (int)y, ID.Traill, Color.red, ObjWid, ObjHei, 0.09f,handler));

    }

    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,ObjWid,ObjHei);


    }
    public void destroy(){}
}
