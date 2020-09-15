/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;
/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Window extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	public Window(int width,int height, String title, Game game)
    {
         frame = new JFrame(title);
         frame.setPreferredSize(new Dimension(width,height));
         frame.setMinimumSize(new Dimension(640,480));
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


}
