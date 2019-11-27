/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package logic;
/**
 * @author Jiří Čech
 * @version 0.1
 */
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import assets.*;
import player.Score;

public class GameLogic {

    public static final int TUBE_DELAY = 100;

    private Boolean paused;
    private Boolean gameover;
    private Boolean started;

    private int pauseDelay;
    private int restartDelay;
    private int tubeDelay;

    private Bird bird;
    private ArrayList<Tube> tubeArray;
    private Keyboard keyboard;

    public Score score;
    
    public GameLogic() {
        keyboard = Keyboard.getInstance();
        restart();
    }
//restarts the game. Resets boolean variables. Creates new Tubes. Resets the score. Creates new bird instance
    public void restart() {
    	tubeArray = new ArrayList<Tube>();
    	
        paused = false;
        started = false;
        gameover = false;

        score = new Score();
        pauseDelay = 0;
        restartDelay = 0;
        tubeDelay = 0;

        bird = new Bird("img/bird.png");
    }
//check for key presses. update variables if any key fits. Also updates the bird's position. Checks for collisions with tubes and moves tubes closer to the bird.
    public void update() {

        isStarted();

        if (!started)
            return;

        isPause();
        isReset();

        if (paused)
            return;

        bird.update();

        if (gameover)
            return;

        moveTubes();
        checkForCollisions();
    }
//load assets into arraylist and sets attets to object. Also sets the speed of the pipes
    public ArrayList<Asset> getAssets() {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        assets.add(new Background("img/background.png"));
        //set speed for every tube and adds it to assets arraylist
        for (Tube tube : tubeArray) {
        	tube.setSpeed(3);
            assets.add(tube);
        }
        assets.add(new Background("img/foreground.png"));
        assets.add(bird);
        return assets;
    }

    private void isStarted() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void isPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void isReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    private void moveTubes() {

        tubeDelay--;
//reset pipe after 100 loops. (tube delay)
        if (tubeDelay < 0) {
            tubeDelay = TUBE_DELAY;
            Tube upTube = null;
            Tube downTube = null;

            for (Tube tube : tubeArray) {
                if (tube.getX() - tube.getWidth() < 0) {
                    if (upTube == null) {
                        upTube = tube;
                    } else if (downTube == null) {
                        downTube = tube;
                        break;
                    }
                }
            }

            if (upTube == null) {
                Tube tube = new Tube("img/tubeUp.png", true);
                tubeArray.add(tube);
                upTube = tube;
            } else {
                upTube.reset();
            }

            if (downTube == null) {
                Tube tube = new Tube("img/tubeDown.png", false);
                tubeArray.add(tube);
                downTube = tube;
            } else {
                downTube.reset();
            }

            upTube.setY(downTube.getY() + downTube.getHeight() + 175);
        }

        for (Tube tube : tubeArray) {
            tube.update();
        }
    }

    private void checkForCollisions() {

        for (Tube tube : tubeArray) {
            if (tube.collides(bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight())) {
                gameover = true;
                bird.dead = true;
                score.writeHighScore();
            } else if (tube.getX() == bird.getX() && !tube.isUp()) {
                //bird got through the pipes. add 1 score.
                score.addScore();
            }
        }
        //bird touched the floor. game over
        if (bird.getY() + bird.getHeight() > Run.HEIGHT - 80) {
            gameover = true;
            bird.dead = true;
            bird.setY(Run.HEIGHT - 80 - bird.getHeight());
            score.writeHighScore();
        }
    }

	public Boolean getPaused() {
		return paused;
	}

	public Boolean getGameover() {
		return gameover;
	}

	public Boolean getStarted() {
		return started;
	}
	
}