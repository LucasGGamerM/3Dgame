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
public class SmartEnemy extends GameObject{

    private int ObjHei = 16, ObjWid = 16;

    private Color color = Color.GREEN;

    private Handler handler;
    
    private GameObject player;
    
    public SmartEnemy(float x , float y, ID id, Handler handler)
    {
        super(x,y,id);

        x = (int) Main.clamp(x, 1, Game.WIDTH - 1);
        y = (int) Main.clamp(y, 1, Game.HEIGHT - 1);

        x += Game.addX;
        y += Game.addY;

        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i ++)
        {
        	if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
        PosX = round(x/Game.getScale());
        PosY = round(y/Game.getYScale());
    }


    public void setW(boolean a) { } ;
    public void setS(boolean a) { } ;
    public void setA(boolean a) { } ;
    public void setD(boolean a) { } ;
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)x + Game.addX, (int)y + Game.addY, ObjWid, ObjHei );
    } 
    
    public void tick()
    {


        float diffX =  (float)(PosX - player.getPosX() - 22);
        float diffY =  (float)(PosY - player.getPosY() - 12);
        /*
        System.out.println("Player: " + player.getPosX() + " : " + player.getPosY());
        System.out.println("Pos: " + PosX + " : " + PosY);
        System.out.println("Diff: " + diffX + " : " + diffY);
        */


        x += velX * Game.getScale();
        y += velY * Game.getYScale();

        PosX = round(x / Game.getScale());
        PosY = round(y / Game.getYScale());


        /*
        if((float)PosX * Game.getScale() != x)
        {
            x = (float)PosX * Game.getScale();

        }
        if((float)PosY * Game.getYScale() != y)
        {
            y = (float)PosY * Game.getYScale();

        }
        */





        ObjHei = round(16 * Game.getYScale());
        ObjWid = round(16 * Game.getScale());

        x = Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        y = Main.clamp(y , 0 , Game.HEIGHT - ObjHei);


        
        double distance = Math.sqrt(((PosX - player.getPosX()) * (double)(PosX - player.getPosX()) + (double)(PosY - player.getPosY()) * (double)(PosY - player.getPosY())));

        velX = (float)  ((-1.0f/distance) * diffX);
        velX = Main.clamp(velX , -3 , 3);
        velY = (float)  ((-1.0f/distance) * diffY);
        velY = Main.clamp(velY , -3 , 3);





        handler.addObject(new Traill((int)x + Game.addX, (int)y + Game.addY, ID.Trail, color, ObjWid, ObjHei, 0.05f,handler));





    }
    
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x + Game.addX,(int)y + Game.addY,ObjWid,ObjHei);
        
        
    }
    public void destroy(){}
}

