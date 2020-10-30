 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import static java.lang.Math.round;

 public class Game extends Canvas implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //private int Starting = 0;

    private int PosCenaX = 0, PosCenaY = 0;

    public static int WIDTH = 720, HEIGHT = 540;

    private Thread thread;

    private boolean running = false;

    public static boolean Init = false;
    public static boolean InitTwo = false;
    public static boolean UmZero = false;

    private Handler handler;

    private HUD hud;

    private Spawn spawn;

    private Menu menu;

    private Dialog dialog;

    private Window window;

    public static BufferedImage StartImage;

    public static Audio mainSound;

    public static boolean SFX = false;
     public static boolean Music = false;
     

    public static int master = 50;

    public static int addX = 0, addY = 0;

    public enum STATE {
        Dialog, Option, Game, Lost, Menu

    }

    public enum DIFICULTY {
        FACIL, NORMAL, DIFICIL, INSANO, BRUTAL
    }

    public DIFICULTY gameDificulty = DIFICULTY.NORMAL;

    public STATE gameState = STATE.Menu;

    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {

        handler = new Handler();

        dialog = new Dialog(this);

        this.addKeyListener(new KeyInput(handler , this));

        spawn = new Spawn(handler , hud, dialog, this);

        hud = new HUD(this, spawn);

        //teste de som 
        mainSound = new Audio("./MainSoundMenu.wav");

    	menu = new Menu(this, hud);

    	this.addMouseListener(menu);

        StartImage = Main.LoadImage("./menu.png");

    	window = new Window(WIDTH,HEIGHT,"ArcadeAdventure",this);
        System.out.println("It Created the Window!");
 
        PosCenaX = 0;
        

        //doing stuff for fun
    }
    public void reset()
    {
        handler.reset();
        HUD.HEALTH = 100;
        HUD.setShowBossHealth(false);
        HUD.BOSSHEALTH = 100;
        HUD.setScore(0);
        Spawn.setIsBoss(false);
        spawn = new Spawn(handler , hud , dialog, this);
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
                try {
                    tick();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
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
    private void tick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (!InitTwo) {
            PosCenaY = (int) -(HEIGHT / getYScale());
            InitTwo = !false;
            System.out.println(PosCenaY);
        }
        
        if(PosCenaY < 0 && Init == false)
        {
            PosCenaY += 1;

        }else{
            PosCenaY = 0;
            Init = true;
        }
        

        if(gameState == STATE.Game)
        {
        	  handler.tick();
        	  hud.tick();
        	  spawn.tick();
            //mainSound.pause();



        }else if(gameState == STATE.Menu || gameState == STATE.Lost || gameState == STATE.Option)
        {
            menu.tick();
            if(Music)
            {
                if(mainSound.getStatus().equals("paused"))
                {
                    mainSound.restart(true);
                }else
                {
                    mainSound.play(true);
                }
            }else{
                mainSound.pause();
            }



        	 //mainSound.resumeAudio();
        }else if(gameState == STATE.Dialog)
        {
            dialog.tick();
            mainSound.pause();
        }

        
    }
    private void render()
    {
        

        addX = ((Window.getFrame().getWidth() - WIDTH) / 2) + round(PosCenaX * Game.getScale());
        addY = ((Window.getFrame().getHeight() - Window.Incremento - HEIGHT) / 2) + round(PosCenaY * Game.getYScale());
        
        if (UmZero) {
            addY = ((Window.getFrame().getHeight() - HEIGHT) / 2) + PosCenaY;
        }
        
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        
        /*
        WIDTH = window.getWidth();
        HEIGHT = window.getHeight();
        */
        

        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Window.getFrame().getWidth(),Window.getFrame().getHeight());
        
        
        AffineTransform at = AffineTransform.getTranslateInstance(Game.addX, Game.addY);
        at.scale((double)Game.WIDTH / 700, (double)Game.HEIGHT / 500);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(StartImage, at, null);

        
        if(gameState == STATE.Game)
        {
            handler.render(g);
            hud.render(g);

        }else if(gameState == STATE.Menu || gameState == STATE.Lost || gameState == STATE.Option)
        {
       	  menu.render(g);
        }else if(gameState == STATE.Dialog)
		{
			handler.render(g);
			dialog.render(g);
		}

                if (window.getWidth() / 4 == window.getHeight() / 3) {
                    WIDTH = window.getWidth();
                    HEIGHT = window.getHeight();
                    
                } else if ((window.getWidth() / 4 >= window.getHeight() / 3)) {
                    HEIGHT = window.getHeight();
                    WIDTH = (window.getHeight() / 3) * 4;
                    g.setColor(Color.black);
                    g.fillRect(0, 0, (Window.getFrame().getWidth() - WIDTH) / 2, HEIGHT);

                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getFrame().getWidth() - (Window.getFrame().getWidth() - WIDTH) / 2, 0, (Window.getFrame().getWidth() - WIDTH) / 2, HEIGHT);
                    
                } else if ((window.getHeight() / 3 >= window.getWidth() / 4)) {
                    WIDTH = window.getWidth();
                    HEIGHT = (window.getWidth() / 4) * 3; 
                    g.setColor(Color.black);
                    g.fillRect(0, 0, WIDTH, (Window.getFrame().getHeight() - Window.Incremento - HEIGHT) / 2);

                    g.setColor(Color.BLACK);
                    g.fillRect(0,  Window.getFrame().getHeight() - Window.Incremento - (Window.getFrame().getHeight() - Window.Incremento - HEIGHT) / 2, WIDTH, (Window.getFrame().getHeight() - Window.Incremento - HEIGHT) / 2);
                }

        g.dispose();
        bs.show();

    }

    public static float getScale()
    {
        //WIDTH = round((float)(Game.HEIGHT / 455)/3) * 4;

        return (float)Game.HEIGHT / Window.Scala;


    }
     public static float getYScale()
     {
         //WIDTH = round((float)(Game.HEIGHT / 455)/3) * 4;

         return (float)Game.WIDTH / 720;


     }


 }
