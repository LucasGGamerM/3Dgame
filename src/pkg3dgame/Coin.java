/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Coin extends GameObject{

    private Color color = Color.YELLOW;

    private Handler handler;

    private int ObjHei = 16, ObjWid = 16;

    public Coin(int x , int y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        velX = 0;
        velY = 0;
        System.out.println("before");



        ObjHei *= Game.getScale();
        ObjWid *= Game.getScale();

        System.out.println("Foi criado");



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
        System.out.println("tick");
        x += velX * Game.getScale();
        y += velY * Game.getScale();

        ObjHei = 16 * Game.getScale();
        ObjWid = 16 * Game.getScale();

        System.out.println("tick2");

        System.out.println("tick3");



        Main.clamp(x , 0 , Game.WIDTH - ObjWid);
        Main.clamp(y , 0 , Game.HEIGHT - ObjHei);



    }

    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,ObjWid,ObjHei);


    }
    public void destroy()
    {
        System.out.println("Removendo");
        handler.removeObject(this);
    }

}
