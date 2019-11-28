/*
 * Copyright (c) 2019.
 * Author Matyas Dedek
 * Project JavaPJ1-FlappyBird
 *
 */

/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import assets.*;
import player.Score;

public class GameLogic {
    //constants
    public int TUBE_DELAY;
    private final int TUBE_GAP = 175;
    private final int GROUND_HEIGHT = 80;
    private double DIFFICULTY_COEFFICIENT;
    private final int DEFAULT_TUBE_SPEED = 3;
    Timer timer;
    //variables
    private Boolean paused;
    private Boolean gameover;
    private Boolean started;
    private Sound bgMusic;
    private int pauseDelay;
    private int restartDelay;
    private int tubeDelay;
    private Bird bird;
    private Keyboard keyboard;
    public Score score;
    private long startTime;
    private int diffSpikeCounter;
    //collections + arraylists
    private ArrayList<Updatable> updatables;

    //class methods
    public GameLogic() {
        keyboard = Keyboard.getInstance();
        restart();
    }
//restarts the game. Resets boolean variables. Creates new Tubes. Resets the score. Creates new bird instance
    public void restart() {
        updatables = new ArrayList<>();
        DIFFICULTY_COEFFICIENT = 1.2;
        TUBE_DELAY =100;
        paused = false;
        started = false;
        gameover = false;
        diffSpikeCounter = 0;
        if(bgMusic!=null)
        bgMusic.stop();
        bgMusic = new Sound("sound/backgroundMusic_takeonme.wav");
        score = new Score();
        pauseDelay = 0;
        restartDelay = 0;
        tubeDelay = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(diffSpikeCounter<10) {
                    DIFFICULTY_COEFFICIENT += 0.1;
                    diffSpikeCounter++;
                    TUBE_DELAY -= 5;
                }
                if(diffSpikeCounter>=10){
                    timer.cancel();
                }
                if(gameover) timer.cancel();
            }
        },10*1000,10*1000);
        bird = new Bird("img/bird.png");
        updatables.add(bird);

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
        //useless foreach. just for the sake of using an interface
        for(Updatable x:updatables){
            if(x instanceof Bird){
                x.update();
            }
        }

        if (gameover){
            return;
        }

        moveTubes();
        //checkForCollisions();
    }
    //loads assets into arraylist and sets assets to object. Also sets the speed of the pipes
    public ArrayList<Asset> getAssets() {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        assets.add(new Background("img/background.png"));
        //set speed for every tube and adds it to assets arraylist
        for(Updatable tube:updatables){
            if(tube instanceof Tube){
                tube.setSpeed((int)(DEFAULT_TUBE_SPEED*DIFFICULTY_COEFFICIENT));
                assets.add((Tube)tube);
            }
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

            for(Updatable tube:updatables){
                if(tube instanceof Tube){
                    if (tube.getX() - tube.getWidth() < 0) {
                        if (upTube == null) {
                            upTube = (Tube)tube;
                        } else if (downTube == null) {
                            downTube = (Tube)tube;
                            break;
                        }
                    }
                }
            }
            if (upTube == null) {
                Tube tube = new Tube("img/tubeUp.png", true);
                updatables.add(tube);
                upTube = tube;
            } else {
                upTube.reset();
            }

            if (downTube == null) {
                Tube tube = new Tube("img/tubeDown.png", false);
                updatables.add(tube);
                downTube = tube;
            } else {
                downTube.reset();
            }

            upTube.setY(downTube.getY() + downTube.getHeight() + TUBE_GAP);
       }

        for(Updatable tube:updatables){
            if(tube instanceof Tube){
                tube.update();
            }
        }

    }

    private void checkForCollisions() {

        for(Updatable tube:updatables){
            if(tube instanceof Tube){
                //tube collided with the bird. game over
                if (tube.collides(bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight())) {
                    gameover = true;
                    bird.dead = true;
                    score.writeHighScore();
                    bgMusic.stop();
                    bgMusic=new Sound("sound/gameOver.wav");
                } else if (tube.getX() == bird.getX() && !tube.isUp()) {
                    //bird got through the pipes. add 1 score.
                    score.addScore();
                }
            }
        }
        //bird touched the floor. game over
        if (bird.getY() + bird.getHeight() > Run.HEIGHT - GROUND_HEIGHT) {
            gameover = true;
            bird.dead = true;
            bird.setY(Run.HEIGHT - 80 - bird.getHeight());
            score.writeHighScore();
            bgMusic.stop();
            bgMusic=new Sound("sound/gameOver.wav");
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
	public int getDiffSpikeCounter(){return diffSpikeCounter;}
	
}
