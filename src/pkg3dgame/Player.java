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
public class Player extends GameObject{

    private Color color = Color.WHITE;

    private int ObjHei = 32 * Game.getScale(), ObjWid = 32 * Game.getScale();

    protected boolean[] keyDown = new boolean[4];

	  Random r = new Random();
    Handler handler;

    public Player(int a, int b, ID id1, Handler handler)
    {
        super(a,b,id1);
        this.handler = handler;

    }




    public void tick()
    {
        ObjHei = 32 * Game.getScale();
        ObjWid = 32 * Game.getScale();

        if(keyDown[0])this.setVelY(-3);
        if(keyDown[1])this.setVelY(3);
        if(keyDown[2])this.setVelX(-3);
        if(keyDown[3])this.setVelX(3);

    	if(keyDown[0] == true && keyDown[1] == true)this.setVelY(0);
        if(keyDown[2] == true && keyDown[3] == true)this.setVelX(0);



    	Main.clamp(velX, -3, 3);

        Main.clamp(velY, -3, 3);

            this.x += this.velX * Game.getScale();
            this.y += this.velY * Game.getScale();

            this.x = (int) Main.clamp(this.x,0,Game.WIDTH - ObjWid);
            this.y = (int) Main.clamp(this.y,0,Game.HEIGHT - ObjHei);
            handler.addObject(new Traill((int)x, (int)y, ID.Traill, color, ObjWid, ObjHei, 0.1f,handler));
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
                     HUD.HEALTH -= 2;
                }
            }
            if(tempObject.getID() == ID.Boss)
            {
                if(this.getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH -= 10;
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
        g.setColor(color);
        g.fillRect((int)this.x, (int)this.y, ObjWid, ObjHei);

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
