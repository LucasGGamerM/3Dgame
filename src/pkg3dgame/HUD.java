/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Color;
import java.awt.Graphics;

import pkg3dgame.Game.DIFICULTY;
import pkg3dgame.Game.STATE;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class HUD {

    public static int HEALTH = 100;

    public static int BOSSHEALTH = 100;

    private int greenValue = 255;

    private int redValue = 255;

    private static int score = 0;

    private static int level = 1;

    private static boolean showBossHealth = false;

    public static int Dif = 1;

    private Game game;

    private Spawn spawn;

    public HUD(Game game, Spawn spawn)
    {
        this.game = game;
        this.spawn = spawn;
        Dif = Config.getDifficulty();
        switch(Dif)
        {
            case 1:
                game.gameDificulty = Game.DIFICULTY.FACIL;
                break;
            case 2:
                game.gameDificulty = Game.DIFICULTY.NORMAL;
                break;
            case 3:
                game.gameDificulty = Game.DIFICULTY.DIFICIL;
                break;
            case 4:
                game.gameDificulty = Game.DIFICULTY.INSANO;
                break;
            case 5:
                game.gameDificulty = Game.DIFICULTY.BRUTAL;
                break;
            default:
                game.gameDificulty = Game.DIFICULTY.NORMAL;
                break;
            

            
        }
    }

    public void tick() {
        if (game.gameDificulty == Game.DIFICULTY.FACIL) {
            Dif = 1;
        } else if (game.gameDificulty == Game.DIFICULTY.NORMAL) {
            Dif = 2;
        } else if (game.gameDificulty == Game.DIFICULTY.DIFICIL) {
            Dif = 3;
        } else if (game.gameDificulty == Game.DIFICULTY.INSANO) {
            Dif = 4;
        } else if (game.gameDificulty == Game.DIFICULTY.BRUTAL) {
            Dif = 5;
        }
        HEALTH = (int) Main.clamp(HEALTH, 0, 100);
        BOSSHEALTH = (int) Main.clamp(BOSSHEALTH, 0, 100);

        greenValue = (int) Main.clamp(greenValue, 0, 255);
        greenValue = HEALTH * 2;
        score++;

        if (HEALTH == 0) {
            game.gameState = STATE.Lost;

        }
        if (BOSSHEALTH == 0) {
            showBossHealth = false;
            spawn.killAllEnemies(true, true);
            BOSSHEALTH = 100;
            HUD.Dif += 1;

        }
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(Game.WIDTH - 224 + Game.addX, Game.HEIGHT - 64 + Game.addY, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(Game.WIDTH - 220 + Game.addX, Game.HEIGHT - 60 + Game.addY, (int) ((double) HEALTH * 1.92), 24);

        // desenha o Nível e a Pontuação
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10 + Game.addX, Game.HEIGHT - 50 + Game.addY);
        g.drawString("Level: " + level, 10 + Game.addX, Game.HEIGHT - 34 + Game.addY);
        // codigo temporario que tira o jogador depois da vida dele cair a zero;

        if (showBossHealth) {
            g.setColor(Color.gray);
            g.fillRect(10 + Game.addX, 10 + Game.addY, Game.WIDTH - 20, 32);
            g.setColor(new Color(redValue, 0, 0));
            g.fillRect(14 + Game.addX, 14 + Game.addY, (int) ((double) BOSSHEALTH * ((double) (Game.WIDTH - 28) / 100)),
                    24);
        }

    }

    public static void setScore(int score1) {
        score = score1;
    }

    public static int getScore() {
        return score;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level1) {
        level = level1;
    }

    public static void setShowBossHealth(boolean a) {
        showBossHealth = a;
    }

    public static void reset() {
        setShowBossHealth(false);
        HEALTH = 100;
        BOSSHEALTH = 100;

    }

}
