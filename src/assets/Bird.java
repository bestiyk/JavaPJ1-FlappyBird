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
package assets;

import java.awt.event.KeyEvent;

public class Bird extends Asset implements Updatable {
	
	private double velocity;
	private double gravity;
    private boolean birdStateFalling;
	public boolean dead;
	
	private int jumpDelay;
	
	private Keyboard keyboard;

	public Bird(String filepath) {
		super(filepath);
		this.setX(100);
		this.setY(150);
		this.setHeight(32);
		this.setWidth(45);
		this.velocity = 0;
	    this.gravity = 0.5;
	    this.jumpDelay = 0;
	    this.dead = false;
		this.birdStateFalling= false;
		
		
		keyboard = Keyboard.getInstance();
		
	}
	
	public void update() {
		this.setVelocity(this.getVelocity()+this.getGravity());
		//bird is falling. time to change animation
        if (this.getJumpDelay() > 0){
        	this.setJumpDelay(this.getJumpDelay()-1);
        }
		if(this.getVelocity()>2){
			this.birdStateFalling=true;
		}
		else{
			this.birdStateFalling=false;
		}
		//spacebar is pressed. make the bird jump up
        if (!dead && keyboard.isDown(KeyEvent.VK_SPACE) && this.getJumpDelay() <= 0) {
            this.setVelocity(-10);
            this.setJumpDelay(10);
        }
		//bird falling animation
		if(birdStateFalling){
			this.loadFile("img/birdFalling.png");
		}
		else{
			this.loadFile("img/bird.png");
		}
        y += (int)velocity;

    }

	@Override
	public void setSpeed(int speed) {

	}

	@Override
	public boolean collides(int objX, int objY, int objWidth, int objHeight) {
		return false;
	}

	@Override
	public boolean isUp() {
		return false;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public int getJumpDelay() {
		return jumpDelay;
	}

	public void setJumpDelay(int jumpDelay) {
		this.jumpDelay = jumpDelay;
	}
	
	
	
}
