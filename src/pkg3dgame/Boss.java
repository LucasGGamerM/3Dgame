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
public class Boss extends GameObject {
    
    private Handler handler;

    private Color color = Color.RED;

    private boolean Init = true;
    
    private boolean ActualImage;

    public int ObjWid = 64, ObjHei = 64;

    private int bossLevel = 0;
    
    private BufferedImage EnemyImage;
    
    private AffineTransform at  = AffineTransform.getTranslateInstance( (int) x ,(int) y);
    
    private int PosX, PosY;

    public Boss(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);

        this.handler = handler;
        velX = 0;
        velY = 0;
        PosX = round(x/Game.getScale());
        PosY = round(y/Game.getYScale());

        EnemyImage = Main.LoadImage("./Basicenemy.png");
        
        
        x += Game.addX;
        y += Game.addY;




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
        PosX += velX;
        PosY += velY;

        x = PosX * Game.getScale();
        y = PosY * Game.getYScale();

        ObjHei = round(64 * Game.getYScale());
        ObjWid = round(64 * Game.getScale());
        if(bossLevel != 0)
        {
            if(y <= 0 || y >= Game.HEIGHT - ObjHei) this.velY *= -1;
            if(x <= 0 || x >= Game.WIDTH - ObjWid ) this.velX *= -1;
        }




        if(bossLevel == 0)
        {
            float diffX =  0;
            float diffY =  PosY + 100;

            double distance = PosY - 100;

            velX = (float)  ((-1.0f/distance) * diffX);
            Main.clamp(velX , -3 , 3);
            velY = (float)  ((-1.0f/distance) * diffY);
            Main.clamp(velY , 2 , 3);
            if(PosY >= 40)
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

        x += Game.addX;
        y += Game.addY;
        
        handler.addObject(new Traill((int) x, (int) y, ID.Trail, color, ObjWid, ObjHei, 0.05f, handler));
       
    }

    public void render(Graphics g)
    {
        
        if (velX > 0) {
            ActualImage = true;
        } else {
            ActualImage = false;
        }

        at = AffineTransform.getTranslateInstance((int) x, (int) y);
        at.scale(4f * Game.getScale(), 4f * Game.getScale());
        Main.rotate(at, false, ActualImage, EnemyImage);
        // at.rotate(Math.toDegrees(135));

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(EnemyImage, at, null);
        

    }
    public void destroy(){}
}
