/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Window extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JFrame frame;

	private static boolean Init = false;

	public static int Incremento = 0;

	public static int Scala;


	public Window(int width,int height, String title, Game game)
    {
         frame = new JFrame(title);

         frame.setPreferredSize(new Dimension(width,height));
         frame.setMinimumSize(new Dimension(Game.WIDTH,Game.HEIGHT));
         frame.setMaximumSize(new Dimension((int)getToolkit().getScreenSize().getWidth() ,(int)getToolkit().getScreenSize().getHeight()));
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(true);
         frame.setLocationRelativeTo(null);
         frame.add(game);
         frame.setVisible(true);
         game.start();
         
    }
    public int getWidth()
    {
        return frame.getContentPane().getWidth();
    }
    public int getHeight()
    {
        return frame.getContentPane().getHeight();
    }
    public static int getXFrameLocation()
    {
        return (int)frame.getLocation().getX();
    }
    public static int getYFrameLocation()
    {
        if((int)frame.getLocation().getY() == 0)
        {
            //System.out.println(frame.getSize());
            Game.FullScreen = true;
            return (int) frame.getLocation().getY();
        }else {
            //System.out.println(frame.getContentPane());
            //return (int)frame.getLocation().getY() + 23;
            //return (int) frame.getBounds().getY();
            Game.FullScreen = false;
            if(!Init)
            {
                Scala = Game.HEIGHT;
                Incremento = frame.getHeight() - Game.HEIGHT;
                frame.setSize(Game.WIDTH,frame.getHeight() + Incremento);
                Init = true;
            }
            return (int) frame.getLocationOnScreen().getY() + Incremento;
        }

    }
    public static JFrame getFrame()
    {
        return frame;
    }
    

}
