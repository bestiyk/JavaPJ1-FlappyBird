/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package logic;
/**
 * @author Matyas Dedek
 * @version 0.1
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import assets.Asset;


public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -2175422990641076173L;
	private GameLogic gameLogic;

    public GamePanel() {
        gameLogic = new GameLogic();
        new Thread(this).start();
    }

    public void update() {
        gameLogic.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        //call JPANEL methon paintComponent
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Asset a : gameLogic.getAssets()) {
        	g2D.drawImage(a.getImage(), a.getX(), a.getY(), null);
        }

        g2D.setColor(Color.BLACK);
        //game is not in progress
        if (!gameLogic.getStarted()) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
            g2D.drawString("Your current highscore is: " + Integer.toString(gameLogic.score.getHighScore()), 150, 275);
        } else {
        	//game is in pause screen
        	if(gameLogic.getPaused() && !(!gameLogic.getStarted() || gameLogic.getGameover())) {
        		g2D.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        		g2D.drawString("Paused", 160, 200);
        	}
        	
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString("Score: " + Integer.toString(gameLogic.score.getScore()), 10, 455);
            g2D.drawString("P for Pause", 350, 455);
        }
        //collision detected. Game over
        if (gameLogic.getGameover()) {
        	g2D.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        	g2D.drawString("Game Over!", 130, 200);
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
            g2D.drawString("Your current highscore is: " + Integer.toString(gameLogic.score.getHighScore()), 150, 275);
            
        }
    }

    public void run() {
        try {
            while (true) {
                //update all objects every 20 milis.
                update();
                Thread.sleep(20);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
