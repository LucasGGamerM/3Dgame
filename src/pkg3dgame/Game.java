 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;




public class Game extends Canvas implements Runnable{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 640 , HEIGHT = 480;

    private Thread thread;

    private boolean running = false;

    private Handler handler;

    private HUD hud;

    private Spawn spawner;

    private Menu menu;

    private Dialog dialog;

    private Window window;

    public static int scale = 1;


    public enum STATE {
        Dialog,
        Game,
        Lost,
        Menu

    }

    public STATE gameState = STATE.Menu;


    public Game()
    {


        hud = new HUD(this);

        handler = new Handler();

        dialog = new Dialog(this);

        this.addKeyListener(new KeyInput(handler , this));

        spawner = new Spawn(handler , hud, dialog);



    	menu = new Menu(this, hud);

    	this.addMouseListener(menu);



    	window = new Window(WIDTH,HEIGHT,"ArcadeAdventure",this);
        System.out.println("It Created the Window!");





        //doing stuff for fun
    }
    public void reset()
    {
        handler.reset();
        HUD.HEALTH = 100;
        HUD.setShowBossHealth(false);
        HUD.BOSSHEALTH = 100;
        hud.setScore(0);
        spawner = new Spawn(handler , hud , dialog);
    }


    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;

    }
    public synchronized void stop()
    {
        try{
            thread.join();
            running = false;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1)
            {
                tick();
                delta--;
            }
            if(running)
            {
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void tick()
    {
        if(gameState == STATE.Game)
        {
        	  handler.tick();
        	  hud.tick();
            spawner.tick();

        }else if(gameState == STATE.Menu)
        {
        	 menu.tick();
        }else if(gameState == STATE.Dialog)
        {
            dialog.tick();
        }
    }
    private void render()
    {
        WIDTH = window.getWidth();
        HEIGHT = window.getHeight();


        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        if(gameState == STATE.Game)
        {
        	hud.render(g);
        	handler.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Lost)
        {
       	  menu.render(g);
        }else if(gameState == STATE.Dialog)
				{
					handler.render(g);
					dialog.render(g);
				}



        g.dispose();
        bs.show();

    }

    public static int getScale()
    {

            return Game.HEIGHT / 800 + 1;


    }

}
