/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public abstract class GameObject {
    
    protected float  x,  y;
    protected ID id;
    protected float velX, velY;
    protected int PosX, PosY;
    
    public GameObject(float x, float y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id; 
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract void setW(boolean a);
    public abstract void setS(boolean a);
    public abstract void setA(boolean a);
    public abstract void setD(boolean a);
    public abstract void destroy();
    
    
    public void setX(int x2)
    {
        this.x = x2;
    }
    public void setY(int y2)
    {
        this.y = y2;
        
    }
    public void setID(ID id2)
    {
        this.id = id2;
    }
    public float getX()
    {
        return this.x;
    }
    public float getY()
    {
        return this.y;
    }
    public int getPosY()
    {
        return this.PosY;
    }
    public int getPosX()
    {
        return this.PosX;
    }


    public ID getID()
    {
        return this.id;
    }
    public void setVelX(int VelX2)
    {
        this.velX = VelX2;
        
    }
    public void setVelY(int VelY2)
    {
        this.velY = VelY2;
        
    }
    public float getVelX()
    {
        return this.velX;
    }
    public float getVelY()
    {
        return this.velY;
    }
}
