/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.*;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */


public class Player extends GameObject{

    private BufferedImage[] PlayerImage = new BufferedImage[5];

    //private String Name = "Lucas";

    private int ActualImage = 4;

    private Color color = Color.WHITE;

    private int ObjHei = 34, ObjWid = 48;

    protected boolean[] keyDown = new boolean[4];

    private boolean isTraill = false;

	Random r = new Random();
	
	private AffineTransform at = AffineTransform.getTranslateInstance( (int) x ,(int) y);
	
	
    
    Handler handler;

    
    public Player(int x, int y, ID id, Handler handler)
    {
        super(x ,y,id);


        this.handler = handler;
        PlayerImage[0] = Main.LoadImage("./Cima.png");
        PlayerImage[1] = Main.LoadImage("./Baixo.png");
        PlayerImage[2] = Main.LoadImage("./Esquerda.png");
        PlayerImage[3] = Main.LoadImage("./direita.png");
        PlayerImage[4] = Main.LoadImage("./Estatico.png");

        PosX = round(x/Game.getScale());
        PosY = round(y/Game.getYScale());

        this.x += Game.addX;
        this.y += Game.addY;

    }



    int seconds = 5 * 60;
    public void tick()
    {
        ObjHei = round(34 * Game.getYScale());
        ObjWid = round(48 * Game.getScale());



        if(keyDown[0]){this.setVelY(-3); ActualImage = 0;}
        if(keyDown[1]){this.setVelY(3); ActualImage = 1;}
        if(keyDown[2]){this.setVelX(-3); ActualImage = 2;}
        if(keyDown[3]){this.setVelX(3); ActualImage = 3;}

        if(keyDown[0] == true && keyDown[1] == true)
        {
            this.setVelY(0); ActualImage = 4;
            if (keyDown[2]) {
                
                ActualImage = 2;
            }
            if (keyDown[3]) {
            
                ActualImage = 3;
            }
        }
        if(keyDown[2] == true && keyDown[3] == true)
        {
            this.setVelX(0); ActualImage = 4;
            if (keyDown[0]) {
                
                ActualImage = 0;
            }
            if (keyDown[1]) {
                
                ActualImage = 1;
            }
            
        }
        isTraill = true;

    	velX = Main.clamp(velX, -3, 3);

        velY = Main.clamp(velY, -3, 3);

        if(velX == 0 && velY == 0)
        {
            ActualImage = 4;

            isTraill = false;

        }

        if(seconds > 0)
        {
            seconds--;
        }

        PosX += velX;
        PosY += velY;

        this.PosX = (int) Main.clamp(this.PosX,0,(Game.WIDTH - ObjWid)/Game.getScale());
        this.PosY = (int) Main.clamp(PosY,0,(Game.HEIGHT - ObjHei)/Game.getYScale());

        this.x = Main.clamp(this.x,0,Game.WIDTH - ObjWid);
        this.y = Main.clamp(this.y,0,Game.HEIGHT - ObjHei);


        x = PosX * Game.getScale();
        y = PosY * Game.getYScale();


        this.x = (int) Main.clamp(this.x,0,Game.WIDTH - ObjWid);
        this.y = (int) Main.clamp(this.y,0,Game.HEIGHT - ObjHei);

        x += Game.addX;
        y += Game.addY;

        handler.addObject(new Traill(at, ID.Trail, PlayerImage[ActualImage], 0.09f, handler));
        //handler.addObject(new Traill((int) x, (int) y, ID.Trail, color, ObjWid, ObjHei, 0.09f, handler));

        collision();
        this.setVelX(0);
        this.setVelY(0);

    }

    public void collision()
    {
        for(int i = 0; i < handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() ==  ID.FastEnemy || tempObject.getID() ==  ID.CornerEnemy || tempObject.getID() ==  ID.SmartEnemy)
            {
                //collision code
                if(this.getBounds().intersects(tempObject.getBounds()))
                {
                     HUD.HEALTH -= HUD.Dif;
                }
            }
            if(tempObject.getID() == ID.Boss)
            {
                if(this.getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH -= 5;
                }
            }
            if(tempObject.getID() == ID.Coin)
            {
                if(this.getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH += 10;
                    tempObject.destroy();
                }
            }
        }
    }

    public void render(Graphics g)
    {
        //g.setColor(color);
        //g.fillRect((int)x,(int)y,ObjWid,ObjHei);

        at = AffineTransform.getTranslateInstance( (int) x,(int) y);
        at.scale(2 * Game.getScale(), 2 * Game.getYScale());
        

        //triangle boincing in the player's head
        AffineTransform Tr =  AffineTransform.getTranslateInstance( (PosX + 2) * Game.getScale() + Game.addX,(PosY - seconds % 20 - 24) * Game.getYScale() + Game.addY);

        Graphics2D g2d = (Graphics2D) g;
        /*
        if(isTraill)
        {
            handler.addObject(new Traill((int)x, (int)y, ID.Traill, color, ObjWid, ObjHei, 0.10f,handler));
        }

        //handler.addObject(new Traill(at , ID.Traill,  PlayerImage[ActualImage]));

        if(isTraill)
        {
            handler.addObject(new Traill((int)x, (int)y, ID.Traill, color, ObjWid, ObjHei, 0.1f,handler));
        }
        */
        //g2d.drawImage(PlayerImage[ActualImage], at, null);
        
        g2d.drawImage(PlayerImage[ActualImage], at, null);



    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x , (int)y , ObjWid, ObjHei);
    }
    public void setW(boolean a)
    {
    	keyDown[0] = a;
    }
    public void setS(boolean a)
    {
    	keyDown[1] = a;
    }
    public void setA(boolean a)
    {
    	keyDown[2] = a;
    }
    public void setD(boolean a)
    {
    	keyDown[3] = a;
    }
    public void destroy(){}
}
