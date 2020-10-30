/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

/** 
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Traill extends GameObject{
    
    private float alpha = 1;
    
    private Color color;

    private AffineTransform at;
    
    private Handler handler;

    private BufferedImage image = null;
    
    private int height;
    private int width;
    private float life;
    private float startingLife;
    
    public void setW(boolean a) { } ;
    public void setS(boolean a) { } ;
    public void setA(boolean a) { } ;
    public void setD(boolean a) { } ;
    
    public Traill(int x, int y, ID id, Color color, int width, int height, float life, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        this.color = color;
        
        this.width = width;
        this.height = height;
        this.startingLife = life;
        
    }
    public Traill(AffineTransform at, ID id, BufferedImage image, float life, Handler handler)
    {
        super((int)at.getTranslateX(), (int)at.getTranslateY(), id);
        this.at = at;
        this.image = image;
        this.startingLife = life;
        this.handler = handler;


    }
    
    public void tick()
    {
        life = startingLife * Menu.TraillLife;
        if(alpha>life)
        {
            alpha -= (life - 0.001f);
            
        }else {
            handler.removeObject(this); 
        }
           
    }
    
    public Rectangle getBounds()
    {
        return null;
    }
    
    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;



        if(image == null)
        {
            g2d.setComposite(makeTransparent(alpha));
            g.setColor(color);
            g.fillRect((int)x,(int) y, width , height );
            g2d.setComposite(makeTransparent(1));
        }else{
            g2d.drawImage(Main.makeImageTranslucent(image, alpha), at, null);
        }
    }
    private AlphaComposite makeTransparent(float Alpha)
    {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, Alpha));
    }
    public void destroy(){}
}
